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
@Table(name = "Reviews")
public class Reviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewID;

    @ManyToOne
	@JoinColumn(name = "product_id")
	private Products Products;

    @ManyToOne
	@JoinColumn(name = "account_id")
	private Account Account;

    @Column(name = "rating", columnDefinition = "NVARCHAR(50)")
    private String Rating;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String Comment;
}