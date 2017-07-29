/**
 * <p>Title: JpaCrudRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2014年10月27日
 * @version 1.0
 */
package com.wx.serveplatform.base.repository.jpa;

import java.util.Collection;

/**
 * <p>Title: JpaCrudRepository</p>
 * <p></p> 
 * @author damon
 * @date 2014年10月27日
 */
public interface JpaCrudRepository<T> extends JpaRepository<T> {
	
	void save(T t);
	
	void delete(T t);
	
	void update(T t);

	Collection<T> findAll();
}
