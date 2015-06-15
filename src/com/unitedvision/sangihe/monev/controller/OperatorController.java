package com.unitedvision.sangihe.monev.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.OperatorService;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Controller
@RequestMapping("/operator")
public class OperatorController extends AbstractController {

	@Autowired
	private OperatorService operatorService;

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody RestMessage simpan(Operator operator) throws ApplicationException, PersistenceException {
		operatorService.simpan(operator);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody RestMessage hapus(Operator operator) throws ApplicationException {
		operatorService.hapus(operator);
		
		return RestMessage.success();
	}
	
	@RequestMapping(value = "/skpd/{idSkpd}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Operator> getBySkpd(@PathVariable Integer idSkpd) throws ApplicationException {
		List<Operator> list = operatorService.getBySkpd(idSkpd);
		
		return ListEntityRestMessage.createListOperator(list);
	}
	
	@RequestMapping(value = "/login/{username}", method = RequestMethod.POST)
	public @ResponseBody EntityRestMessage<Operator> login(@PathVariable String username, @RequestParam String password) throws ApplicationException {
		Operator operator = operatorService.get(username);
		
		return EntityRestMessage.create(operator);
	}
}
