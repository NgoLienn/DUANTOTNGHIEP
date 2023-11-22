package com.poly.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankcode;
    private String createdate;
    private String currcode;
    private String status;
    private long amount;
    private String txnref;
    //    private int course_id;
    @ManyToOne
    @JoinColumn(name ="cart_id")
    private Carts carts;
    private String username;

    @OneToMany(mappedBy = "payment")
    private Set<Transactionentity> transaction;

}