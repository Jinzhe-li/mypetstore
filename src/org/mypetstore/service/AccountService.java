package org.mypetstore.service;

import org.mypetstore.domain.Account;
import org.mypetstore.persistence.AccountDAO;
import org.mypetstore.persistence.impl.AccountDAOImpl;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO =new AccountDAOImpl();
    }
    public Account getAccount(String userid,String password){
        return accountDAO.getAccountByUserid(userid,password);
    }
    public boolean isAccount(String userid){
        return accountDAO.getAccount(userid);
    }
    public void insert(Account account){
        accountDAO.insertAccount(account);
    }
    public void update(Account account){
        accountDAO.updateAccount(account);
    }
}
