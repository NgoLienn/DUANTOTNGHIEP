package com.poly.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Voucher")
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long voucherId;

    private String code;
    private int discount;
    private Date expiry_date;
    private boolean used;
    private int quantity;

    @Temporal(TemporalType.DATE)
    private Date post_date = new Date();

    private String description;
    private String title;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    public boolean isExpired() {
        // Kiểm tra xem voucher đã hết hạn hay chưa
        Date currentDate = new Date();
        return this.expiry_date.before(currentDate);
    }

    public void decreaseQuantity() {
        // Giảm số lượng của voucher sau khi được sử dụng
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
}
