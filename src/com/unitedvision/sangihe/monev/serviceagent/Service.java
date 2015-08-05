package com.unitedvision.sangihe.monev.serviceagent;

import com.unitedvision.sangihe.monev.serviceagent.entity.Token;

public interface Service {

	Token create(String username, String password) throws ServiceException;
	
	void lock(String tokenString) throws ServiceException;
	
	Token get(String tokenString) throws ServiceException;

}
