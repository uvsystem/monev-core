package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;

public interface KegiatanRepository extends JpaRepository<Kegiatan, Integer> {

	List<Kegiatan> findBySkpd(Skpd skpd);

	@Query(value = "SELECT COALESCE(SUM(k.anggaran), 0) FROM Kegiatan k WHERE k.skpd = ?1")
	long summarizeAnggaran(Skpd skpd);

	List<Kegiatan> findByNamaContaining(String keyword);
}
