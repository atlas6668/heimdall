/**
 * <p>Title: MaterialServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月5日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wx.serveplatform.common.utils.EhCacheUtil;
import com.wx.serveplatform.common.utils.FileUtils;
import com.wx.serveplatform.model.NewsHouse;
import com.wx.serveplatform.model.Photo;
import com.wx.serveplatform.repository.sdj.NewsHouseRepository;
import com.wx.serveplatform.repository.sdj.PhotoRepository;
import com.wx.serveplatform.utils.MaterialUtil;

/**
 * <p>Title: MaterialServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月5日
 */
@Service
@Transactional
@Cacheable(cacheManager="baseCache")
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private NewsHouseRepository newsHouseRepository;
	
	@Override
	public String upload(MultipartFile file, String targetPath, HttpServletRequest request) throws IOException {
		return FileUtils.transferTo(file, request, targetPath);
	}

	@Override
	public Photo findPhotoById(Integer id) {
		return photoRepository.findById(id);
	}

	@Override
	public Photo save(Photo model) {
		model = photoRepository.save(model);
		// 清除缓存，以退为进，迫使ehcache更新缓存
		EhCacheUtil.remove(MaterialUtil.MATERIAL_CACHE, MaterialUtil.CACHE_PHOTO_LIST);
		return model;
	}

	@Override
	public Page<Photo> searchPhotos(Map<String, Object> searchParams) {
		int pageNum = (int) searchParams.get("pageNum"); 
		int pageSize = (int) searchParams.get("pageSize");
		//多个order
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "loadtimemills"));
		
		//PageRequest page zero-based page index. size the size of the page to be returned.
		return photoRepository.findAll(buildPhotosSpecification(searchParams), 
				new PageRequest(pageNum - 1, pageSize, new Sort(orders)));
	}
	
	/**
	 * <p>Title: buildPhotosSpecification</p>
	 * <p>Description: 构建查询条件</p>
	 * @param searchParams
	 * @return
	 */
	private Specification<Photo> buildPhotosSpecification(final Map<String, Object> searchParams) {
		return new Specification<Photo>(){  
	        public Predicate toPredicate(Root<Photo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	//where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
				//拼接where 条件
	        	Object[] oa = pl.toArray();
	        	Predicate[] pp = new Predicate[oa.length];
	        	for(int i = 0; i < oa.length; i++){
	        		pp[i] = (Predicate) oa[i];
	        	}
	        	query.where(pp);
	            return query.getRestriction();  
	        }  
    	};
	}

	@Override
	public void delete(String[] _ids, HttpServletRequest request) throws IOException {
		Photo photo;
		String wholepath;
		String path;
		int index;
		String fileName;
		for(String id : _ids){
			photo = photoRepository.findById(Integer.parseInt(id));
			wholepath = photo.getPath();
			index = wholepath.lastIndexOf("/");
			path = wholepath.substring(0, index);
			fileName = wholepath.substring(index + 1, wholepath.length());
			photoRepository.delete(photo);
			FileUtils.clear(path, request, fileName);
		}
		// 清除缓存，以退为进，迫使ehcache更新缓存
		EhCacheUtil.remove(MaterialUtil.MATERIAL_CACHE, MaterialUtil.CACHE_PHOTO_LIST);
	}

	@Override
	public Page<NewsHouse> searchNews(Map<String, Object> searchParams) {
		int pageNum = (int) searchParams.get("pageNum"); 
		int pageSize = (int) searchParams.get("pageSize");
		//多个order
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "createtimemills"));
		
		//PageRequest page zero-based page index. size the size of the page to be returned.
		return newsHouseRepository.findAll(buildNewsSpecification(searchParams), 
				new PageRequest(pageNum - 1, pageSize, new Sort(orders)));
	}
	
	/**
	 * <p>Title: buildNewsSpecification</p>
	 * <p>Description: 构建查询条件</p>
	 * @param searchParams
	 * @return
	 */
	private Specification<NewsHouse> buildNewsSpecification(final Map<String, Object> searchParams) {
		return new Specification<NewsHouse>(){  
	        public Predicate toPredicate(Root<NewsHouse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	//where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
				//拼接where 条件
	        	Object[] oa = pl.toArray();
	        	Predicate[] pp = new Predicate[oa.length];
	        	for(int i = 0; i < oa.length; i++){
	        		pp[i] = (Predicate) oa[i];
	        	}
	        	query.where(pp);
	            return query.getRestriction();  
	        }  
    	};
	}
	
	
}
