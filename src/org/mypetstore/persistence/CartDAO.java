package org.mypetstore.persistence;

import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;

public interface CartDAO {
    void insertCart(CartItem cartItem,String userid);
    void updateCart(CartItem cartItem,String userid);
    void deleteCart(CartItem cartItem,String userid);
    void deleteCart(String userid);
    Cart selectCart(String userid);
}
