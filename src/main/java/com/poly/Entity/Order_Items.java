package com.poly.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_Items")
public class Order_Items implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderitemID;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders Orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "size_product_id")
    private String Size_Product;

    @Column(name = "quantity")
    private int Quantity;

    @Column(name = "subtotal")
    private Float Subtotal;

    @Column(name = "price")
    private Float Price;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    private String Name;

    @Column(name = "evaluate")
    private Boolean evaluate;
}
