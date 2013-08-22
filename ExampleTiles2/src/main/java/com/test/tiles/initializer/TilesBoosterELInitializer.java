package com.test.tiles.initializer;

import javax.servlet.ServletContext;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.servlet.wildcard.WildcardServletTilesApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;


public class TilesBoosterELInitializer extends AbstractTilesInitializer {

    /** {@inheritDoc} */
    @Override
    protected TilesApplicationContext createTilesApplicationContext(
            TilesApplicationContext preliminaryContext) {
    	
        return new WildcardServletTilesApplicationContext(
                (ServletContext) preliminaryContext.getContext());
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractTilesContainerFactory createContainerFactory(
            TilesApplicationContext context) {
    	
//    	System.out.println("Executing TilesBoosterELInitializer.createContainerFactory");
        return new TilesBoosterELContainerFactory();
    }
}