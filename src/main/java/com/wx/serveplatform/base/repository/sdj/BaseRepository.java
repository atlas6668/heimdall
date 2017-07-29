/**
 * <p>Title: BaseRepository.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: xiaoma.com</p>
 * @author damon
 * @date 2014年5月28日
 * @version 1.0
 */
package com.wx.serveplatform.base.repository.sdj;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * <p>Title: BaseRepository</p>
 * <p>Description: 抽象出符合jpa规范的方法，可供子接口继承，且能被spring data jpa自动注册、封装实现</p>
 * <p>最佳方案是从原Repository子接口中直接copy方法过来</p>
 * <p>Company: xiaoma.com</p> 
 * @author damon
 * @date 2014年5月28日
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
	
	/**
	 * <p>Title: findById</p>
	 * <p>Description: 基于实体类继承自BaseEntity，必有id属性</p>
	 * @param id
	 * @return
	 */
	T findById(ID id);
	
	/**
	 * <p>Title: findAll</p>
	 * <p>Description: 查全部</p>
	 * @return
	 */
	Collection<T> findAll();
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: 保存（包括新增和修改）</p>
	 * @param entity
	 * @return
	 */
	T save(T entity);
	
	/**
	 * Deletes the entity with the given id.
	 * <p>Description: 指定id删除</p>
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	void delete(ID id);

	/**
	 * Deletes a given entity.
	 * <p>Description: 指定entity删除</p>
	 * @param entity
	 * @throws IllegalArgumentException in case the given entity is (@literal null}.
	 */
	void delete(T entity);
	
	/**
	 * Deletes the given entities.
	 * <p>Description: 批量删除</p>
	 * <p>执行外键检查，且抛异常</p>
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is (@literal null}.
	 */
	void delete(Iterable<? extends T> entities);
	
	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
	 * the {@link javax.persistence.EntityManager} after the call.
	 * <p>Description: 批量删除InBatch</p>
	 * <p>不执行外键检查</p>
	 * @param entities
	 */
	void deleteInBatch(Iterable<T> entities);
	
}
