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

import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.SkpdService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Controller
@RequestMapping("/skpd")
public class SkpdController extends AbstractController {

	@Autowired
	private SkpdService skpdService;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody RestMessage simpan(@RequestBody Skpd skpd) throws ApplicationException, PersistenceException {
		skpdService.simpan(skpd);
		
		return RestMessage.success();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody RestMessage hapus(@RequestBody Skpd skpd) throws ApplicationException {
		skpdService.hapus(skpd);
		
		return RestMessage.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EntityRestMessage<Skpd> get(@PathVariable Integer id) throws ApplicationException {
		Skpd skpd = skpdService.get(id);
		
		return EntityRestMessage.create(skpd);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Skpd> get() throws ApplicationException {
		List<Skpd> list = skpdService.get();
		
		return ListEntityRestMessage.createListSkpd(list);
	}

	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Skpd> search(@PathVariable String keyword) throws ApplicationException {
		List<Skpd> list = skpdService.search(keyword);
		
		return ListEntityRestMessage.createListSkpd(list);
	}
}
