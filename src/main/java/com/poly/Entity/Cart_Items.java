package com.poly.Entity;

import java.io.Serializable;

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
@Table(name = "Cart_Items")
public class Cart_Items implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartitemID;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Carts Carts;

    @Column(name = "quantity")
    private Float Quantity;

    @Column(name = "price")
    private Float Price;

    @Column(name = "subtotal")
    private Float Subtotal;
}
