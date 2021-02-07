package org.mypetstore.persistence;

import org.mypetstore.domain.LineItem;

import java.util.List;

public interface LineItemDAO {
    void insertLineItem(int orderid,LineItem lineItem);
    List<LineItem> selectLineItem(int orderid);
}
