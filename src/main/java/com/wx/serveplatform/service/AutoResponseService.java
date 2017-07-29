/**
 * <p>Title: AutoResponseService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月26日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.wx.serveplatform.model.AutoResponse;

/**
 * <p>Title: AutoResponseService</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月26日
 */
public interface AutoResponseService {

	/**
	 * <p>findByCategory</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月26日
	 * @param category
	 * @return
	 */
	List<AutoResponse> findByCategory(String category);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月26日
	 * @param model
	 * @return
	 */
	AutoResponse save(AutoResponse model);

	/**
	 * <p>searchKeywords</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月31日
	 * @param searchParams
	 * @return
	 */
	Page<AutoResponse> searchKeywords(Map<String, Object> searchParams);

	/**
	 * <p>findByIdAndCategory</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月1日
	 * @param id
	 * @param category
	 * @return
	 */
	AutoResponse findByIdAndCategory(Integer id, String category);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月1日
	 * @param _ids
	 */
	void delete(String[] _ids);

}
