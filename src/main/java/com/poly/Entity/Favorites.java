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
@Table(name = "Favorites")
public class Favorites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long categoryID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account Account;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products Products;

    @Column(name = "date_favorite")
    private Date DateFavorite;
}
