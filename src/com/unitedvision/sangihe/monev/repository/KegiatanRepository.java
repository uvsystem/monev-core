package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedvision.sangihe.monev.entity.Kegiatan;

public interface KegiatanRepository extends JpaRepository<Kegiatan, Long> {

	List<Kegiatan> findByProgram_UnitKerja_Id(Long id);

	List<Kegiatan> findByProgram_Id(Long id);

	List<Kegiatan> findByNamaContaining(String keyword);

}
