package com.unitedvision.sangihe.monev.interceptor;

import javax.persistence.PersistenceException;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryExceptionInterceptor {

	@AfterThrowing(
		pointcut = "execution(public * com.unitedvision.sangihe.monev.repository.*.save(..))",
		throwing = "ex")
	public void errorThrown(PersistenceException ex) throws PersistenceException {
		Throwable cause = ex.getCause();

		if (cause instanceof DataIntegrityViolationException)
			errorThrown((DataIntegrityViolationException)cause);
		if (cause instanceof ConstraintViolationException)
			errorThrown((ConstraintViolationException)cause);
		
		throw ex;
	}

	@AfterThrowing(
		pointcut = "execution(public * com.unitedvision.sangihe.monev.repository.*.save(..))",
		throwing = "ex")
	public void errorThrown(DataIntegrityViolationException ex) throws PersistenceException {
		throw new PersistenceException(ex.getMessage());
	}

	@AfterThrowing(
		pointcut = "execution(public * com.unitedvision.sangihe.monev.repository.*.save(..))",
		throwing = "ex")
	public void errorThrown(ConstraintViolationException ex) throws PersistenceException {
		throw new PersistenceException(ex.getMessage());
	}
}
