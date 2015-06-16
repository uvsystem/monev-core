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
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Controller
@RequestMapping("/kegiatan")
public class KegiatanController extends AbstractController {

	@Autowired
	private KegiatanService kegiatanService;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody RestMessage simpan(@RequestBody Kegiatan kegiatan) throws ApplicationException, PersistenceException {
		kegiatanService.simpan(kegiatan);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody RestMessage hapus(@RequestBody Kegiatan kegiatan) throws ApplicationException {
		kegiatanService.hapus(kegiatan);
		
		return RestMessage.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EntityRestMessage<Kegiatan> get(@PathVariable Integer id) throws ApplicationException {
		Kegiatan kegiatan = kegiatanService.get(id);
		
		return EntityRestMessage.create(kegiatan);
	}
	
	@RequestMapping(value = "/skpd/{idSkpd}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Kegiatan> getBySkpd(@PathVariable Integer idSkpd) throws ApplicationException {
		List<Kegiatan> list = kegiatanService.getBySkpd(idSkpd);
		
		return ListEntityRestMessage.createListKegiatan(list);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Kegiatan> get() throws ApplicationException {
		List<Kegiatan> list = kegiatanService.get();
		
		return ListEntityRestMessage.createListKegiatan(list);
	}
	
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Kegiatan> search(@PathVariable String keyword) throws ApplicationException {
		List<Kegiatan> list = kegiatanService.search(keyword);
		
		return ListEntityRestMessage.createListKegiatan(list);
	}
	
	@RequestMapping(value = "/rekap/skpd/{idSkpd}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<RekapKegiatan> rekap(@PathVariable Integer idSkpd) {
		List<RekapKegiatan> list = kegiatanService.rekap(idSkpd);
		
		return ListEntityRestMessage.createListRekapKegiatan(list);
	}
	
	@RequestMapping(value = "/rekap", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<RekapKegiatan> rekap() {
		List<RekapKegiatan> list = kegiatanService.rekap();
		
		return ListEntityRestMessage.createListRekapKegiatan(list);
	}
}
