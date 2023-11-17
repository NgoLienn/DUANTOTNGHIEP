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
@Table(name = "Carts")
public class Carts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account Account;

    @Temporal(TemporalType.DATE)
    @Column(name = "product_add_date")
    Date ProductAddDate = new Date();

    @OneToMany(mappedBy = "Carts")
    List<Cart_Items> cart_items;

    @OneToMany(mappedBy = "carts")
    List<Payment> payments;
}
