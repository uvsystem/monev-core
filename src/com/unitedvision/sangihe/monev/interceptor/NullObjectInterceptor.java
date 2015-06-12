package com.unitedvision.sangihe.monev.interceptor;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;

@Aspect
@Component
public class NullObjectInterceptor {

	@AfterReturning(
		pointcut = "execution(public * com.unitedvision.sangihe.monev.service.impl.*.get*(..) throws com.unitedvision.sangihe.monev.exception.EntityNotExistsException)",
		returning = "returnValue")
	public void nullObjectReturned(Object returnValue) throws EntityNotExistsException {

		if (returnValue == null)
			throw new EntityNotExistsException("Data tidak ditemukan");
		
		if ((returnValue instanceof List) && (((List<?>)returnValue).size() <= 0))
			throw new EntityNotExistsException("Data tidak ditemukan");
		
	}
}
