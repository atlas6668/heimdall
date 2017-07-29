/**
 * <p>Title: AccountServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月10日
 * @version 1.0
 */
package com.wx.serveplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.serveplatform.model.Account;
import com.wx.serveplatform.repository.sdj.AccountRepository;

/**
 * <p>Title: AccountServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月10日
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	public Account save(Account model) {
		return accountRepository.save(model);
	}

	@Override
	public void delete(String[] _ids) {
		for(String id : _ids){
			accountRepository.delete(Integer.parseInt(id));
		}
	}

	@Override
	public Account findById(Integer id) {
		return accountRepository.findById(id);
	}
	
}
