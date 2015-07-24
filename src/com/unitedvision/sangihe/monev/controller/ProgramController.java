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

import com.unitedvision.sangihe.ehrm.connector.RestMessage;
import com.unitedvision.sangihe.monev.entity.Program;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.ProgramService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;

@Controller
@RequestMapping("/program")
public class ProgramController {

	@Autowired
	private ProgramService programService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/{idUnitKerja}")
	@ResponseBody
	public RestMessage tambah(@PathVariable Long idUnitKerja, @RequestBody Program program) throws ApplicationException, PersistenceException {
		programService.simpan(program, idUnitKerja);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public RestMessage hapus(@PathVariable Long id) throws ApplicationException, PersistenceException {
		programService.hapus(id);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EntityRestMessage<Program> get(@PathVariable Long id) throws ApplicationException, PersistenceException {
		Program program = programService.get(id);
		
		return EntityRestMessage.create(program);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ListEntityRestMessage<Program> get() throws ApplicationException, PersistenceException {
		List<Program> daftarProgram = programService.get();
		
		return ListEntityRestMessage.createListProgram(daftarProgram);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/satker/{id}")
	@ResponseBody
	public ListEntityRestMessage<Program> getByUnitKerja(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<Program> daftarProgram = programService.getByUnitKerja(id);
		
		return ListEntityRestMessage.createListProgram(daftarProgram);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cari/{keyword}")
	@ResponseBody
	public ListEntityRestMessage<Program> cari(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<Program> daftarProgram = programService.cari(keyword);
		
		return ListEntityRestMessage.createListProgram(daftarProgram);
	}
}