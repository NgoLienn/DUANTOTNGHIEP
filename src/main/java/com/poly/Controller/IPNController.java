package com.poly.Controller;

import com.poly.Entity.*;
import com.poly.Reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@RestController
@Controller
@RequestMapping("/return")
public class IPNController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountReponsitory accountRepo;
    @Autowired
    private OrdersReposiotry ordersRepo;
    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping
    public String showRespond(
            @RequestParam Long vnp_Amount,
            @RequestParam String vnp_BankCode,
            @RequestParam(required = false) String vnp_BankTranNo,
            @RequestParam String vnp_CardType,
            @RequestParam String vnp_OrderInfo,
            @RequestParam String vnp_PayDate,
            @RequestParam String vnp_ResponseCode,
            @RequestParam String vnp_TmnCode,
            @RequestParam String vnp_TransactionNo,
            @RequestParam String vnp_TransactionStatus,
            @RequestParam String vnp_TxnRef,
            @RequestParam String vnp_SecureHash,

            HttpServletResponse resp, Model m, HttpServletRequest request) {

        Payment paymentEntity = paymentRepository.timma(vnp_TxnRef);
        String paymentTxnRef = paymentEntity.getTxnref();
        if(vnp_TransactionStatus.equals("02")){
            m.addAttribute("error","Giao dich không thành công do hủy giao dịch");
            return "redirect:/";
        }
        if (!vnp_TxnRef.equals(paymentTxnRef)) {
            m.addAttribute("error","Tham chiếu giao dịch không khớp, nếu có bất kỳ thiếu nại lh Email:crouseonline@gmail.com");
            return "user/index";
        } else if (paymentEntity.getStatus().equals("YES")) {
            m.addAttribute("error","Giao dich Đã được thanh toán vui lòng không lạm dung");
            return "user/index";
        }

        if (vnp_TransactionStatus.equals("00")) {
            Transactionentity transactionEntity = new Transactionentity();
            transactionEntity.setTransactionno(vnp_TransactionNo);
            transactionEntity.setTxnref(vnp_TxnRef);
            transactionEntity.setAmount(vnp_Amount/100);
            transactionEntity.setTransactionstatus(vnp_TransactionStatus);
            transactionEntity.setBanktranno(vnp_BankTranNo);
            transactionEntity.setPayment(paymentEntity);
            transactionRepository.save(transactionEntity);
            paymentEntity.setBankcode(vnp_BankCode);
            paymentEntity.setStatus("YES");
            paymentRepository.save(paymentEntity);

            //lưu vào order
            String username = request.getRemoteUser();
            Account account = accountRepo.findByUsername(username);
            Carts carts = cartRepo.findByCartUser(username);
            float subtotal = cartItemsRepo.getSum(carts.getCartID());
            Status status = new Status();
            status.setStatusID(1L);
            Orders orders = new Orders();
            orders.setAccount(account);
            orders.setStatus(status);
            orders.setDeliveryAddress(account.getAddress());
            orders.setPhone(account.getPhone());
            orders.setPaymentMethod("Thanh toán online");
            orders.setTotalAmount(subtotal);
            ordersRepo.save(orders);

            for (Cart_Items cartItems : carts.getCart_items()) {
                Products product = new Products();
                product.setProductId(cartItems.getProductId().getProductId());
                Order_Items orderItems = new Order_Items();
                orderItems.setOrders(orders);
                orderItems.setSize_Product(cartItems.getSizeName());
                orderItems.setQuantity(cartItems.getQuantity());
                orderItems.setPrice(cartItems.getPrice());
                orderItems.setName(cartItems.getProductId().getName());
                orderItems.setSubtotal(cartItems.getSubtotal());
                orderItems.setProduct(product);
                orderItemRepo.save(orderItems);
                System.out.println(cartItems.getQuantity());
            }
            cartItemsRepo.deleteAll(carts.getCart_items());
            cartRepo.delete(carts);
        }

        return "redirect:/user/confirmation";
    }




}