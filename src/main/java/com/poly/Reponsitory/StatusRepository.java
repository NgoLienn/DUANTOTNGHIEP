package com.poly.Reponsitory;

import com.poly.Entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("Select u from Status u where u.Name=?1")
    Status findByStatusName(String name);

}
