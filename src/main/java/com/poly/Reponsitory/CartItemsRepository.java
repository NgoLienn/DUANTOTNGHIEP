package com.poly.Reponsitory;

import com.poly.Entity.Cart_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<Cart_Items, Long> {

}
