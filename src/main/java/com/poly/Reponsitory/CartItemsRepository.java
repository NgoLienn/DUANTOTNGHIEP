package com.poly.Reponsitory;

import com.poly.Entity.Cart_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<Cart_Items, Long> {
    @Query("select u from Cart_Items u where  u.cartitemID=?1")
    Cart_Items findByCartitemID(Long Id);

    @Query("select u from Cart_Items u where  u.Carts.Account.UserName=?1")
    List<Cart_Items> findByCartItem(String username);

    @Query("select SUM(u.Subtotal) from Cart_Items u where u.Carts.cartID=?1")
    Long getSum(Long Id);

    @Query("select u from Cart_Items u where u.productId.productId=?1 and u.sizeName=?2")
    Cart_Items findByProductAndSize(Integer product, String size);

}
