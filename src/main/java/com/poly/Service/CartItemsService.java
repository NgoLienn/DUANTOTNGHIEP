package com.poly.Service;

import java.util.List;

import javax.swing.Spring;

import com.poly.Reponsitory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poly.Entity.Cart_Items;
import com.poly.Entity.Carts;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;

@Service
public class CartItemsService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public Carts createCart(Carts cart) {
        return cartRepository.save(cart);
    }


}




