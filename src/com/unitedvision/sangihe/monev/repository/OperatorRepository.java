package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Skpd;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {

	Operator findByUsername(String username);
	
	List<Operator> findBySkpd(Skpd skpd);
	
}
