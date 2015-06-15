package com.unitedvision.sangihe.monev.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.ListEntityRestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage;

@Aspect
@Component
public class MessageConverter {
	
	@Around("execution(public com.unitedvision.sangihe.monev.util.RestMessage com.unitedvision.sangihe.monev.controller.*.*(..) throws com.unitedvision.sangihe.monev.exception.ApplicationException, javax.persistence.PersistenceException)")
	public @ResponseBody RestMessage process(ProceedingJoinPoint jointPoint) {
		try {
			return (RestMessage) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			
			String message = e.getMessage();
			if (message == null)
				message = e.toString();
			
			return RestMessage.error(new Exception(createMessage(message)));
		}
	}

	@Around("execution(public com.unitedvision.sangihe.monev.util.EntityRestMessage com.unitedvision.sangihe.monev.controller.*.*(..) throws com.unitedvision.sangihe.monev.exception.ApplicationException)")
	public @ResponseBody EntityRestMessage<?> getEntity(ProceedingJoinPoint jointPoint) {
		try {
			return (EntityRestMessage<?>) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			
			return EntityRestMessage.entityError(new Exception(e.getMessage()));
		}
	}
	
	@Around("execution(public com.unitedvision.sangihe.monev.util.ListEntityRestMessage com.unitedvision.sangihe.monev.controller.*.*(..) throws com.unitedvision.sangihe.monev.exception.ApplicationException)")
	public @ResponseBody ListEntityRestMessage<?> getListEntity(ProceedingJoinPoint jointPoint) {
		try {
			return (ListEntityRestMessage<?>) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			
			return ListEntityRestMessage.listEntityError(new Exception(e.getMessage()));
		}
	}
	
	
	private String createMessage(String key) {
		System.out.println( String.format( "LOG: %s", key) );
		
		if (key.equals("Credential Error")) {
			return "Username atau password salah";
		} else if (key.contains("Kesalahan")) {
			return key;
		} else if (key.contains("username")) {
			return "Username yang anda masukkan sudah digunakan";
		} else if (key.contains("kode")) {
			return "Kode yang anda masukkan sudah digunakan";
		} else if (key.contains("nama")) {
			return "Nama yang anda masukkan sudah digunakan";
		} else if (key.contains("skpd")) {
			return "Kegiatan yang anda masukan sudah terdata";
		} else if (key.contains("kegiatan")) {
			return "Realisasi yang anda masukan sudah terdata";
		} else {
			return "Undefined Error";
		}
	}
}
