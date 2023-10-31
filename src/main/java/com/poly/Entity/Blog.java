package com.poly.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Blog")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int blogID;

    @Column(name = "name", columnDefinition = "NVARCHAR(50)")
    private String Name;

    @Column(name = "description", columnDefinition = "NVARCHAR(800)")
    private String Description;

    @Column(name = "blog_date", columnDefinition = "DATE")
    private String Blog_Date;

}
