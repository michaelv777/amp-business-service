package com.amp.business.aws;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amp.business.base.BusinessBeanBase;
import com.amp.common.api.impl.ToolkitReflection;
import com.amp.jpa.entities.manager.IJPAEntity;

/**
 * Session Bean implementation class OperationalBeanAWS
 */

@Transactional
@Service("operationsBeanAWS")
public class OperationsBeanAWS extends    BusinessBeanBase
							   implements OperationsBeanRemoteAWS, 
       						   	          OperationsBeanLocalAWS
{
	private static final Logger LOG = 
			LoggerFactory.getLogger(OperationsBeanAWS.class);
	
	//---class variables
    private ToolkitReflection iReflection = null;
    
    //---getters/setters
    
	//---class methods---------------------------------
    /**
     * Default constructor. 
     */
 
    public OperationsBeanAWS() 
    {
		try
    	{
    	
    	}
    	catch( Exception e)
    	{
    		LOG.error(e.getMessage(), e);
    	}
    }
    //---
    @PostConstruct
	public void init()
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		//------
    		
    		if ( cRes )
    		{
	    		if ( null == this.cDataHandler )
	    		{
	    			LOG.error(methodName + "::this.cDataHandler is null  for DataHandlerAWS.class!!");
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.getcDataHandler().getcToolkitDataProvider() )
	    		{
	    			LOG.error(methodName + "::cToolkitDataProvider is NULL!");
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    	}
	}
    /*-------------------------------------------------------------------*/
    @PreDestroy
	public void freeResources()
	{
		String  methodName = "";
		
		try
    	{
    		methodName = this.iReflection.getMethodName();
    	}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    	}
	}
    //---
    
    //---
	/* (non-Javadoc)
	 * @see business.configuration.E2EConfigurationBeanBaseI#handleRequest
	 * (java.lang.String,  java.util.Map)
	 */
	@Override
	public Map<String, Object> handleRequest(
			String cRequestMethod, Map<String, Object> cMethodParams) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		Map<String, Object> cMethodResults = new HashMap<String, Object>();
		
		try
    	{
    		methodName = this.iReflection.getMethodName();
    		
    		if ( null == cMethodParams )
    		{
    			cRes = false;
    		}
    		if ( cRes )
    		{
	    		cRes = this.invokeRequestMethod(
	    						this.getClass(), 
	    						this, 
	    						cRequestMethod, 
	    						cMethodParams, 
	    						cMethodResults);
    		
    		}
    		
    		return cMethodResults;	 
    	}
		
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		
    		return cMethodResults;
    	}
	}
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean getObjectsByType(Map<String, Object> cMethodParams,
							  		Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		NativeQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
			
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		
    		String cTableName = cObjectType;
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		if ( null == cObjectType)
    		{
    			LOG.error(methodName + "::null == cMappingFile!");
    		}
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("com.amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cTableName = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.getcDataHandler().getcToolkitDataProvider() )
	    		{
	    			LOG.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			Vector<String> repParams = new Vector<String>();
    			
    			repParams.add(cTableName);
    			
    			sqlQuery = this.getcDataHandler().getcToolkitDataProvider().
    							gettSQL().getSqlQueryByFunctionName(methodName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			LOG.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = this.getcDataHandler().getcToolkitDataProvider().
    							gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cObjectsValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cObjectsValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cObjectsValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			LOG.error(methodName + "::" + cnf.getMessage());
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean getObjectsByFilter(Map<String, Object> cMethodParams,
							  		  Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		NativeQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
			
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cFilter     = (String)cMethodParams.get("FILTER");
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		if ( null == cFilter)
    		{
    			LOG.error(methodName + "::null == cFilter!");
    		}
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("com.amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.getcDataHandler().getcToolkitDataProvider() )
	    		{
	    			LOG.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			Vector<String> repParams = new Vector<String>();
    			
    			repParams.add(cObjectType);
    			
    			sqlQuery = this.getcDataHandler().getcToolkitDataProvider().
    							gettSQL().getSqlQueryByFunctionName(methodName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			LOG.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = this.getcDataHandler().getcToolkitDataProvider().
    							gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			LOG.error(methodName + "::" + cnf.getMessage());
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	//---
	
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean getObjectsNamesExt(Map<String, Object> cMethodParams,
						   			  Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		NativeQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		String cFunctionName = (String)cMethodParams.get("FUNCTION");
    		Vector<String> repParams = (Vector<String>)cMethodParams.get("PARAMS"); 
    		
    		if ( (null == cObjectType) || (null == cFunctionName) || (null == repParams))
    		{
    			LOG.error(methodName + "::(null == cTableName) || " +
    									   "(null == cFunctionName) || (null == repParams):" + methodName);
    		}
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("com.amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.getcDataHandler().getcToolkitDataProvider() )
	    		{
	    			LOG.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			sqlQuery = this.getcDataHandler().getcToolkitDataProvider().
    							gettSQL().getSqlQueryByFunctionName(cFunctionName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			LOG.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = this.getcDataHandler().getcToolkitDataProvider().
    							gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			LOG.error(methodName + "::" + cnf.getMessage());
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	
	//---
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean getObjectsNames(Map<String, Object> cMethodParams,
			   				  	  Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		Session hbsSession = null;
		
		Transaction tx = null;
		
		Statement st = null;
		
		ResultSet rs = null;
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType    = (String)cMethodParams.get("OBJECT_TYPE");
    		String cFunctionName = (String)cMethodParams.get("FUNCTION");
    		Vector<String> repParams =  (Vector<String>)cMethodParams.get("PARAMS"); 
    		
    		if ( (null == cObjectType) || (null == cFunctionName) || (null == repParams))
    		{
    			LOG.error(methodName + "::(null == cTableName) || " +
    									   "(null == cFunctionName) || (null == repParams):" + methodName);
    		}
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("com.amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.getcDataHandler().getcToolkitDataProvider() )
	    		{
	    			LOG.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			sqlQuery = this.getcDataHandler().getcToolkitDataProvider().
    								gettSQL().getSqlQueryByFunctionName(cFunctionName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			LOG.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = this.getcDataHandler().getcToolkitDataProvider().
    								gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    				
    			/*
    			SessionFactoryImplementor sessionFactoryImplementation = 
    					(SessionFactoryImplementor) hbsSession.getSessionFactory();
    			
    			
    		    ConnectionProvider connectionProvider = 
    		    		sessionFactoryImplementation.getConnectionProvider();
    		    
    		    Connection conn = connectionProvider.getConnection();
    			*/
    		    
    			
    		   /*	
    		   Connection conn = hbsSession.getSessionFactory().getSessionFactoryOptions().
    		    		getServiceRegistry().getService(ConnectionProvider.class).getConnection();
    		    
    			*/
    			
    			SessionImpl sessionImpl = (SessionImpl) hbsSession;
    			
    			Connection conn = sessionImpl.connection();
    			
    		    st = conn.createStatement();
    			
    		    LOG.debug("M.V. Custom:: " + sqlQuery);
    			
        		rs = st.executeQuery(sqlQuery);

        		List<String> cValues = new ArrayList<String>();
        		
    			while (rs.next()) 
    			{
    				String cObjectName = rs.getString("NAME");
    				
    				cValues.add(cObjectName);
    			}
    			
    			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			LOG.error(methodName + "::" + cnf.getMessage());
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		LOG.error(methodName + "::" + e.getMessage());
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			if ( rs != null ) 
			{

				try 
				{ rs.close(); }
				catch( SQLException e )
				{LOG.warn("Result set could not be closed: " + e.getMessage());}
			}

			if ( st != null ) {

				try 
				{ st.close(); }
				catch( SQLException e ) 
				{LOG.warn("Statement could not be closed: " + e.getMessage());}
			}
			
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	
	//---
	@Override
	public boolean handleCategoryNodeLookup(Map<String, Object> cMethodParams,
			  	  				       	    Map<String, Object> cMethodResults)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	LOG.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandler.handleNodeLookup(cMethodParams, cMethodResults);
	        }
	        
	        LOG.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
    }
	
	//---
	@Override
	public boolean handleItemSearchList(Map<String, Object> cMethodParams,
			  	  				        Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	LOG.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandler.handleItemSearchList(cMethodParams, cMethodResults);
	        }
	        
	        LOG.info("------------------");
	        
	        return cRes;
		}
		catch( Exception e)
		{
			LOG.error(cMethodName + "::Exception:" + e.getMessage());
			
			return cRes;
		}
	}

	//---
	@Override
	public boolean getCategoryNodeLookup(Map<String, Object> cMethodParams,
			  	  				       	 Map<String, Object> cMethodResults)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	LOG.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandler.getNodeLookup(cMethodParams, cMethodResults);
	        }
	        
	        LOG.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
    }
	
	@Override
	public boolean getItemSearchList(Map<String, Object> cMethodParams, 
									 Map<String, Object> cMethodResults) 
	{
		
		boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	LOG.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandler.getItemSearchList(cMethodParams, cMethodResults);
	        }
	        
	        LOG.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
	}
}
