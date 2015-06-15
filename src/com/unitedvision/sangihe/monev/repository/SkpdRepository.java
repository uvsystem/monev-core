package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unitedvision.sangihe.monev.entity.Skpd;

public interface SkpdRepository extends JpaRepository<Skpd, Integer> {

	@Query(value = "Select s From Skpd s Where s.nama Like Concat('%', :keyword, '%') Or s.kode Like Concat('%', :keyword, '%')")
	List<Skpd> findByKodeContainingOrNamaContaining(@Param("keyword") String keyword);

}
