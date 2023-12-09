package com.poly.Controller;

import com.poly.Config.Config;
import com.poly.Entity.*;
import com.poly.Reponsitory.*;
import com.poly.Service.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class PaymentController {
    @Autowired
    CartRepository cartRepo;

    @Autowired
    AccountReponsitory accountRepo;

    @Autowired
    CartItemsRepository cartItemsRepo;

    @Autowired
    OrdersReposiotry ordersRepo;

    @Autowired
    OrderItemRepository orderItemRepo;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    VoucherService voucherService;

    @Autowired
    VoucherRepository voucherRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/payment")
    public String ViewProfile(@RequestParam(value = "voucherCode", defaultValue = "") String voucherCode, Model model,
            Authentication authentication) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Account account = accountRepo.findByUsername(users);
        model.addAttribute("account", account);
        Carts carts = cartRepo.findByCartUser(users);
        model.addAttribute("cart", carts);
        Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal", subtotal);

        List<Voucher> voucherr = voucherRepo.findAll();
        model.addAttribute("voucher", voucherr);

        int pricevou = 0;

        // Tạo một đối tượng BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (voucherCode.equals("")) {

        } else {
            Voucher voucher = voucherRepo.finByCodee(voucherCode);

            if (voucher == null) {

            } else {
                // So sánh mật khẩu từ người dùng với mật khẩu đã được mã hóa
                // boolean passwordsMatch = encoder.matches(voucherCode, voucher.getCode());

                System.out.println(" 1");

                if (voucher != null && !voucher.isUsed() && voucher.getQuantity() > 0) {

                    pricevou = voucher.getDiscount();

                    model.addAttribute("isvoucher", voucherCode);
                }
            }

        }

        model.addAttribute("pricevoucher", pricevou);

        return "user/payment_method";
    }

    // @PostMapping("/applyVoucher")
    // public ResponseEntity<?> applyVoucher(@RequestParam("voucherCode") String
    // voucherCode, Model model,
    // Authentication authentication) {
    // String users = "";
    // if (authentication instanceof OAuth2AuthenticationToken) {
    // OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
    // authentication;
    // OAuth2User user = oauthToken.getPrincipal();
    // users = user.getAttribute("email");
    // } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
    // users = authentication.getName();
    // }
    // Account account = accountRepo.findByUsername(users);
    // model.addAttribute("account", account);

    // // Lấy thông tin giỏ hàng của người dùng
    // Carts carts = cartRepo.findByCartUser(users);
    // model.addAttribute("cart", carts);

    // // Xác nhận thanh toán thành công
    // boolean paymentSuccessful = true; // Giả định thanh toán thành công

    // if (paymentSuccessful) {
    // // Lấy thông tin và kiểm tra voucher
    // Voucher voucher = voucherService.getVoucherByCode(voucherCode);
    // if (voucher != null && !voucher.isUsed() && voucher.getQuantity() > 0) {
    // // Tính tổng giá trị đơn hàng
    // float subtotal = cartItemsRepo.getSum(carts.getCartID());

    // // Reset giá trị của cart items nếu đã áp dụng voucher trước đó
    // resetCartItems(carts.getCart_items());

    // // Áp dụng giảm giá từ voucher vào tổng giá trị đơn hàng
    // float discountAmount = voucher.getDiscount();
    // float newSubtotal = subtotal - discountAmount;
    // if (newSubtotal < 0) {
    // newSubtotal = 0; // Đảm bảo giá trị không âm
    // }
    // model.addAttribute("newSubtotal", newSubtotal); // Truyền giá trị sau khi áp
    // dụng voucher vào view

    // Map<String, Object> response = new HashMap<>();
    // response.put("newSubtotal", newSubtotal);

    // // Lấy danh sách các cart item
    // List<Cart_Items> cartItems = carts.getCart_items();

    // // Tính tổng giá trị hiện tại của giỏ hàng
    // float currentTotal = 0;
    // for (Cart_Items cartItem : cartItems) {
    // currentTotal += cartItem.getSubtotal();
    // }

    // // Tính tổng giá trị mới sau khi áp dụng voucher
    // float newTotal = currentTotal - discountAmount;

    // if (newTotal < 0) {
    // newTotal = 0; // Đảm bảo giá trị không âm
    // }
    // // Tính tỷ lệ giảm giá cho mỗi cart item
    // float discountPerCartItem = discountAmount / cartItems.size();

    // // Cập nhật tổng giá trị mới vào từng cart item trong giỏ hàng
    // for (Cart_Items cartItem : cartItems) {
    // cartItem.setSubtotal(cartItem.getSubtotal() - discountPerCartItem);
    // cartItemsRepo.save(cartItem); // Lưu thay đổi vào CSDL
    // }

    // // Update account status for voucher usage
    // account.setUsed_voucher(true);
    // accountRepo.save(account);

    // model.addAttribute("voucherCode", voucherCode);

    // return ResponseEntity.ok(newSubtotal);
    // } else {
    // return ResponseEntity.badRequest()
    // .body("Không thể thanh toán. Voucher không hợp lệ hoặc đã được sử dụng.");
    // }
    // } else {
    // Map<String, Object> response = new HashMap<>();
    // response.put("error", "Voucher không hợp lệ hoặc đã được sử dụng.");
    // // Giữ nguyên tổng giá trị đơn hàng nếu không áp dụng voucher
    // float subtotal = cartItemsRepo.getSum(carts.getCartID());
    // model.addAttribute("newSubtotal", subtotal);

    // return ResponseEntity.badRequest().body("Voucher không hợp lệ hoặc đã được sử
    // dụng.");
    // }
    // }

    // // Hàm reset giá trị của các cart items
    // private void resetCartItems(List<Cart_Items> cartItems) {
    // for (Cart_Items cartItem : cartItems) {
    // cartItem.setSubtotal(cartItem.getProductId().getPrice() *
    // cartItem.getQuantity());
    // cartItemsRepo.save(cartItem); // Lưu thay đổi vào CSDL
    // }
    // }

    @PostMapping("/payment")
    public String payment(Model model, Authentication authentication, HttpServletRequest req,
            @RequestParam("payment") String payment,
            HttpServletResponse resp) throws IOException {
        String selectedProvince = req.getParameter("tinh");
        String selectedDistrict = req.getParameter("huyen");
        String selectedWard = req.getParameter("xa");
        String selectedChitiet = req.getParameter("chitiet");
        String selectedName = req.getParameter("ten");
        String selectedPhone = req.getParameter("phone");
        String SelectedAddress = selectedChitiet + ", " + selectedWard + ", " + selectedDistrict + ", "
                + selectedProvince;
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        if (payment.equals("true")) {
            // Xác nhận thanh toán thành công
            boolean paymentSuccessful = true; // Giả định thanh toán thành công

            if (paymentSuccessful) {
                Account account = accountRepo.findByUsername(users);
                Carts carts = cartRepo.findByCartUser(users);
                float subtotal = cartItemsRepo.getSum(carts.getCartID());

                Status status = new Status();
                status.setStatusID(1L);

                account.setAddress(SelectedAddress);
                account.setFullname(selectedName);
                account.setPhone(selectedPhone);

                Orders orders = new Orders();
                orders.setAccount(account);
                orders.setStatus(status);
                orders.setDeliveryAddress(account.getAddress());
                orders.setPhone(account.getPhone());
                orders.setPaymentMethod("Thanh toán khi nhận hàng");
                orders.setTotalAmount(subtotal + 30000);
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

                // Update voucher status and quantity
                String voucherCode = req.getParameter("voucherCode");
                Voucher voucher = voucherService.getVoucherByCode(voucherCode);
                if (voucher != null && !voucher.isUsed() && voucher.getQuantity() > 0) {
                    // voucher.setUsed(true);
                    voucher.setQuantity(voucher.getQuantity() - 1);
                    voucherService.updateVoucher(voucher);

                    account.setUsed_voucher(true);
                    accountRepo.save(account);
                }

                cartItemsRepo.deleteAll(carts.getCart_items());
                cartRepo.delete(carts);
            }

            return "redirect:/user/confirmation";
        } else {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_CurrCode = "VND";
            String vnp_OrderInfo = req.getParameter("oder");
            String orderType = "1";
            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(req);
            String vnp_TmnCode = Config.vnp_TmnCode;
            int amount = Integer.parseInt(req.getParameter("vnp_Amount")) * 100;
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);
            String bank_code = req.getParameter("bankcode");
            if (bank_code != null && !bank_code.isEmpty())
                vnp_Params.put("vnp_BankCode", bank_code);
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            String locate = req.getParameter("language");
            if (locate != null && !locate.isEmpty())
                vnp_Params.put("vnp_Locale", locate);
            else
                vnp_Params.put("vnp_Locale", "vn");

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            Carts carts = new Carts();
            carts.setCartID(Long.valueOf(vnp_OrderInfo));
            Payment paymentEntity = new Payment();
            paymentEntity.setAmount(amount / 100);
            paymentEntity.setStatus("NO");
            paymentEntity.setTxnref(vnp_TxnRef);
            paymentEntity.setCreatedate(vnp_CreateDate);
            paymentEntity.setBankcode(bank_code);
            paymentEntity.setCurrcode(vnp_CurrCode);
            paymentEntity.setUsername(users);
            paymentEntity.setCarts(carts);
            paymentEntity.setAddress(SelectedAddress);
            paymentRepository.save(paymentEntity);

            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String hr = Config.vnp_HashSecret;
            System.out.println(hr);
            String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
            System.out.println(vnp_SecureHash);
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            resp.setStatus(HttpServletResponse.SC_FOUND);
            resp.setHeader("Location", paymentUrl);
            return "redirect:" + paymentUrl;
        }
    }

}
//