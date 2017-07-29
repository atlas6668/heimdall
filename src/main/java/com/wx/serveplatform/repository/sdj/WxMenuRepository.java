/**
 * <p>Title: WxMenuRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月9日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.WxMenuButton;

/**
 * <p>Title: WxMenuRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月9日
 */
public interface WxMenuRepository extends BaseRepository<WxMenuButton, Integer>,
		JpaSpecificationExecutor<WxMenuButton> {

	/**
	 * <p>findByFatherButton</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月9日
	 * @param id
	 * @return 
	 */
	List<WxMenuButton> findByFatherButton(Integer id);

	
	
}
