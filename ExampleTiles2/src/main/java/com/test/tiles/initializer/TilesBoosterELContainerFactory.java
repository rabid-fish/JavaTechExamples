/*
 * $Id: TilesBoosterELContainerFactory.java 836356 2009-11-15 13:27:43Z apetrelli $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.test.tiles.initializer;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELResolver;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.context.ChainedTilesRequestContextFactory;
import org.apache.tiles.context.TilesRequestContextFactory;
import org.apache.tiles.definition.DefinitionsFactoryException;
import org.apache.tiles.definition.pattern.DefinitionPatternMatcherFactory;
import org.apache.tiles.definition.pattern.PatternDefinitionResolver;
import org.apache.tiles.definition.pattern.PrefixedPatternDefinitionResolver;
import org.apache.tiles.definition.pattern.regexp.RegexpDefinitionPatternMatcherFactory;
import org.apache.tiles.definition.pattern.wildcard.WildcardDefinitionPatternMatcherFactory;
import org.apache.tiles.el.ELAttributeEvaluator;
import org.apache.tiles.el.JspExpressionFactoryFactory;
import org.apache.tiles.el.TilesContextBeanELResolver;
import org.apache.tiles.el.TilesContextELResolver;
import org.apache.tiles.evaluator.AttributeEvaluatorFactory;
import org.apache.tiles.evaluator.BasicAttributeEvaluatorFactory;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.impl.BasicTilesContainer;
import org.apache.tiles.impl.mgmt.CachingTilesContainer;
import org.apache.tiles.locale.LocaleResolver;
import org.apache.tiles.renderer.AttributeRenderer;
import org.apache.tiles.renderer.TypeDetectingAttributeRenderer;
import org.apache.tiles.renderer.impl.BasicRendererFactory;
import org.apache.tiles.renderer.impl.ChainedDelegateAttributeRenderer;
import org.apache.tiles.util.URLUtil;

/**
 * Copied from TilesBoosterELContainerFactory but removed support for MVEL and OGNL.
 * 
 * Tiles container factory that:
 * <ul>
 * <li>allows using EL as attribute expressions;</li>
 * <li>allows using Wildcards and Regular Expressions in definition names;</li>
 * <li>loads all the definition files that have the "tiles*.xml" pattern under
 * <code>/WEB-INF</code> directory (and subdirectories) and under
 * <code>META-INF</code> directories (and subdirectories) in every jar.</li>
 * </ul>
 * 
 * @version $Rev: 836356 $ $Date: 2009-11-15 14:27:43 +0100 (dom, 15 nov 2009) $
 * @since 2.2.0
 */
public class TilesBoosterELContainerFactory extends BasicTilesContainerFactory {

	/** {@inheritDoc} */
	@Override
	protected BasicTilesContainer instantiateContainer(TilesApplicationContext applicationContext) {
		return new CachingTilesContainer();
	}

	/** {@inheritDoc} */
	@Override
	protected List<TilesRequestContextFactory> getTilesRequestContextFactoriesToBeChained(ChainedTilesRequestContextFactory parent) {
		List<TilesRequestContextFactory> factories = super.getTilesRequestContextFactoriesToBeChained(parent);
		return factories;
	}

	/** {@inheritDoc} */
	@Override
	protected void registerAttributeRenderers(BasicRendererFactory rendererFactory, TilesApplicationContext applicationContext,
		TilesRequestContextFactory contextFactory, TilesContainer container, AttributeEvaluatorFactory attributeEvaluatorFactory) {

		super.registerAttributeRenderers(rendererFactory, applicationContext, contextFactory, container, attributeEvaluatorFactory);
	}

