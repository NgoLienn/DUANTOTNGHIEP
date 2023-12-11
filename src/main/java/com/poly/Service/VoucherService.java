// package com.poly.Service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.poly.Entity.Voucher;
// import com.poly.Reponsitory.VoucherRepository;

// @Service
// public class VoucherService {

//     @Autowired
//     private VoucherRepository voucherRepository;

//     public Voucher getVoucherByCode(String code) {
//         return voucherRepository.findByCode(code);
//     }

//     public boolean useVoucher(String code) {
//         Voucher voucher = voucherRepository.findByCode(code);

//         if (voucher != null && !voucher.isUsed() && voucher.getQuantity() > 0) {
//             voucher.setUsed(true);
//             voucher.setQuantity(voucher.getQuantity() - 1);
//             voucherRepository.save(voucher);
//             return true; // Đánh dấu voucher đã sử dụng thành công
//         }

//         return false; // Không thể sử dụng voucher
//     }

//     public void updateVoucher(Voucher voucher) {
//         voucherRepository.save(voucher);
//     }
// }
