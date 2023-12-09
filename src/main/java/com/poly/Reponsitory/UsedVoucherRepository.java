package com.poly.Reponsitory;

import com.poly.Entity.Account;
import com.poly.Entity.UsedVoucher;
import com.poly.Entity.Voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedVoucherRepository extends JpaRepository<UsedVoucher, Long> {
    UsedVoucher findByAccountAndVoucher(Account account, Voucher voucher);
}