	/** {@inheritDoc} */
	@Override
	protected AttributeRenderer createDefaultAttributeRenderer(BasicRendererFactory rendererFactory,
		TilesApplicationContext applicationContext, TilesRequestContextFactory contextFactory, TilesContainer container,
		AttributeEvaluatorFactory attributeEvaluatorFactory) {
		ChainedDelegateAttributeRenderer retValue = new ChainedDelegateAttributeRenderer();
		retValue.addAttributeRenderer((TypeDetectingAttributeRenderer) rendererFactory.getRenderer(DEFINITION_RENDERER_NAME));
		retValue.addAttributeRenderer((TypeDetectingAttributeRenderer) rendererFactory.getRenderer(TEMPLATE_RENDERER_NAME));
		retValue.addAttributeRenderer((TypeDetectingAttributeRenderer) rendererFactory.getRenderer(STRING_RENDERER_NAME));
		retValue.setApplicationContext(applicationContext);
		retValue.setRequestContextFactory(contextFactory);
		retValue.setAttributeEvaluatorFactory(attributeEvaluatorFactory);
		return retValue;
	}

	/** {@inheritDoc} */
	@Override
	protected AttributeEvaluatorFactory createAttributeEvaluatorFactory(TilesApplicationContext applicationContext,
		TilesRequestContextFactory contextFactory, LocaleResolver resolver) {

		BasicAttributeEvaluatorFactory attributeEvaluatorFactory = new BasicAttributeEvaluatorFactory(
			createELEvaluator(applicationContext));

		return attributeEvaluatorFactory;
	}

	/** {@inheritDoc} */
	@Override
	protected <T> PatternDefinitionResolver<T> createPatternDefinitionResolver(Class<T> customizationKeyClass) {
		DefinitionPatternMatcherFactory wildcardFactory = new WildcardDefinitionPatternMatcherFactory();
		DefinitionPatternMatcherFactory regexpFactory = new RegexpDefinitionPatternMatcherFactory();
		PrefixedPatternDefinitionResolver<T> resolver = new PrefixedPatternDefinitionResolver<T>();
		resolver.registerDefinitionPatternMatcherFactory("WILDCARD", wildcardFactory);
		resolver.registerDefinitionPatternMatcherFactory("REGEXP", regexpFactory);
		return resolver;
	}

	/** {@inheritDoc} */
	@Override
	protected List<URL> getSourceURLs(TilesApplicationContext applicationContext, TilesRequestContextFactory contextFactory) {
		try {
			Set<URL> finalSet = new HashSet<URL>();
			Set<URL> webINFSet = applicationContext.getResources("/WEB-INF/**/tiles*.xml");
			Set<URL> metaINFSet = applicationContext.getResources("classpath*:META-INF/**/tiles*.xml");

			if (webINFSet != null) {
				finalSet.addAll(webINFSet);
			}
			if (metaINFSet != null) {
				finalSet.addAll(metaINFSet);
			}

			return URLUtil.getBaseTilesDefinitionURLs(finalSet);
		} catch (IOException e) {
			throw new DefinitionsFactoryException("Cannot load definition URLs", e);
		}
	}

	/**
	 * Creates the EL evaluator.
	 * 
	 * @param applicationContext
	 *            The Tiles application context.
	 * @return The EL evaluator.
	 */
	private ELAttributeEvaluator createELEvaluator(TilesApplicationContext applicationContext) {

		ELAttributeEvaluator evaluator = new ELAttributeEvaluator();
		evaluator.setApplicationContext(applicationContext);
		JspExpressionFactoryFactory efFactory = new JspExpressionFactoryFactory();
		efFactory.setApplicationContext(applicationContext);

		evaluator.setExpressionFactory(efFactory.getExpressionFactory());
		ELResolver elResolver = new CompositeELResolver() {
			{
				add(new TilesContextELResolver());
				add(new TilesContextBeanELResolver());
				add(new ArrayELResolver(false));
				add(new ListELResolver(false));
				add(new MapELResolver(false));
				add(new ResourceBundleELResolver());
				add(new BeanELResolver(false));
			}
		};
		evaluator.setResolver(elResolver);
		
//    	System.out.println("Completed TilesBoosterELContainerFactory.createELEvaluator");

    	return evaluator;
	}

}