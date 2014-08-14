package com.github.rabid_fish;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.AndRevFilter;
import org.eclipse.jgit.revwalk.filter.AuthorRevFilter;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.MaxCountRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.gitective.core.CommitUtils;
import org.joda.time.DateTime;

public class RepoReader {

	private String repositoryPath = null;
	private String author = null;
	private Integer maxHits = null;
	private Date since = null;
	private Date until = null;

	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		
		/*
		 * Immediate goals:
		 *  1) every day, be able to track number of lines of code change per developer
		 *  2) count lines of inline css added
		 *  3) count lines of inlines js added
		 * This should be straight-up reporting for graphs that we can print out
		 * 
		 * Longer term goals
		 *  1) run custom checker script past new lines and determine if there are violations
		 * This should be added to Jenkins so a dev can be notified when he/she commits bad code
		 * 
		 * Some more metrics to grab about our code:
		 * number of files
		 * number of files over a certain length
		 * average method length
		 */
		
		String author = args[0];
		String repoPath = args[1];  // Path on local disk, e.g. - /Users/<username>/<repo>
		
		RepoReader repoReader = new RepoReader();
		repoReader.setAuthor(author);
		repoReader.setMaxHits(20);
		repoReader.setRepositoryPath(repoPath);
		repoReader.findCommitsUsingJGitAndWalk(System.out);
	}
	
	void findCommitsUsingJGitAndWalk(PrintStream printStream) throws IOException {
		
		validateFilters();
		
		File repositoryFolder = new File(repositoryPath);
		Git git = Git.open(repositoryFolder);
		Repository repository = git.getRepository();

		AnyObjectId start = CommitUtils.getCommit(repository, "HEAD");

		RevWalk walk = new RevWalk(repository);
		walk.setRetainBody(true);
		walk.markStart(walk.parseCommit(start));

		List<RevFilter> list = new ArrayList<RevFilter>();
		
		addFilterForDates(list);
		addFilterForMaxHits(list);
		addFilterForAuthor(list);
		
		walk.setRevFilter(AndRevFilter.create(list));
		
		outputCommitsMetadata(printStream, repository, walk);
	}

	void outputCommitsMetadata(PrintStream printStream, Repository repository, RevWalk walk) {
		
		int commitCount = 0;
		for (RevCommit commit : walk) {
			commitCount++;
			outputCommitMetadata(printStream, commit, commitCount);
			outputCommitCodeChanges(printStream, repository, commit);
		}
	}

	void outputCommitMetadata(PrintStream printStream, RevCommit commit, int commitCount) {
		
		String message =
				commitCount++ + " >> " + commit.getFullMessage() + "\n"
				+ "\t" + commit.getCommitTime() + "\n"
				+ "\t" + commit.getAuthorIdent().getName() + "\n"
				;
		
		printStream.print(message);
	}
	
	/*
	 * Written with help from
	 * https://github.com/gitblit/gitblit/blob/master/src/main/java/com/gitblit/utils/JGitUtils.java#L718
	 */
	void outputCommitCodeChanges(PrintStream printStream, Repository repository, RevCommit commit) {
		outputFileAdditions(printStream, repository, commit);
		outputFileDiffs(printStream, repository, commit);
	}

	void outputFileAdditions(PrintStream printStream, Repository repository, RevCommit commit) {
		
		if (commit.getParentCount() != 0) return;
		
		try {
			TreeWalk tw = new TreeWalk(repository);
			tw.reset();
			tw.setRecursive(true);
			tw.addTree(commit.getTree());
			
			while (tw.next()) {
				printStream.println("\t+" + tw.getPathString());
//			list.add(new PathChangeModel(tw.getPathString(), tw.getPathString(), 0, tw
//			.getRawMode(0), tw.getObjectId(0).getName(), commit.getId().getName(),
//			ChangeType.ADD));
			}
			
			tw.release();
			
		} catch (IOException e) {
			printStream.println("\t** Unable to display file additions **");
			return;
		}
	}

	void outputFileDiffs(PrintStream printStream, Repository repository, RevCommit commit) {
		
		if (commit.getParentCount() == 0) return;
		
		DiffFormatter df = new DiffFormatter(printStream);
		df.setRepository(repository);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);
		
		RevWalk rw = new RevWalk(repository);
		List<DiffEntry> diffs = null;
		try {
			RevCommit parent = rw.parseCommit(commit.getParent(0).getId());
			diffs = df.scan(parent.getTree(), commit.getTree());
		} catch (IOException e) {
			printStream.println("\t** Unable to display file diffs **");
			return;
		}
		
		for (DiffEntry diff : diffs) {
			printStream.println("\t" + diff.getNewPath());
//			// create the path change model
//			PathChangeModel pcm = PathChangeModel.from(diff, commit.getName());
//
//			if (calculateDiffStat) {
//				// update file diffstats
//				df.format(diff);
//				PathChangeModel pathStat = df.getDiffStat().getPath(pcm.path);
//				if (pathStat != null) {
//					pcm.insertions = pathStat.insertions;
//					pcm.deletions = pathStat.deletions;
//				}
//			}
//			list.add(pcm);
		}
	}

	void validateFilters() {
		
		int filterCount = 0;
		if (author != null) filterCount++;
		if (maxHits != null) filterCount++;
		if (since != null || until != null) filterCount++;
		
		if (filterCount < 2) {
			throw new IllegalArgumentException("Unable to process RepoReader request, not enough filters have been configured");
		}
		
		if (since != null && until != null) {
			DateTime sinceDateTime = new DateTime(since);
			DateTime untilDateTime = new DateTime(until);
			if (sinceDateTime.isAfter(untilDateTime) || sinceDateTime.isEqual(untilDateTime)) {
				throw new IllegalArgumentException("Unable to process RepoReader request, since date is equal to or after until date");
			}
		}
	}

	void addFilterForDates(List<RevFilter> list) {
		
		if (since != null && until != null) {
			RevFilter commitTimeRevFilter = CommitTimeRevFilter.between(since, until);
			list.add(commitTimeRevFilter);
		} else if (since != null) {
			RevFilter commitTimeRevFilter = CommitTimeRevFilter.after(since);
			list.add(commitTimeRevFilter);
		} else if (until != null) {
			RevFilter commitTimeRevFilter = CommitTimeRevFilter.before(until);
			list.add(commitTimeRevFilter);
		}
	}

	void addFilterForMaxHits(List<RevFilter> list) {
		if (maxHits != null) {
			RevFilter maxCountRevFilter = MaxCountRevFilter.create(maxHits);
			list.add(maxCountRevFilter);
		}
	}

	void addFilterForAuthor(List<RevFilter> list) {
		if (author != null) {
			RevFilter authorRevFilter = AuthorRevFilter.create(author);
			list.add(authorRevFilter);
		}
	}

	public String getRepositoryPath() {
		return repositoryPath;
	}

	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getMaxHits() {
		return maxHits;
	}

	public void setMaxHits(Integer maxHits) {
		this.maxHits = maxHits;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}
}
