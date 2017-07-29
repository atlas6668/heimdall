/**
 * <p>Title: AccountService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月10日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.util.List;

import com.wx.serveplatform.model.Account;

/**
 * <p>Title: AccountService</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月10日
 */
public interface AccountService {

	/**
	 * <p>findAll</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月10日
	 * @return
	 */
	List<Account> findAll();

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月10日
	 * @param model
	 * @return
	 */
	Account save(Account model);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月10日
	 * @param _ids
	 */
	void delete(String[] _ids);

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年4月10日
	 * @param id
	 * @return
	 */
	Account findById(Integer id);

}
