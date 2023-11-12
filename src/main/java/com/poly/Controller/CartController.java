package com.poly.Controller;

import java.util.List;

import com.poly.Entity.*;
import com.poly.Reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
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
    private  SizeProductRepository sizeProductRepo;

    @GetMapping("/cart")
    public String listProducts(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Carts carts = cartRepo.findByCartUser(username);
        model.addAttribute("carts",carts);
        Long subtotal= cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal",subtotal);

        return "user/cart";
    }

    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable int productId, Model model, HttpServletRequest httpServletRequest,
                            @RequestParam("size") String size, @RequestParam("soluong") int soluong) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepo.findByUsername(username);
        Carts cart = cartRepo.findByCartsUsername(account.getAccountID());
        Products product = productRepo.findByProduct(productId);
        Size size1 = sizeRepo.findByProductAndSizeName(productId,size);
        Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(size1.getSizeID());
        if (cart == null || cart.getCart_items().isEmpty()) {
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
        }
        else {
            Cart_Items cart_Items = cartItemsRepo.findByProductAndSize(productId,size);
            if(cart_Items!=null){
                Integer product1 = cart_Items.getProductId().getProductId();
                String size2 = cart_Items.getSizeName();

                    cart_Items.setQuantity(cart_Items.getQuantity()+soluong);
                    float quantity = cart_Items.getQuantity();
                    cart_Items.setSubtotal((float) (quantity * cart_Items.getPrice()));
                    cartItemsRepo.save(cart_Items);

                }else {
                    Cart_Items cartItems = new Cart_Items();
                    cartItems.setProductId(product);
                    cartItems.setCarts(cart);
                    cartItems.setQuantity(soluong);
                    cartItems.setPrice(sizeProduct.getPrice());
                    cartItems.setSubtotal(soluong * sizeProduct.getPrice());
                    cartItems.setSizeName(size);
                    cartItemsRepo.save(cartItems);
                }


            return "redirect:/cart";
            }

    }
    
    @GetMapping("cart/remove/{cartitemID}")
    public String remove(@PathVariable("cartitemID") Long Id){
        Cart_Items cartItems = cartItemsRepo.findByCartitemID(Id);
        cartItemsRepo.delete(cartItems);
        return "redirect:/cart";
    }
    @GetMapping("cart/updateCartItems")
    public String updateQuantity( @RequestParam(value = "cartitemID",defaultValue = "") long cartitemID,
                                  HttpServletRequest httpServletRequest, Model model){
        Cart_Items cartItem = cartItemsRepo.findByCartitemID(cartitemID);
        String username = httpServletRequest.getRemoteUser();
        Carts carts = cartRepo.findByCartUser(username);
        model.addAttribute("carts",carts);
        Long subtotal= cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal",subtotal);
        int soluong = cartItem.getQuantity()-1;
        cartItem.setQuantity(soluong);
        cartItem.setSubtotal(cartItem.getPrice()*soluong);

        cartItemsRepo.save(cartItem);
        return "user/cart";
    }
    @GetMapping("cart/updateCartItemss")
    public String updateQuantityAdd( @RequestParam(value = "cartitemID",defaultValue = "") long cartitemID,
                                     HttpServletRequest httpServletRequest, Model model){
        Cart_Items cartItem = cartItemsRepo.findByCartitemID(cartitemID);
        String username = httpServletRequest.getRemoteUser();
        Carts carts = cartRepo.findByCartUser(username);
        model.addAttribute("carts",carts);
        Long subtotal= cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal",subtotal);
        int soluong = cartItem.getQuantity()+1;
        cartItem.setQuantity(soluong);
        cartItem.setSubtotal(cartItem.getPrice()*soluong);
        cartItemsRepo.save(cartItem);
        return "user/cart";
    }




}
