package com.test.tiles.view;


public class TilesBoosterUri {

	private String viewName;
	private String viewJs;
	private String viewCss;
	
	private String uriBase;
	private String contextBase;
	
	protected TilesBoosterUri() {}
	
	public static TilesBoosterUri constructUri(String uriPath, String contextPath) {
		
		TilesBoosterUri uri = new TilesBoosterUri();
		uri.setProperties(uriPath, contextPath);
		return uri;
	}
	
	protected void setProperties(String uriPath, String contextPath) {
		
		int uriPathLength = uriPath.length();
		int viewNameStart = uriPath.lastIndexOf('/');
		int contextPathLength = contextPath.length();
		
		validate(uriPathLength, viewNameStart, contextPathLength);
		
		viewName = uriPath.substring(viewNameStart + 1, uriPathLength - 4);
		
		uriBase = uriPath.substring(0, viewNameStart);
		
		contextBase = uriBase.substring(contextPathLength);

		viewJs = contextBase + "/" + viewName + ".js";
		viewCss = contextBase + "/" + viewName + ".css";
	}

	private void validate(int uriPathLength, int viewNameStart, int contextPathLength) {

		if (uriPathLength == 0) {
			throw new IllegalArgumentException("Invalid argument passed to setProperties, uriPath is empty");
		}

		if (viewNameStart == -1) {
			throw new IllegalArgumentException("Invalid argument passed to setProperties, viewNameStart is missing slashes in its path");
		}

		if (contextPathLength == 0) {
			throw new IllegalArgumentException("Invalid argument passed to setProperties, contextPath is empty");
		}

		if (contextPathLength > uriPathLength) {
			throw new IllegalArgumentException("Invalid argument passed to setProperties, contextPath cannot be longer than uriPath");
		}
	}
	
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getUriBase() {
		return uriBase;
	}
	public void setUriBase(String uriBase) {
		this.uriBase = uriBase;
	}
	public String getContextBase() {
		return contextBase;
	}
	public void setContextBase(String resourceBase) {
		this.contextBase = resourceBase;
	}
	public String getViewJs() {
		return viewJs;
	}
	public void setViewJs(String viewJs) {
		this.viewJs = viewJs;
	}
	public String getViewCss() {
		return viewCss;
	}
	public void setViewCss(String viewCss) {
		this.viewCss = viewCss;
	}
}
