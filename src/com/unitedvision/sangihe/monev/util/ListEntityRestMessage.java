package com.unitedvision.sangihe.monev.util;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;

public class ListEntityRestMessage<T> extends RestMessage {
	private List<T> list;
	
	protected ListEntityRestMessage(Exception ex) {
		super(ex);
	}

	protected ListEntityRestMessage(List<T> list) {
		super("Berhasil", Type.LIST);
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public static <T> ListEntityRestMessage<T> listEntityError(Exception cause) {
		return new ListEntityRestMessage<T>(cause);
	}
	
	public static ListEntityRestMessage<Kegiatan> createListKegiatan(List<Kegiatan> kegiatan) {
		return new ListEntityRestMessage<Kegiatan>(kegiatan);
	}
}
