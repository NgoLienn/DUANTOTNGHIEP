package com.poly.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poly.Entity.*;
import com.poly.Reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.Service.ProductService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    CartRepository cartRepon;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountReponsitory accountRepo;

    @Autowired
    private SizeRepository sizeRepo;

    @Autowired
    private SizeProductRepository sizeProductRepo;

    @GetMapping("/cart")
    public String listProducts(Model model, Authentication authentication) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Carts carts = cartRepo.findByCartUser(users);
        if (carts == null) {
            return "redirect:/user/CartNull";
        } else {
            Long subtotal = cartItemsRepo.getSum(carts.getCartID());
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("carts", carts);
        }

//        Cart_Items cartItem = cartItemsRepo.findByCartId(carts.getCartID()); // Thay thế findByCartId bằng phương thức
                                                                             // phù hợp của bạn
//        if (cartItem != null) {
//            Products product = cartItem.getProductId(); // Lấy sản phẩm từ cartItem
//            if (product != null) {
//                int availableQuantity = product.getQuantity(); // Lấy số lượng từ sản phẩm
//                model.addAttribute("availableQuantity", availableQuantity);
//            }
//        } else {
//            // Xử lý khi không tìm thấy cartItem hoặc sản phẩm
//            // Ví dụ: Hiển thị thông báo hoặc xử lý tương ứng
//        }

        return "user/cart";
    }

    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable int productId, Model model, Authentication authentication,
            @RequestParam("size") String size, @RequestParam(value = "soluong", required = false) Integer soluong) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Account account = accountRepo.findByUsername(users);
        Carts cart = cartRepo.findByCartsUsername(account.getAccountID());
        Products product = productRepo.findByProduct(productId);
        Size size1 = sizeRepo.findByProductAndSizeName(productId, size);
        Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(size1.getSizeID());
        if (cart == null) {
            Carts newCart = new Carts();
            newCart.setAccount(account);
            cartRepon.save(newCart);
            // Lấy sản phẩm từ cơ sở dữ liệu bằng productId
            Cart_Items cartItems = new Cart_Items();
            cartItems.setProductId(product);
            cartItems.setCarts(newCart);
            cartItems.setQuantity(soluong);
            cartItems.setPrice(sizeProduct.getPrice());
            cartItems.setSubtotal(soluong * sizeProduct.getPrice());
            cartItems.setSizeName(size);
            cartItemsRepo.save(cartItems);
            return "redirect:/cart";
        } else {
            Cart_Items cart_Items = cartItemsRepo.findByProductAndSize(productId, size);
            if (cart_Items != null) {
                Integer product1 = cart_Items.getProductId().getProductId();
                String size2 = cart_Items.getSizeName();

                cart_Items.setQuantity(cart_Items.getQuantity() + soluong);
                float quantity = cart_Items.getQuantity();
                cart_Items.setSubtotal((float) (quantity * cart_Items.getPrice()));
                cartItemsRepo.save(cart_Items);

            } else {
                Cart_Items cartItems = new Cart_Items();
                cartItems.setProductId(product);
                cartItems.setCarts(cart);
                cartItems.setQuantity(soluong);
                cartItems.setPrice(sizeProduct.getPrice());
                cartItems.setSubtotal(soluong * sizeProduct.getPrice());
                cartItems.setSizeName(size);
                cartItemsRepo.save(cartItems);

//                if (soluong == null || soluong <= 0) {
//                    soluong = 1; // Nếu 'quantity' là null hoặc không hợp lệ, sử dụng giá trị mặc định là 1
//                }
//                // lấy id sản phẩm
//                Products produc = cartItems.getProductId();
//
//                if (produc != null && produc.getQuantity() >= soluong && soluong > 0) {
//
//                    int availableQuantity = produc.getQuantity(); // Số lượng có sẵn từ CSDL
//                    model.addAttribute("availableQuantity", availableQuantity);
//
//                    int updatedQuantity = produc.getQuantity() - soluong;
//                    if (updatedQuantity >= 0) {
//                        // produc.setQuantity(updatedQuantity);
//                        // // productRepo.save(produc);
//                        // System.out.println("Selected quantity: " + quantity);
//                    } else {
//                        model.addAttribute("outOfStockMessage",
//                                "Sản phẩm không đủ số lượng. Vui lòng chọn số lượng ít hơn!");
//                        model.addAttribute("disableBuyButton", true);
//                    }
//
//                } else {
//                    model.addAttribute("outOfStockMessage",
//                            "Sản phẩm không đủ số lượng. Vui lòng chọn số lượng ít hơn!");
//                    model.addAttribute("disableBuyButton", true);
//                }
            }
            return "redirect:/cart";
        }

    }

    @PostMapping("/payment/{productId}")
    public String payment(@PathVariable int productId, Model model, Authentication authentication,
            @RequestParam("size") String size, @RequestParam("soluong") int soluong) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Account account = accountRepo.findByUsername(users);
        Carts cart = cartRepo.findByCartsUsername(account.getAccountID());
        Products product = productRepo.findByProduct(productId);
        Size size1 = sizeRepo.findByProductAndSizeName(productId, size);
        Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(size1.getSizeID());
        if (cart == null) {
            Carts newCart = new Carts();
            newCart.setAccount(account);
            cartRepon.save(newCart);
            // Lấy sản phẩm từ cơ sở dữ liệu bằng productId
            Cart_Items cartItems = new Cart_Items();
            cartItems.setProductId(product);
            cartItems.setCarts(newCart);
            cartItems.setQuantity(soluong);
            cartItems.setPrice(sizeProduct.getPrice());
            cartItems.setSubtotal(soluong * sizeProduct.getPrice());
            cartItems.setSizeName(size);
            cartItemsRepo.save(cartItems);
            return "redirect:/user/payment";
        } else {
            Cart_Items cart_Items = cartItemsRepo.findByProductAndSize(productId, size);
            if (cart_Items != null) {
                Integer product1 = cart_Items.getProductId().getProductId();
                String size2 = cart_Items.getSizeName();

                cart_Items.setQuantity(cart_Items.getQuantity() + soluong);
                float quantity = cart_Items.getQuantity();
                cart_Items.setSubtotal((float) (quantity * cart_Items.getPrice()));
                cartItemsRepo.save(cart_Items);

            } else {
                Cart_Items cartItems = new Cart_Items();
                cartItems.setProductId(product);
                cartItems.setCarts(cart);
                cartItems.setQuantity(soluong);
                cartItems.setPrice(sizeProduct.getPrice());
                cartItems.setSubtotal(soluong * sizeProduct.getPrice());
                cartItems.setSizeName(size);
                cartItemsRepo.save(cartItems);
            }
            return "redirect:/user/payment";
        }

    }

    @GetMapping("cart/remove/{cartitemID}")
    public String remove(@PathVariable("cartitemID") Long Id) {
        Cart_Items cartItems = cartItemsRepo.findByCartitemID(Id);
        cartItemsRepo.delete(cartItems);
        return "redirect:/cart";
    }

    @GetMapping("cart/updateCartItems")
    public String updateQuantity(@RequestParam(value = "cartitemID", defaultValue = "") long cartitemID,
            HttpServletRequest httpServletRequest, Model model, Authentication authentication) {

        Cart_Items cartItem = cartItemsRepo.findByCartitemID(cartitemID);
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Carts carts = cartRepo.findByCartUser(users);
        model.addAttribute("carts", carts);
        int soluong = cartItem.getQuantity() - 1;
        if (soluong < 1) {

        } else {
            cartItem.setQuantity(soluong);
            cartItem.setSubtotal(cartItem.getPrice() * soluong);
        }

        cartItemsRepo.save(cartItem);
        List<Cart_Items> cartItems = cartItemsRepo.findByCartItem(users);
        float sum = 0;
        for (Cart_Items carts1 : cartItems) {
            sum += carts1.getSubtotal();
        }
        Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal", sum);
        return "user/cart";
    }

    @GetMapping("cart/updateCartItemss")
    public String updateQuantityAdd(@RequestParam(value = "cartitemID", defaultValue = "") long cartitemID,
            HttpServletRequest httpServletRequest, Model model, Authentication authentication) {
        Cart_Items cartItem = cartItemsRepo.findByCartitemID(cartitemID);
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Carts carts = cartRepo.findByCartUser(users);
        model.addAttribute("carts", carts);

        int soluong = cartItem.getQuantity() + 1;

        cartItem.setQuantity(soluong);
        cartItem.setSubtotal(cartItem.getPrice() * soluong);

        cartItemsRepo.save(cartItem);
        List<Cart_Items> cartItems = cartItemsRepo.findByCartItem(users);
        float sum = 0;
        for (Cart_Items carts1 : cartItems) {
            sum += carts1.getSubtotal();
        }
        Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal", sum);
        return "user/cart";
    }

}
