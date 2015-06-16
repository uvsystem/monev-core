package com.unitedvision.sangihe.monev.util;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.RekapSkpd;
import com.unitedvision.sangihe.monev.entity.Skpd;

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
	
	public static ListEntityRestMessage<Skpd> createListSkpd(List<Skpd> skpd) {
		return new ListEntityRestMessage<Skpd>(skpd);
	}
	
	public static ListEntityRestMessage<Operator> createListOperator(List<Operator> operator) {
		return new ListEntityRestMessage<Operator>(operator);
	}
	
	public static ListEntityRestMessage<Kegiatan> createListKegiatan(List<Kegiatan> kegiatan) {
		return new ListEntityRestMessage<Kegiatan>(kegiatan);
	}
	
	public static ListEntityRestMessage<Realisasi> createListRealisasi(List<Realisasi> realisasi) {
		return new ListEntityRestMessage<Realisasi>(realisasi);
	}

	public static ListEntityRestMessage<RekapSkpd> createListRekapSkpd(List<RekapSkpd> rekapSkpd) {
		return new ListEntityRestMessage<RekapSkpd>(rekapSkpd);
	}

	public static ListEntityRestMessage<RekapKegiatan> createListRekapKegiatan(List<RekapKegiatan> rekapKegiatan) {
		return new ListEntityRestMessage<RekapKegiatan>(rekapKegiatan);
	}
}
