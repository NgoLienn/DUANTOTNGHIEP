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
@Table(name = "Size_Product")
public class Size_Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_product_id")
    private Long sizeproductID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products Products;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size Size;

    @Column(name = "price")
    private Float Price;

}
