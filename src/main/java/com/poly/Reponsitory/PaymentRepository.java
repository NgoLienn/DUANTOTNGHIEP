package com.poly.Reponsitory;

import com.poly.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("select p from Payment p where p.txnref=?1")
    Payment timma(String txnref);
}
