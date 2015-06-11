package com.unitedvision.sangihe.monev.util;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;

public class EntityRestMessage<T> extends RestMessage {
	private T model;
	
	protected EntityRestMessage(Exception ex) {
		super(ex);
	}
	
	protected EntityRestMessage(T model) {
		super("Berhasil", Type.ENTITY, model);
		this.model = model;
	}
	
	public T getModel() {
		return model;
	}
	
	public static <T> EntityRestMessage<T> entityError(Exception cause) {
		return new EntityRestMessage<T>(cause);
	}
	
	public static EntityRestMessage<Skpd> create(Skpd skpd) {
		return new EntityRestMessage<Skpd>(skpd);
	}
	
	public static EntityRestMessage<Operator> create(Operator operator) {
		return new EntityRestMessage<Operator>(operator);
	}
	
	public static EntityRestMessage<Kegiatan> create(Kegiatan kegiatan) {
		return new EntityRestMessage<Kegiatan>(kegiatan);
	}
	
	public static EntityRestMessage<Realisasi> create(Realisasi realisasi) {
		return new EntityRestMessage<Realisasi>(realisasi);
	}
}
