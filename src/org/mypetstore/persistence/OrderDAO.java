package org.mypetstore.persistence;

import org.mypetstore.domain.Order;

import java.util.List;

public interface OrderDAO {
    void insertOrder(Order order);
    List<Order> selectOrder(String userid);
}
