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
    private Float Quantity;

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

    @OneToMany(mappedBy = "Products")
    List<Size> size;

    @OneToMany(mappedBy = "product")
    List<Order_Items> orderitems;

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
}