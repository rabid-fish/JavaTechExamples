package com.test.tiles.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;

public class TilesBoosterViewPreparerHelper {

	private final TilesBoosterAttributeHelper attributeHelper;

	public TilesBoosterViewPreparerHelper(String[][] layouts) {
		this.attributeHelper = new TilesBoosterAttributeHelper(layouts);
	}
	
	public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext) {
		
		Object[] requestObjects = tilesContext.getRequestObjects();
		for (Object requestObject : requestObjects) {
			if (requestObject instanceof javax.servlet.jsp.PageContext) {
				PageContext page = (PageContext) requestObject;
				if (page.getRequest() instanceof HttpServletRequest) {

					HttpServletRequest request = (HttpServletRequest) page.getRequest();

					attributeHelper.putAttributeForLayout(tilesContext.getRequestScope(), request);

					attributeHelper.putAttributesForSympatheticResources(
						page.getServletContext(), 
						tilesContext.getRequestScope(), 
						request.getRequestURI());

					break;
				}
			}
		}
	}
}
