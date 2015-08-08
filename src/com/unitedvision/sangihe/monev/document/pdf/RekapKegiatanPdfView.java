package com.unitedvision.sangihe.monev.document.pdf;

import java.awt.Color;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.util.DateUtil;

public class RekapKegiatanPdfView extends CustomAbstractPdfView {

	@Override
	public Document create(Map<String, Object> model, Document doc) throws DocumentException {
		@SuppressWarnings("unchecked")
		List<RekapKegiatan> list = (List<RekapKegiatan>) model.get("rekap");
		Integer tahun = (Integer) model.get("tahun");
		
		doc.newPage();
		
		createTitle(doc);
		addEmptyLine(doc, 1);
		
		createSubTitle(doc, tahun);
		addEmptyLine(doc, 1);
		
		createContent(doc, list);
		addEmptyLine(doc, 2);
		
		return doc;
	}

	@Override
	protected void createTitle(Document doc) throws DocumentException {
		Paragraph title = new Paragraph();
		title.add(new Paragraph("Pemerintah Kabupaten Kepulauan Sangihe", fontTitle));
		title.add(new Paragraph("Rekap Kegiatan Satuan Kerja", fontSubTitle));
		title.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(title);
	}

	protected void createSubTitle(Document doc, Integer tahun) throws DocumentException {
		Paragraph subTitle = new Paragraph();
		subTitle.setAlignment(Element.ALIGN_CENTER);
		
		float[] columnWidth = {12};
		
		PdfPTable table = new PdfPTable(columnWidth);
		table.setWidthPercentage(tablePercentage);
		
		insertCell(table, String.format("Tahun: %d", tahun), align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		Date tanggalCetak = DateUtil.getDate();
		insertCell(table, String.format("Tanggal Cetak: %s", DateUtil.toFormattedStringDate(tanggalCetak, "-")), align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		
		subTitle.add(table);
		
		doc.add(subTitle);
	}

	@Override
	protected void createContent(Document doc, List<?> list) throws DocumentException {
		
		Paragraph content = new Paragraph();
		content.setAlignment(Element.ALIGN_CENTER);
		
		// 8 Kolom
		float[] columnWidth = {7, 5, 5, 3};

		PdfPTable table = new PdfPTable(columnWidth);
		table.setWidthPercentage(tablePercentage);

		insertCell(table, "Nama Kegiatan", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Pagu Anggaran", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Realisasi Anggaran", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Realisasi Fisik", align, 1, fontHeader, Rectangle.BOX, null, null);
		table.setHeaderRows(1);
		
		String deterUnitKerja = null;
		String deterProgram = null;
		for (Object object : list) {

			RekapKegiatan rekap = (RekapKegiatan)object;
			String namaUnitKerja = rekap.getNamaUnitKerja();
			String namaProgram = rekap.getNamaProgram();
			
			if ( !namaUnitKerja.equals(deterUnitKerja) ) {
				deterUnitKerja = namaUnitKerja;
				insertCell(table, deterUnitKerja, align, 4, fontHeader, Rectangle.BOX, null, Color.GRAY);
			}
			
			if ( !namaProgram.equals(deterProgram) ) {
				deterProgram = namaProgram;
				insertCell(table, deterProgram, align, 4, fontContent, Rectangle.BOX, null, Color.YELLOW);
			}

			insertCell(table, rekap.getNamaKegiatan(), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("Rp %d", rekap.getPaguAnggaran()), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("Rp %d", rekap.getRealisasiAnggaran()), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("%d %s", rekap.getRealisasiFisik(), "%"), align, 1, fontContent, Rectangle.BOX, null, null);
		}

		content.add(table);
		
		doc.add(content);
	}
	
	@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate(), 0.1f, 0.1f, 0.1f, 0.1f);
	}
}
