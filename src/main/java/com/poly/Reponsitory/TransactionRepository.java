package com.poly.Reponsitory;

import com.poly.Entity.Transactionentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactionentity,Integer> {
}
