package org.mypetstore.persistence.impl;

import org.mypetstore.domain.LineItem;
import org.mypetstore.domain.Order;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.persistence.OrderDAO;
import org.mypetstore.service.OrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public final static String INSERT= "INSERT INTO ORDERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String SELECT="SELECT ORDERID,USERID,ORDERDATE,SHIPADDR1,SHIPADDR2,SHIPCITY,SHIPSTATE,SHIPZIP,SHIPCOUNTRY,BILLADDR1,BILLADDR2,BILLCITY,BILLSTATE,BILLZIP,BILLCOUNTRY,COURIER,TOTALPRICE,BILLTOFIRSTNAME,BILLTOLASTNAME,SHIPTOFIRSTNAME,SHIPTOLASTNAME,CREDITCARD,EXPRDATE,CARDTYPE,LOCALE FROM ORDERS WHERE USERID=?";
    @Override
    public void insertOrder(Order order) {

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1,order.getOrderId());
            statement.setString(2,order.getUsername());
            String date=order.getOrderDate().toString().trim();
            statement.setString(3, date);
            statement.setString(4,order.getShipAddress1());
            statement.setString(5,order.getShipAddress2());
            statement.setString(6,order.getShipCity());
            statement.setString(7,order.getShipState());
            statement.setString(8,order.getShipZip());
            statement.setString(9,order.getShipCountry());
            statement.setString(10,order.getBillAddress1());
            statement.setString(11,order.getBillAddress2());
            statement.setString(12,order.getBillCity());
            statement.setString(13,order.getBillState());
            statement.setString(14,order.getBillZip());
            statement.setString(15,order.getBillCountry());
            statement.setString(16,order.getCourier());
            statement.setBigDecimal(17,order.getTotalPrice());
            statement.setString(18,order.getBillToFirstName());
            statement.setString(19,order.getBillToLastName());
            statement.setString(20,order.getShipToFirstName());
            statement.setString(21,order.getShipToLastName());
            statement.setString(22,order.getCreditCard());
            statement.setString(23,order.getExpiryDate());
            statement.setString(24,order.getCardType());
            statement.setString(25,order.getLocale());
            statement.executeUpdate();
            OrderService orderService=new OrderService();
            for (int i = 0; i <order.getLineItems().size() ; i++) {
                orderService.insertLineItem(order.getOrderId(),order.getLineItems().get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> selectOrder(String userid) {
        List<Order> orderList=new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT);
            statement.setString(1,userid);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt(1));
                order.setUsername(resultSet.getString(2));
                order.setOrderDate(resultSet.getString(3));
                order.setShipAddress1(resultSet.getString(4));
                order.setShipAddress2(resultSet.getString(5));
                order.setShipCity(resultSet.getString(6));
                order.setShipState(resultSet.getString(7));
                order.setShipZip(resultSet.getString(8));
                order.setShipCountry(resultSet.getString(9));
                order.setBillAddress1(resultSet.getString(10));
                order.setBillAddress2(resultSet.getString(11));
                order.setBillCity(resultSet.getString(12));
                order.setBillState(resultSet.getString(13));
                order.setBillZip(resultSet.getString(14));
                order.setBillCountry(resultSet.getString(15));
                order.setCourier(resultSet.getString(16));
                order.setTotalPrice(resultSet.getBigDecimal(17));
                order.setBillToFirstName(resultSet.getString(18));
                order.setBillToLastName(resultSet.getString(19));
                order.setShipToFirstName(resultSet.getString(20));
                order.setShipToLastName(resultSet.getString(21));
                order.setCreditCard(resultSet.getString(22));
                order.setExpiryDate(resultSet.getString(23));
                order.setCardType(resultSet.getString(24));
                order.setLocale(resultSet.getString(25));
                OrderService orderService=new OrderService();
                List<LineItem> itemList=orderService.selectItem(order.getOrderId());
                order.setLineItems(itemList);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
