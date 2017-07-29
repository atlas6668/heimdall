/**
 * <p>Title: MaterialService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月5日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.wx.serveplatform.model.NewsHouse;
import com.wx.serveplatform.model.Photo;

/**
 * <p>Title: MaterialService</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月5日
 */
public interface MaterialService {

	/**
	 * <p>upload</p>
	 * <p>上传资源</p>
	 * @author damon
	 * @date 2015年3月5日
	 * @param file
	 * @param request
	 * @return
	 */
	String upload(MultipartFile file, String targetPath, HttpServletRequest request) throws IOException;

	/**
	 * <p>findPhotoById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param id
	 * @return
	 */
	Photo findPhotoById(Integer id);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param model
	 * @return
	 */
	Photo save(Photo model);

	/**
	 * <p>searchPhotos</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月6日
	 * @param searchParams
	 * @return
	 */
	Page<Photo> searchPhotos(Map<String, Object> searchParams);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月9日
	 * @param _ids
	 * @param request 
	 */
	void delete(String[] _ids, HttpServletRequest request) throws IOException;

	/**
	 * <p>searchNews</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年3月17日
	 * @param searchParams
	 * @return
	 */
	Page<NewsHouse> searchNews(Map<String, Object> searchParams);

}
