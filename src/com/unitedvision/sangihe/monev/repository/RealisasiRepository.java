package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;

public interface RealisasiRepository extends JpaRepository<Realisasi, Integer> {

	List<Realisasi> findByKegiatan(Kegiatan kegiatan);

	@Query(value = "SELECT COALESCE(SUM(r.anggaran), 0) FROM Realisasi r WHERE r.kegiatan.skpd = ?1")
	long summarizeAnggaran(Skpd skpd);
	
	@Query(value = "SELECT COALESCE(SUM(r.anggaran), 0) FROM Realisasi r WHERE r.kegiatan = ?1")
	long summarizeAnggaran(Kegiatan kegiatan);
}
