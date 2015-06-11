package com.unitedvision.sangihe.monev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.RealisasiService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Controller
@RequestMapping("/realisasi")
public class RealisasiController extends AbstractController {

	@Autowired
	private RealisasiService realisasiService;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody EntityRestMessage<Realisasi> simpan(@RequestBody Realisasi realisasi) throws ApplicationException {
		realisasiService.simpan(realisasi);
		
		return EntityRestMessage.create(realisasi);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody RestMessage hapus(@RequestBody Realisasi realisasi) throws ApplicationException {
		realisasiService.hapus(realisasi);
		
		return RestMessage.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EntityRestMessage<Realisasi> get(@PathVariable Integer id) throws ApplicationException {
		Realisasi realisasi = realisasiService.get(id);
		
		return EntityRestMessage.create(realisasi);
	}
	
	@RequestMapping(value = "/kegiatan/{idKegiatan}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Realisasi> getByKegiatan(@PathVariable Integer idKegiatan) throws ApplicationException {
		List<Realisasi> list = realisasiService.getByKegiatan(idKegiatan);
		
		return ListEntityRestMessage.createListRealisasi(list);
	}
	
	public @ResponseBody ListEntityRestMessage<Realisasi> get() throws ApplicationException {
		List<Realisasi> list = realisasiService.get();
		
		return ListEntityRestMessage.createListRealisasi(list);
	}
}
