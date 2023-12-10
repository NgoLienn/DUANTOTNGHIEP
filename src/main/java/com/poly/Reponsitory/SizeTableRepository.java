package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.Entity.TableSize;

public interface SizeTableRepository extends JpaRepository<TableSize, Integer> {

    @Query("select t from TableSize t where t.sizeName =:name")
    TableSize findByNameSize(String name);
}
