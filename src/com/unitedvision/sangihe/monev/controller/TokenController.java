package com.unitedvision.sangihe.monev.controller;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.ehrm.connector.EntityRestMessage;
import com.unitedvision.sangihe.ehrm.connector.RestMessage;
import com.unitedvision.sangihe.ehrm.connector.Service;
import com.unitedvision.sangihe.ehrm.connector.ServiceException;
import com.unitedvision.sangihe.ehrm.connector.entity.Token;
import com.unitedvision.sangihe.monev.exception.ApplicationException;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private Service tokenService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/{nip}")
	@ResponseBody
	public EntityRestMessage<Token> create(@PathVariable String nip) throws ApplicationException, PersistenceException {
		Token token;
		try {
			token = tokenService.create(nip);
			
			return EntityRestMessage.create(token);
		} catch (ServiceException e) {
			throw new ApplicationException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{tokenString}")
	@ResponseBody
	public EntityRestMessage<Token> get(@PathVariable String tokenString) throws ApplicationException, PersistenceException {
		Token token;
		try {
			token = tokenService.get(tokenString);
			
			return EntityRestMessage.create(token);
		} catch (ServiceException e) {
			throw new ApplicationException(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{tokenString}")
	@ResponseBody
	public RestMessage lock(@PathVariable String tokenString) throws ApplicationException, PersistenceException {
		try {
			tokenService.lock(tokenString);
			
			return RestMessage.success();
		} catch (ServiceException e) {
			throw new ApplicationException(e);
		}
	}
}
