/**
 * <p>Title: JdbcCrudRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2014年12月10日
 * @version 1.0
 */
package com.wx.serveplatform.base.repository.jdbc;

import java.util.Collection;

/**
 * <p>Title: JdbcCrudRepository</p>
 * <p></p> 
 * @author damon
 * @date 2014年12月10日
 */
public interface JdbcCrudRepository<T> extends JdbcRepository<T> {

	void save(T t);
	
	void delete(T t);
	
	void update(T t);

	Collection<T> findAll();
}
