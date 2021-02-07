package org.mypetstore.service;

import org.mypetstore.domain.LineItem;
import org.mypetstore.domain.Order;
import org.mypetstore.persistence.LineItemDAO;
import org.mypetstore.persistence.OrderDAO;
import org.mypetstore.persistence.impl.LineItemDAOImpl;
import org.mypetstore.persistence.impl.OrderDAOImpl;

import java.util.List;

public class OrderService {
    OrderDAO orderDAO;
    LineItemDAO lineItemDAO;
    public OrderService(){
        orderDAO=new OrderDAOImpl();
        lineItemDAO=new LineItemDAOImpl();
    }
    public void insertLineItem(int orderid, LineItem lineItem){
        lineItemDAO.insertLineItem(orderid,lineItem);
    }
    public void insertOrder(Order order){
        orderDAO.insertOrder(order);
    }
    public List<Order> selectOrder(String userid){
        return orderDAO.selectOrder(userid);
    }
    public List<LineItem> selectItem(int orderid){
        return lineItemDAO.selectLineItem(orderid);
    }
}
