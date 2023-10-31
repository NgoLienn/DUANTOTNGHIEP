package com.poly.Entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "Orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account Account;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status Status;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "delivery_address", columnDefinition = "TEXT")
    private String deliveryAddress;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method", columnDefinition = "VARCHAR(100)")
    private String paymentMethod;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String Phone;
}
