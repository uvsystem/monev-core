package com.unitedvision.sangihe.monev.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Controller
@RequestMapping("/kegiatan")
public class KegiatanController {

	@Autowired
	private KegiatanService kegiatanService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/{idProgram}")
	@ResponseBody
	public RestMessage tambah(@PathVariable Long idProgram, @RequestBody Kegiatan kegiatan) throws ApplicationException, PersistenceException {
		kegiatanService.simpan(kegiatan, idProgram);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public RestMessage hapus(@PathVariable Long id) throws ApplicationException, PersistenceException {
		kegiatanService.hapus(id);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EntityRestMessage<Kegiatan> get(@PathVariable Long id) throws ApplicationException, PersistenceException {
		Kegiatan kegiatan = kegiatanService.get(id);
		
		return EntityRestMessage.create(kegiatan);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ListEntityRestMessage<Kegiatan> get() throws ApplicationException, PersistenceException {
		List<Kegiatan> daftarKegiatan = kegiatanService.get();
		
		return ListEntityRestMessage.createListKegiatan(daftarKegiatan);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/satker/{id}")
	@ResponseBody
	public ListEntityRestMessage<Kegiatan> getByUnitKerja(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<Kegiatan> daftarKegiatan = kegiatanService.getByUnitKerja(id);
		
		return ListEntityRestMessage.createListKegiatan(daftarKegiatan);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/program/{id}")
	@ResponseBody
	public ListEntityRestMessage<Kegiatan> getByProgram(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<Kegiatan> daftarKegiatan = kegiatanService.getByProgram(id);
		
		return ListEntityRestMessage.createListKegiatan(daftarKegiatan);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cari/{keyword}") 
	@ResponseBody
	public ListEntityRestMessage<Kegiatan> cari(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<Kegiatan> daftarKegiatan = kegiatanService.cari(keyword);
		
		return ListEntityRestMessage.createListKegiatan(daftarKegiatan);
	}
}
