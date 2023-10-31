package com.poly.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

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

    @Column(name = "create_at")
    private Date Create_at;

    @Column(name = "update_at")
    private Date Update_at;

    @Column(name = "views")
    private Integer Views;

    public Long getId() {
        return productId;
    }
}
