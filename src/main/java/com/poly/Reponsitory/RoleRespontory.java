package com.poly.Reponsitory;

import com.poly.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRespontory extends JpaRepository<Roles, String> {
    @Query("select u from Roles u where u.id = ?1") //
    Roles findByid(String id);

    @Query("select u from Roles u where u.id = ?1 or u.name = ?2")
    Roles findByidandname(String id, String name);
}
