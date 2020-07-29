/**
 * 
 */
package com.amp.business.base;

import java.util.List;
import java.util.Map;

import com.amp.jpa.entities.Category;


/**
 * @author MVEKSLER
 *
 */
public interface BusinessBeanBaseLocal 
{
	public boolean invokeRequestMethod(
			Class<?> cClassToInvoke, 
			Object   cObjectToInvoke,
		    String   cRequestMethod, 
		    Map<String, Object> cMethodParams, 
		    Map<String, Object> cMethodResults );

	public boolean invokeRequestMethodExt(
			  Class<?> cClassToInvoke, 
			  Object   cObjectToInvoke,
		      String   cRequestMethod, 
		      Map<String, Object> cMethodParams, 
		      Map<String, Object> cMethodResults );
	
	public boolean getObjectsByType(
				Map<String, Object> cMethodParams, 
			    Map<String , Object> cMethodResults);			

	public boolean getObjectsByFilter(
				Map<String, Object> cMethodParams,
				Map<String, Object> cMethodResults);
	
	public boolean getObjectsNames(
				Map<String, Object> cMethodParams,
				Map<String, Object> cMethodResults);
	
	public boolean getObjectsNamesExt(
				Map<String, Object> cMethodParams,
				Map<String, Object> cMethodResults);
	
	public boolean handleCategoryNodeLookup(
			    Map<String, Object> cMethodParams,
	       	    Map<String, Object> cMethodResults);
	
	public boolean getCategoryNodeLookup(
			  	Map<String, Object> cMethodParams,
			  	Map<String, Object> cMethodResults);
	
	public boolean getRootCategories(
				Map<String, Object> cMethodParams,
				Map<String, Object> cMethodResults);
	
	public List<Category> getRootCategoriesExt(
				String cSourceName);
	
	
	public boolean getRootCategoriesNodes(
			Map<String, Object> cMethodParams,
			Map<String, Object> cMethodResults);
	
	public boolean getRootCategorySubNodes(
			Map<String, Object> cMethodParams,
		 	Map<String, Object> cMethodResults);
	
	public boolean handleItemSearchList(
		    Map<String, Object> cMethodParams,
       	    Map<String, Object> cMethodResults);

	public boolean getItemSearchList(
		  	Map<String, Object> cMethodParams,
		  	Map<String, Object> cMethodResults);
}
