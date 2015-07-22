package com.unitedvision.sangihe.monev.util;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Program;

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
	
	public static EntityRestMessage<Kegiatan> create(Kegiatan kegiatan) {
		return new EntityRestMessage<Kegiatan>(kegiatan);
	}
	
	public static EntityRestMessage<Program> create(Program program) {
		return new EntityRestMessage<Program>(program);
	}
}
