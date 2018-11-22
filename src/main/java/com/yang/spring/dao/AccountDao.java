package com.yang.spring.dao;

import com.yang.spring.bean.Account;

import java.util.List;

public interface AccountDao {

    void insert(Account account);

    List<Account> getAccounts(Account account);
}
