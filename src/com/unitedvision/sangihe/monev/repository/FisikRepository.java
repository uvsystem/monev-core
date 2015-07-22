package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedvision.sangihe.monev.entity.Fisik;

public interface FisikRepository extends JpaRepository<Fisik, Long> {

	List<Fisik> findByKegiatan_Id(Long id);

	List<Fisik> findByKegiatan_Program_Id(Long id);

}
