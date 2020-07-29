package com.apm.business.aws.test;
/**
 * 
 */


import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.amp.business.aws.OperationsBeanAWS;
import com.amp.common.api.impl.ToolkitConstants;
import com.amp.common.api.impl.ToolkitDataProvider;
import com.amp.jpa.entities.Category;

/**
 * @author MVEKSLER
 *
 */
public class OperationsBeanAWSTest {

	ToolkitDataProvider cToolkitDataProvider = null;
	/**
	 * @param name
	 */
	public OperationsBeanAWSTest() {
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception 
	{
		
		
		try
		{
			if ( this.cToolkitDataProvider != null )
			{
				this.cToolkitDataProvider.closeConnection();
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}

	/**
	 * Test method for {@link com.amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore 
	@Test
	public void validateToolkitDataProvider() 
	{
		try
		{
			
			this.cToolkitDataProvider = new ToolkitDataProvider();
			
			if ( this.cToolkitDataProvider.isLcRes() )
			{
				boolean cRes = this.cToolkitDataProvider.openConnection();
				if ( !cRes )
				{
					fail("testToolkitDataProvider failed to open DB connection!");
				}
			}
			
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}

	/**
	 * Test method for {@link com.amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore 
	@Test
	public void validateGetRootDepartments() 
	{
		try
		{
			OperationsBeanAWS cOperationsBeanAWS = new OperationsBeanAWS();
			cOperationsBeanAWS.init();
			
			List<Category> cDepartments = cOperationsBeanAWS.getRootCategoriesExt("Amazon");
			
			for( Category cDepartment : cDepartments )
			{
				System.out.println(cDepartment.getName());
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}
	
	/**
	 * Test method for {@link com.amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore
	@Test
	public void validateHandleCategoryNodeLookup() 
	{
		boolean cRes = true;
		
		try
		{
			Map<String, Object> cMethodParams = new HashMap<String, Object>();
		    Map<String, Object> cMethodResults = new HashMap<String, Object>();
		       	 
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put(ToolkitConstants.BROWSE_NODE_ID_PARAM, "677211011");
		    
		    cMethodParams.put("p1", params);
		    
		    OperationsBeanAWS cOperationsBeanAWS = new OperationsBeanAWS();
		    cOperationsBeanAWS.init();
		    
		    cOperationsBeanAWS.handleCategoryNodeLookup(cMethodParams, cMethodResults);
		    
			if ( !cRes )
			{
				fail("testMappingHandler failed build MappingHandler object!");
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}
	
}
