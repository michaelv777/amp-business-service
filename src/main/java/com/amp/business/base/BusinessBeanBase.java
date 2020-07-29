/**
 * 
 */
package com.amp.business.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.amp.common.api.impl.ToolkitDataProvider;
import com.amp.common.api.impl.ToolkitReflection;
import com.amp.data.handler.aws.DataHandlerAWS;
import com.amp.jpa.entities.Category;

/**
 * @author MVEKSLER
 *
 */
public abstract class BusinessBeanBase implements BusinessBeanBaseLocal, BusinessBeanBaseRemote
{
	private static final Logger LOG = 
			LoggerFactory.getLogger(BusinessBeanBase.class);
	
	@Autowired
	protected DataHandlerAWS cDataHandler = null;
	
	@Autowired
	protected ApplicationContext applicationContext = null;
	
	//---class variables---------------------------------
   
	private ToolkitReflection iReflection = null;
	//---getters/setters-------------------------------
	
	public DataHandlerAWS getcDataHandler() {
		return cDataHandler;
	}

	public void setcDataHandler(DataHandlerAWS cDataHandler) {
		this.cDataHandler = cDataHandler;
	}
	
	/**
	 * 
	 */
	public BusinessBeanBase() 
	{
		try
		{
			this.iReflection = new ToolkitReflection();	
		}
		catch( Exception e )
		{
			
		}
	}
	
	//---
	@Override
	public boolean invokeRequestMethod(Class<?> cClassToInvoke, 
									   Object   cObjectToInvoke,
									   String   cRequestMethod, 
									   Map<String, Object> cMethodParams, 
									   Map<String, Object> cMethodResults )
	{
		boolean cRes = true;
		
		try
		{
			Method[] cMethods = cClassToInvoke.getMethods();
			
			for(Method cMethod : cMethods)
			{
	            if(cRequestMethod.equals((cMethod.getName())))
	            {
	            	cMethod.invoke(cObjectToInvoke, cMethodParams, cMethodResults);
	            	
	            	break;
	            }
	        }
			
			return cRes;
		}
		catch( IllegalArgumentException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( InvocationTargetException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( IllegalAccessException  e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( SecurityException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( Exception e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
	}
	//---
	@Override	
	public boolean invokeRequestMethodExt(Class<?> cClassToInvoke, 
										  Object   cObjectToInvoke,
									      String   cRequestMethod, 
									      Map<String, Object> cMethodParams, 
									      Map<String, Object> cMethodResults )
	{
		boolean cRes = true;
		
		try
		{
			
			Class<?> c = Class.forName(cClassToInvoke.getClass().getName());
			
			Method method = c.getDeclaredMethod(cRequestMethod, Map.class, Map.class);
			
			method.invoke(cObjectToInvoke, cMethodParams, cMethodResults);
			
			return cRes;
		}
		catch( NoSuchMethodException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( InvocationTargetException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( IllegalAccessException  e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( ClassNotFoundException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( IllegalArgumentException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( SecurityException e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
		catch( Exception e )
		{
			LOG.error("invokeRequestMethod::" + e.getMessage(), e);
			
			return ( cRes = false );
		}
	}
	//---
	/**
	 * @param cSourceName
	 * @return
	 */
	@Override
	public List<Category> getRootCategoriesExt(String cSourceName)
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		List<Category> cCategories = new LinkedList<Category>();
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		if ( null == cSourceName )
    		{
    			LOG.error(methodName + "::(null == cSourceName)");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cCategories = this.cDataHandler.getRootCategories(cSourceName);
    		}
    		//------
    		
    		return cCategories;	 
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		return new LinkedList<Category>();
    	}
	}
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategories(Map<String, Object> cMethodParams,
			   						 Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			LOG.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandler.getRootCategories(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		return cRes;
    	}
	}
	
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategoriesNodes(Map<String, Object> cMethodParams,
			   						 	  Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			LOG.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandler.getRootCategoriesNodes(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		return cRes;
    	}
	}
		
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategorySubNodes(Map<String, Object> cMethodParams,
			   						 	   Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			LOG.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandler.getRootCategorySubNodes(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		return cRes;
    	}
	}
	
	//---
	@Override
	public boolean setResources(HashMap<String, String> cResources) 
	{
		String  methodName = "";
		
		boolean cRes = true;
		
		try
		{
			this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
			if ( this.cDataHandler.getcToolkitDataProvider() == null )
    		{
    			this.cDataHandler.setcToolkitDataProvider(new ToolkitDataProvider());
    			
    			if ( cRes )
    			{
    				cRes = this.cDataHandler.getcToolkitDataProvider().loadSettings();
    			}
    			
    			if ( cRes )
    			{
    				cRes = this.cDataHandler.getcToolkitDataProvider().init();
    			}
    			
    		}

			/*
			if ( this.getcToolkitDataProvider() == null )
    		{
    			this.setcToolkitDataProvider(this.cDataHandler.getcToolkitDataProvider());
    		}
    		*/
			
			return cRes ;
		}
		catch( Exception e)
		{
			LOG.error(methodName + "::" + e.getMessage());
    		
    		return cRes;
		}
	}
}
