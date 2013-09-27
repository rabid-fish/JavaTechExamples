package com.github.rabid_fish.config.helper;

import java.util.ArrayList;
import java.util.List;

import com.github.rabid_fish.config.ConfigurationColumn;
import com.github.rabid_fish.config.QueueConfigSearch;

/**
 * A 'default' instance of this class can be created as a spring bean.  This will cause
 * the json configuration file to be read _once_ and only once.  From that point forward,
 * use the 'clone' method and provide a search parameter to get an instance that has
 * the configured columns plus the search criteria column.
 */
public class QueueConfigSearchHelper extends QueueConfigHelper {

	public static final String SEARCH_TITLE = "Search";

	private QueueConfigSearch queueConfigSearch = null;
	
	private QueueConfigSearchHelper() {
		// allow for cloning
		super();
	}
	
	public QueueConfigSearchHelper(String resourcePath) {
		queueConfigSearch = getConfigFromResourcePath(QueueConfigSearch.class, resourcePath);
	}
	
	public QueueConfigSearchHelper clone(String searchString) {
		
		List<ConfigurationColumn> columns = getQueueConfigSearch().getColumns();
		List<ConfigurationColumn> cloneColumns = new ArrayList<ConfigurationColumn>(columns);
		
		ConfigurationColumn column = createSearchColumn(searchString);
		cloneColumns.add(column);
		
		QueueConfigSearch cloneSearch = new QueueConfigSearch();
		cloneSearch.setColumns(cloneColumns);
		
		QueueConfigSearchHelper cloneHelper = new QueueConfigSearchHelper();
		cloneHelper.setQueueConfigSearch(cloneSearch);
		
		return cloneHelper;
	}

	private ConfigurationColumn createSearchColumn(String search) {
		
		ConfigurationColumn column = new ConfigurationColumn();
		column.setTitle(SEARCH_TITLE);
		column.setRegex(search);
		
		return column;
	}

	public void setQueueConfigSearch(QueueConfigSearch queueConfigSearch) {
		this.queueConfigSearch = queueConfigSearch;
	}

	public QueueConfigSearch getQueueConfigSearch() {
		return queueConfigSearch;
	}
}
