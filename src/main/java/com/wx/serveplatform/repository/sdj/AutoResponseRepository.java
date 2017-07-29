/**
 * <p>Title: AutoResponseRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月26日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.AutoResponse;

/**
 * <p>Title: AutoResponseRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月26日
 */
public interface AutoResponseRepository extends BaseRepository<AutoResponse, Integer>, JpaSpecificationExecutor<AutoResponse> {

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
	 * <p>findByIdAndCategory</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月1日
	 * @param id
	 * @param category
	 * @return
	 */
	AutoResponse findByIdAndCategory(Integer id, String category);

}
