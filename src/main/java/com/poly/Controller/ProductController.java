// package com.poly.Controller;

// import java.math.BigDecimal;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// public class ProductController {
//     @PostMapping("/calculateTotalPrice")
//     @ResponseBody
//     public BigDecimal viewProduct(@RequestParam(value = "quantity") int quantity,
//             @RequestParam(value = "currentPrice") BigDecimal currentPrice) {

//         if (quantity < 0)
//             quantity = 0;
//         BigDecimal totalPrice = currentPrice.multiply(BigDecimal.valueOf(quantity));
//         return totalPrice;
//     }

// }
