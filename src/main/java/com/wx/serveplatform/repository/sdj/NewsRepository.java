/**
 * <p>Title: NewsRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月17日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.News;

/**
 * <p>Title: NewsRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月17日
 */
public interface NewsRepository extends BaseRepository<News, Integer>, JpaSpecificationExecutor<News> {

}
