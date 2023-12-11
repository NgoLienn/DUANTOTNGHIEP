// package com.poly.Reponsitory;

// import com.poly.Entity.Voucher;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface VoucherRepository extends JpaRepository<Voucher, Long> {
//     @Query("select u from Voucher u where u.code = ?1")
//     Voucher finByCodee(String code);

//     Voucher findByCode(String code);
// }
