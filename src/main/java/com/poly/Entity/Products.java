package com.poly.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categoryId;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    private String Name;

    @Column(name = "price")
    private Float Price;

    @Column(name = "prices")
    private Float Prices;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String Description;

    @Column(name = "description_an", columnDefinition = "NVARCHAR(MAX)")
    private String Description_an;

    @Column(name = "quantity")
    private Integer Quantity;

    @Column(name = "image", columnDefinition = "VARCHAR(MAX)")
    private String Image;

    @Column(name = "sale")
    private Integer Sale;

    @Column(name = "highlight")
    private Boolean Highlight;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date Create_at = new Date();

    @Column(name = "update_at")
    private Date Update_at;

    @Column(name = "views")
    private Integer Views;

    @Column(name = "average_rating")
    private Float average_rating;

    @OneToMany(mappedBy = "Products")
    List<Size> size;

    @OneToMany(mappedBy = "product")
    List<Order_Items> orderitems;

    @Column(name = "status")
    private Boolean status_prod;

    public int getId() {
        return productId;
    }

    public Categories getCategory() {
        return categoryId;
    }

    public void setCategory(Categories category) {
        this.categoryId = category;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter method for productName
    public String getProductName() {
        return Name;
    }

    // Setter method for productName
    public void setProductName(String Name) {
        this.Name = Name;
    }

    @OneToMany(mappedBy = "productId")
    List<Image_product> iamgeproduct;
}