// package com.poly.DTO;

// import javax.validation.constraints.Digits;
// import javax.validation.constraints.Min;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.NotNull;

// import com.poly.Entity.Categories;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class ProductDTO {

//     @NotEmpty(message = "Tên không được để trống")
//     @NotBlank(message = "Tên Sản phẩm không được bỏ trống")
//     private String name;

//     @NotNull(message = "Giá Cũ không được bỏ trống")
//     @Digits(integer = 10, fraction = 2, message = "Giá Cũ phải là số và có tối đa 2 chữ số sau dấu phẩy")
//     private Float prices;

//     @NotNull(message = "Giá không được bỏ trống")
//     @Digits(integer = 10, fraction = 2, message = "Giá Cũ phải là số và có tối đa 2 chữ số sau dấu phẩy")
//     private Float price;

//     @Min(value = 1, message = "Số Lượng phải lớn hơn hoặc bằng 1")
//     private int quantity;

//     @NotBlank(message = "Mô Tả Ẩn không được bỏ trống")
//     private String description_an;

//     @NotBlank(message = "Mô Tả không được bỏ trống")
//     private String description;

//     @NotNull(message = "Thể Loại không được bỏ trống")
//     private Categories categoryId; // Assuming Category is another entity

//     @NotBlank(message = "Hình Ảnh Đại Diện Sản Phẩm không được bỏ trống")
//     private String image;

// }
