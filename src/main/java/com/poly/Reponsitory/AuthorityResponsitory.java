package com.poly.Reponsitory;

import com.poly.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityResponsitory extends JpaRepository<Authority,Integer> {
}
