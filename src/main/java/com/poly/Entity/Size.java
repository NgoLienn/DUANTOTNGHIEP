package com.poly.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Size")
public class Size implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long sizeID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products Products;

    @OneToMany(mappedBy = "Size")
    List<Size_Product> sizeProducts;

    @Column(name = "name", columnDefinition = "NVARCHAR(50)")
    private String Name;
}
