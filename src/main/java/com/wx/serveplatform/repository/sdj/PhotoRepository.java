/**
 * <p>Title: PhotoRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月6日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.Photo;

/**
 * <p>Title: PhotoRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月6日
 */
public interface PhotoRepository extends BaseRepository<Photo, Integer>, JpaSpecificationExecutor<Photo> {

}
