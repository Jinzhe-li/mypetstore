package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Account;
import org.mypetstore.persistence.AccountDAO;
import org.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static final String getAccountByUsernameString = "SELECT USERID AS userid,EMAIL AS email,FIRSTNAME AS firstname,LASTNAME AS lastname,STATUS AS status,ADDR1 AS address1,ADDR2 AS address2,CITY AS city,STATE AS state,ZIP AS zip,COUNTRY AS country,PHONE AS phone FROM ACCOUNT WHERE USERID=?";
    private static final String getAccountSignonString = "SELECT PASSWORD AS password FROM SIGNON WHERE USERNAME=?";
    private static final String getAccountProfile = "SELECT LANGPREF AS languagePreference,FAVCATEGORY AS favouriteCategoryId,MYLISTOPT AS listOption,BANNEROPT AS bannerOption FROM PROFILE WHERE USERID=?";
    private static final String getAccountString = "SELECT USERNAME FROM SIGNON WHERE USERNAME=?";
    private static final String insertAccountString = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String insertSignOnString = "INSERT INTO SIGNON VALUES(?,?)";
    private static final String insertProfileString = "INSERT INTO PROFILE VALUES(?,?,?,?,?)";
    private static final String updateAccountString = "UPDATE ACCOUNT SET USERID=?,EMAIL=?,FIRSTNAME=?,LASTNAME=?,STATUS=?,ADDR1=?,ADDR2=?,CITY=?,STATE=?,ZIP=?,COUNTRY=?,PHONE=? WHERE USERID=?";
    private static final String updateSignOnString = "UPDATE SIGNON SET PASSWORD=? WHERE USERNAME=?";
    private static final String updateProfileString = "UPDATE PROFILE SET USERID=?,LANGPREF=?,FAVCATEGORY=?,MYLISTOPT=?,BANNEROPT=? WHERE USERID=?";
    @Override
    public Account getAccountByUserid(String userid,String password) {
        Account account = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(getAccountSignonString);
            statement.setString(1,userid);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()&&resultSet.getString(1).equals(password))
            {
                account =new Account();
                account.setPassword(resultSet.getString(1));
                statement=conn.prepareStatement(getAccountByUsernameString);
                statement.setString(1,userid);
                resultSet = statement.executeQuery();
                resultSet.next();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                statement=conn.prepareStatement(getAccountProfile);
                statement.setString(1,userid);
                resultSet = statement.executeQuery();
                resultSet.next();
                account.setLanguagePreference(resultSet.getString(1));
                account.setFavouriteCategoryId(resultSet.getString(2));
                account.setListOption(resultSet.getInt(3)==1);
                account.setBannerOption(resultSet.getInt(4)==1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean getAccount(String userid) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(getAccountString);
            statement.setString(1,userid);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                DBUtil.closeResultSet(resultSet);
                DBUtil.closeStatement(statement);
                DBUtil.closeConnection(conn);
                return false;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(insertAccountString);
            statement.setString(1,account.getUsername());
            statement.setString(2,account.getEmail());
            statement.setString(3,account.getFirstName());
            statement.setString(4,account.getLastName());
            statement.setString(5,account.getStatus());
            statement.setString(6,account.getAddress1());
            statement.setString(7,account.getAddress2());
            statement.setString(8,account.getCity());
            statement.setString(9,account.getState());
            statement.setString(10,account.getZip());
            statement.setString(11,account.getCountry());
            statement.setString(12,account.getPhone());
            statement.executeUpdate();
            statement = conn.prepareStatement(insertSignOnString);
            statement.setString(1,account.getUsername());
            statement.setString(2,account.getPassword());
            statement.executeUpdate();
            statement = conn.prepareStatement(insertProfileString);
            statement.setString(1,account.getUsername());
            statement.setString(2,account.getLanguagePreference());
            statement.setString(3,account.getFavouriteCategoryId());
            if(account.isListOption()){
                statement.setInt(4,1);
            }
            else statement.setInt(4,0);
            if(account.isBannerOption()){
                statement.setInt(5,1);
            }
            else statement.setInt(5,0);
            statement.executeUpdate();
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateAccountString);
            statement.setString(1,account.getUsername());
            statement.setString(2,account.getEmail());
            statement.setString(3,account.getFirstName());
            statement.setString(4,account.getLastName());
            statement.setString(5,account.getStatus());
            statement.setString(6,account.getAddress1());
            statement.setString(7,account.getAddress2());
            statement.setString(8,account.getCity());
            statement.setString(9,account.getState());
            statement.setString(10,account.getZip());
            statement.setString(11,account.getCountry());
            statement.setString(12,account.getPhone());
            statement.setString(13,account.getUsername());
            int i=statement.executeUpdate();
            statement = conn.prepareStatement(updateSignOnString);
            statement.setString(2,account.getUsername());
            statement.setString(1,account.getPassword());
            statement.executeUpdate();
            statement = conn.prepareStatement(updateProfileString);
            statement.setString(1,account.getUsername());
            statement.setString(2,account.getLanguagePreference());
            statement.setString(3,account.getFavouriteCategoryId());
            if(account.isListOption()){
                statement.setInt(4,1);
            }
            else statement.setInt(4,0);
            if(account.isBannerOption()){
                statement.setInt(5,1);
            }
            else statement.setInt(5,0);
            statement.setString(6,account.getUsername());
            statement.executeUpdate();
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
