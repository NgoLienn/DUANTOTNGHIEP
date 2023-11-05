package com.poly.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountID;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(100)")
    private String Fullname;

    @Column(name = "username", columnDefinition = "NVARCHAR(100)")
    private String UserName;

    @Column(name = "password", columnDefinition = "NVARCHAR(100)")
    private String Password;

    @Column(name = "address", columnDefinition = "NVARCHAR(MAX)")
    private String Address;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String Phone;

    @Column(name = "avata", columnDefinition = "NVARCHAR(100)")
    private String Avata;

    @Column(name = "token")
    private String token;

    @Column(name = "active")
    private Boolean active;
    @Column(name="resettoken")
    private String resetToken;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;

    private String encrypt(String plaintext) {
        return plaintext;
    }

    public void generateActivationToken() {
        String identifier = UUID.randomUUID().toString();
        long timestamp = new Date().getTime();
        String plaintext = identifier + ":" + timestamp;
        String token = encrypt(plaintext);
        this.token = token;
    }
    public void ResetToken() {
        String identifier = UUID.randomUUID().toString();
        long timestamp = new Date().getTime();
        String plaintext = identifier + ":" + timestamp;
        String token = encrypt(plaintext);
        this.resetToken = token;
    }
}
