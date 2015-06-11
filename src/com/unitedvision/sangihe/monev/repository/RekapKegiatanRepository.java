package com.unitedvision.sangihe.monev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unitedvision.sangihe.monev.entity.RekapKegiatan;

public interface RekapKegiatanRepository extends JpaRepository<RekapKegiatan, Integer> {

	@Query(value = "SELECT k.id as id, k.nama as nama, k.anggaran as anggaran, k.awal as awal, k.akhir as akhir, k.skpd as skpd, "
			+ "(SELECT SUM(r.anggaran) FROM realisasi r WHERE r.kegiatan = k.id) AS total_realisasi_anggaran,"
			+ "(SELECT DISTINCT r.fisik FROM realisasi r WHERE r.kegiatan = k.id ORDER BY r.tahun, r.bulan) AS total_realisasi_fisik "
			+ "FROM kegiatan k", nativeQuery = true)
	List<RekapKegiatan> rekap();
	
	@Query(value = "SELECT k.id as id, k.nama as nama, k.anggaran as anggaran, k.awal as awal, k.akhir as akhir, k.skpd as skpd, "
			+ "(SELECT SUM(r.anggaran) FROM realisasi r WHERE r.kegiatan = k.id) AS total_realisasi_anggaran,"
			+ "(SELECT DISTINCT r.fisik FROM realisasi r WHERE r.kegiatan = k.id ORDER BY r.tahun, r.bulan) AS total_realisasi_fisik "
			+ "FROM kegiatan k "
			+ "WHERE k.skpd = :idSkpd", nativeQuery = true)
	List<RekapKegiatan> rekap(@Param("idSkpd") Integer idSkpd);
	
}
