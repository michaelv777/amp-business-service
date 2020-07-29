/**
 * 
 */
package com.amp.business.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MVEKSLER
 *
 */
public interface BusinessBeanBaseRemote 
{
	public Map<String, Object> handleRequest(
			String cRequestMethod, Map<String, Object> cMethodParams);
	
	public boolean setResources(HashMap<String, String> cResources);
}
