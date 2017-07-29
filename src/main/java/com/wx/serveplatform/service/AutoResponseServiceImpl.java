/**
 * <p>Title: AutoResponseServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月26日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.serveplatform.model.AutoResponse;
import com.wx.serveplatform.repository.sdj.AutoResponseRepository;

/**
 * <p>Title: AutoResponseServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年3月26日
 */
@Service
@Transactional
public class AutoResponseServiceImpl implements AutoResponseService {

	@Autowired
	private AutoResponseRepository autoResponseRepository;

	@Override
	public List<AutoResponse> findByCategory(String category) {
		return autoResponseRepository.findByCategory(category);
	}

	@Override
	public AutoResponse save(AutoResponse model) {
		return autoResponseRepository.save(model);
	}

	@Override
	public Page<AutoResponse> searchKeywords(Map<String, Object> searchParams) {
		int pageNum = (int) searchParams.get("pageNum"); 
		int pageSize = (int) searchParams.get("pageSize");
		//多个order
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "id"));
		
		//PageRequest page zero-based page index. size the size of the page to be returned.
		return autoResponseRepository.findAll(buildKeywordsSpecification(searchParams), 
				new PageRequest(pageNum - 1, pageSize, new Sort(orders)));
	}

	/**
	 * <p>buildKeywordsSpecification</p>
	 * <p>构建查询条件</p>
	 * @author damon
	 * @date 2015年3月31日
	 * @param searchParams
	 * @return
	 */
	private Specification<AutoResponse> buildKeywordsSpecification(final Map<String, Object> searchParams) {
		return new Specification<AutoResponse>(){  
	        public Predicate toPredicate(Root<AutoResponse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	AutoResponse model = (AutoResponse) searchParams.get("model");
	        	//where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
	        	// 自动回复 关键词回复
	        	pl.add(cb.equal(root.get("category").as(String.class), AutoResponse.KEYWORD));
	        	if(model.getKeyword() != null && !("").equals(model.getKeyword())){
					pl.add(cb.equal(root.get("keyword").as(String.class), model.getKeyword()));
				}
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
	public AutoResponse findByIdAndCategory(Integer id, String category) {
		return autoResponseRepository.findByIdAndCategory(id, category);
	}

	@Override
	public void delete(String[] _ids) {
		for(String id : _ids){
			autoResponseRepository.delete(Integer.parseInt(id));
		}
	}
	
}
