package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Item;
import org.mypetstore.domain.LineItem;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.persistence.LineItemDAO;
import org.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    public final static String INSERT= "INSERT INTO LINEITEM VALUES (?, ?, ?, ?, ?)";
    public final static String SELECT= "SELECT LINENUM,ITEMID,QUANTITY,UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    @Override
    public void insertLineItem(int orderid,LineItem lineItem) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1,orderid);
            statement.setInt(2,lineItem.getLineNumber());
            statement.setString(3, lineItem.getItemId());
            statement.setInt(4,lineItem.getQuantity());
            statement.setBigDecimal(5,lineItem.getUnitPrice());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LineItem> selectLineItem(int orderid) {
        List<LineItem> itemList=new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT);
            statement.setInt(1,orderid);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                LineItem lineItem=new LineItem();
                lineItem.setOrderId(orderid);
                lineItem.setLineNumber(resultSet.getInt(1));
                lineItem.setItemId(resultSet.getString(2));
                lineItem.setQuantity(resultSet.getInt(3));
                lineItem.setUnitPrice(resultSet.getBigDecimal(4));
                CatalogService catalogService = new CatalogService();
                Item item=catalogService.getItem(lineItem.getItemId());
                lineItem.setItem(item);
                itemList.add(lineItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
