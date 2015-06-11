package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unitedvision.sangihe.monev.entity.RekapSkpd;

public interface RekapSkpdRepository extends JpaRepository<RekapSkpd, Integer> {

	@Query(value = "SELECT s.id as id, s.kode as kode, s.nama as nama, "
			+ "(SELECT SUM(k.anggaran) FROM kegiatan k WHERE k.skpd = s.id) AS total_anggaran, "
			+ "(SELECT SUM(r.anggaran) FROM realisasi r WHERE r.kegiatan = k.id) AS total_realisasi_anggaran, "
			+ "(SELECT SUM(r.fisik) FROM realisasi r WHERE r.kegiatan = k.id) AS total_realisasi_fisik, "
			+ "(SELECT COUNT(*) FROM kegiatan where k.skpd = s.id) AS jumlah_kegiatan "
			+ "FROM skpd s INNER JOIN kegiatan k", nativeQuery = true)
	List<RekapSkpd> rekap();
	
}
