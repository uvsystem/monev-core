package com.unitedvision.sangihe.monev.util;

import java.util.List;

import com.unitedvision.sangihe.ehrm.connector.RestMessage;
import com.unitedvision.sangihe.monev.entity.Anggaran;
import com.unitedvision.sangihe.monev.entity.Fisik;
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Program;

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
	
	public static ListEntityRestMessage<Kegiatan> createListKegiatan(List<Kegiatan> daftarKegiatan) {
		return new ListEntityRestMessage<Kegiatan>(daftarKegiatan);
	}

	public static ListEntityRestMessage<Program> createListProgram(List<Program> daftarProgram) {
		return new ListEntityRestMessage<Program>(daftarProgram);
	}

	public static ListEntityRestMessage<Anggaran> createListAnggaran(List<Anggaran> daftarAnggaran) {
		return new ListEntityRestMessage<Anggaran>(daftarAnggaran);
	}

	public static ListEntityRestMessage<Fisik> createListFisik(List<Fisik> daftarFisik) {
		return new ListEntityRestMessage<Fisik>(daftarFisik);
	}
}
