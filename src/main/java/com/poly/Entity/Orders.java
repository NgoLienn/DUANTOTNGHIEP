package com.poly.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

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

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "order_time")
    private Date orderTime = new Date();

    // Getter và Setter

    // ...

    public Timestamp getOrderTime() {
        return new Timestamp(orderTime.getTime());
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = new Date(orderTime.getTime());
    }

    // ...

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "delivery_address", columnDefinition = "TEXT")
    private String deliveryAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "delivery_time")
    private Date deliveryTime = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "payment_date")
    private Date paymentDate = new Date();

    @Column(name = "payment_method", columnDefinition = "NVARCHAR(100)")
    private String paymentMethod;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String Phone;

    @OneToMany(mappedBy = "Orders")
    List<Order_Items> orderitem;
}
