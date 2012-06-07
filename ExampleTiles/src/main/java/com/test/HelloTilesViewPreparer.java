package com.test;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

import com.test.tiles.view.TilesBoosterViewPreparerHelper;

/**
 * Read more about Tiles' ViewPreparer at:
 * http://tiles.apache.org/2.2/framework/tutorial/advanced/preparer.html
 */
public class HelloTilesViewPreparer implements ViewPreparer {

	String[][] layouts = {
		{ "default", "layout.jsp" },
		{ "/ajax/", "layoutNoChrome.jsp" },
	};
	
	TilesBoosterViewPreparerHelper viewPreparerHelper = new TilesBoosterViewPreparerHelper(layouts);
	
	public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext) {
		viewPreparerHelper.execute(tilesContext, attributeContext);
	}

}