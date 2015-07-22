package com.unitedvision.sangihe.monev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Fisik;
import com.unitedvision.sangihe.monev.entity.Foto;
import com.unitedvision.sangihe.monev.repository.FisikRepository;

@Service
@Transactional(readOnly = true)
public class FisikServiceImpl implements FisikService {

	@Autowired
	private FisikRepository fisikRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Fisik simpan(Fisik fisik) {
		return fisikRepository.save(fisik);
	}

	@Override
	@Transactional(readOnly = false)
	public Fisik tambahFoto(Long id, String lokasiFoto) {
		Fisik fisik = get(id);
		fisik.addFoto(new Foto(lokasiFoto));
		
		return simpan(fisik);
	}

	@Override
	@Transactional(readOnly = false)
	public Fisik tambahFoto(Long id, List<Foto> daftarFoto) {
		Fisik fisik = get(id);
		
		for (Foto foto : daftarFoto)
			fisik.addFoto(foto);
		
		return simpan(fisik);
	}

	@Override
	@Transactional(readOnly = false)
	public void hapus(Long id) {
		fisikRepository.delete(id);
	}

	@Override
	public Fisik get(Long id) {
		return fisikRepository.findOne(id);
	}

	@Override
	public List<Fisik> getByKegiatan(Long id) {
		return fisikRepository.findByKegiatan_Id(id);
	}

	@Override
	public List<Fisik> getByProgram(Long id) {
		return fisikRepository.findByKegiatan_Program_Id(id);
	}

}
