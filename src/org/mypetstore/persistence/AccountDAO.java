package org.mypetstore.persistence;

import org.mypetstore.domain.Account;

public interface AccountDAO {
    Account getAccountByUserid(String userid,String password);
    boolean getAccount(String userid);
    void insertAccount(Account account);
    void updateAccount(Account account);
}
