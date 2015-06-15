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

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.CredentialException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.service.OperatorService;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;
import com.unitedvision.sangihe.monev.util.UsernamePasswordMessage;

@Controller
@RequestMapping("/operator")
public class OperatorController extends AbstractController {

	@Autowired
	private OperatorService operatorService;

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody RestMessage simpan(@RequestBody Operator operator) throws ApplicationException, PersistenceException {
		operatorService.simpan(operator);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody RestMessage hapus(@RequestBody Operator operator) throws ApplicationException {
		operatorService.hapus(operator);
		
		return RestMessage.success();
	}
	
	@RequestMapping(value = "/skpd/{idSkpd}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Operator> getBySkpd(@PathVariable Integer idSkpd) throws ApplicationException {
		List<Operator> list = operatorService.getBySkpd(idSkpd);
		
		return ListEntityRestMessage.createListOperator(list);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Operator> get() throws ApplicationException {
		List<Operator> list = operatorService.get();
		
		return ListEntityRestMessage.createListOperator(list);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody RestMessage login(@RequestBody UsernamePasswordMessage message) throws ApplicationException, PersistenceException {
		Operator operator;
		
		try {
			operator = operatorService.get(message.getUsername());
		} catch(EntityNotExistsException e) {
			throw new CredentialException("Credential Error");
		}
		
		if (!operator.getPassword().equals(message.getPassword()))
			throw new CredentialException("Credential Error");
		
		return RestMessage.create(operator);
	}

	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
	public @ResponseBody ListEntityRestMessage<Operator> search(@PathVariable String keyword) throws ApplicationException {
		List<Operator> list = operatorService.search(keyword);
		
		return ListEntityRestMessage.createListOperator(list);
	}
}
