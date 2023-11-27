package com.poly.Reponsitory;

import com.poly.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityResponsitory extends JpaRepository<Authority,Integer> {
    @Query("select u from Authority u where u.account.UserName=?1")
    Authority findByUserName(String UserName);
}
