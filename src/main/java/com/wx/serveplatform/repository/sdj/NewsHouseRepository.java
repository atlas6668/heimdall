/**
 * <p>Title: NewsHouseRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月17日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.NewsHouse;

/**
 * <p>Title: NewsHouseRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月17日
 */
public interface NewsHouseRepository extends BaseRepository<NewsHouse, Integer>, JpaSpecificationExecutor<NewsHouse> {

}
