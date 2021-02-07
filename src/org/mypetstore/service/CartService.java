package org.mypetstore.service;

import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.persistence.CartDAO;
import org.mypetstore.persistence.impl.CartDAOImpl;

public class CartService {
    private CartDAO cartDAO;

    public CartService(){
        cartDAO=new CartDAOImpl();
    }
    public void insertCart(CartItem cartItem,String userid){
        cartDAO.insertCart(cartItem,userid);
    }
    public void updateCart(CartItem cartItem,String userid){
        cartDAO.updateCart(cartItem,userid);
    }
    public void deleteCart(CartItem cartItem,String userid){
        cartDAO.deleteCart(cartItem,userid);
    }
    public Cart selectCart(String userid){
        return cartDAO.selectCart(userid);
    }
    public void deleteCart(String userid){
        cartDAO.deleteCart(userid);
    }
}
