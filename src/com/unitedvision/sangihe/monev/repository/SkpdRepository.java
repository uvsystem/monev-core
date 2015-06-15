package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedvision.sangihe.monev.entity.Skpd;

public interface SkpdRepository extends JpaRepository<Skpd, Integer> {

	List<Skpd> findByKodeContainingOrNamaContaining(String keyword);

}
