package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.domain.Item;
import org.mypetstore.persistence.CartDAO;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDAOImpl implements CartDAO {
    public final static String INSERT= "INSERT INTO Cart (USERid, ItemID, ProductID, Description, InStock, Quantity,ListPrice,TotalCost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE= "UPDATE Cart SET Quantity = ? WHERE ItemID = ? AND USERID = ?";
    public final static String DELETE="DELETE FROM Cart WHERE ItemID = ? and Userid = ?";
    public final static String DELETEALL="DELETE FROM Cart WHERE Userid = ?";
    public final static String SELECT="SELECT USERid, ItemID, ProductID, Description, InStock, Quantity,ListPrice,TotalCost FROM CART WHERE USERID=?";

    @Override
    public void insertCart(CartItem cartItem,String userid) {
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT);
            preparedStatement.setString(1,userid);
            preparedStatement.setString(2,cartItem.getItem().getItemId());
            preparedStatement.setString(3,cartItem.getItem().getProduct().getProductId());
            preparedStatement.setString(4,cartItem.getItem().getAttribute1()+cartItem.getItem().getAttribute2());
            preparedStatement.setString(5,String.valueOf( cartItem.isInStock()));
            preparedStatement.setInt(6,cartItem.getQuantity());
            preparedStatement.setBigDecimal(7,cartItem.getItem().getListPrice());
            preparedStatement.setBigDecimal(8,cartItem.getTotal());
            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(CartItem cartItem,String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1,cartItem.getQuantity());
            preparedStatement.setString(2,cartItem.getItem().getItemId());
            preparedStatement.setString(3,userid);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(CartItem cartItem, String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE);;
            preparedStatement.setString(1,cartItem.getItem().getItemId());
            preparedStatement.setString(2,userid);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETEALL);
            preparedStatement.setString(1,userid);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart selectCart(String userid) {
        Cart cart=new Cart();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT);
            preparedStatement.setString(1,userid);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                CatalogService catalogService = new CatalogService();
                String workingItemId = resultSet.getString(2);
                boolean isInStock = resultSet.getString(5)=="true";
                int quantity = resultSet.getInt(6);
                Item item = catalogService.getItem(workingItemId);
                cart.addItem(item, isInStock,quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
}
