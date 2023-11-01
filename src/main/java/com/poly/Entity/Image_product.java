package com.poly.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Image_product")
public class Image_product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_product_id")
    private Long imageproductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products productId;

    @Column(name = "url_img", columnDefinition = "VARCHAR(MAX)")
    private String Url_Img;

    @Column(name = "alt_text", columnDefinition = "NVARCHAR(100)")
    private String Alt_Text;

    public Image_product(String url_img, Products productId) {
        this.Url_Img = url_img;
        this.productId = productId;
    }

    public Long getProduct_id() {
        return productId.getId();
    }

}
