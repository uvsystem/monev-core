package com.unitedvision.sangihe.monev.document.pdf;

import java.awt.Color;
import java.sql.Date;
import java.time.Month;
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

public class RekapSingleKegiatanPdfView extends CustomAbstractPdfView {

	private RekapKegiatan rekapKegiatan;
	
	@Override
	public Document create(Map<String, Object> model, Document doc) throws DocumentException {
		rekapKegiatan = (RekapKegiatan) model.get("rekap");
		
		doc.newPage();
		
		createTitle(doc);
		addEmptyLine(doc, 1);
		
		createSubTitle(doc);
		addEmptyLine(doc, 1);
		
		createContent(doc, rekapKegiatan.getDaftarAnggaran());
		addEmptyLine(doc, 2);
		
		return doc;
	}

	@Override
	protected void createTitle(Document doc) throws DocumentException {
		Paragraph title = new Paragraph();
		title.add(new Paragraph("Pemerintah Kabupaten Kepulauan Sangihe", fontTitle));
		title.add(new Paragraph("Rekap Kegiatan", fontSubTitle));
		title.setAlignment(Element.ALIGN_CENTER);
		
		doc.add(title);
	}

	protected void createSubTitle(Document doc) throws DocumentException {
		Paragraph subTitle = new Paragraph();
		subTitle.setAlignment(Element.ALIGN_CENTER);
		
		float[] columnWidth = {2, 4, 2, 4};
		
		PdfPTable table = new PdfPTable(columnWidth);
		table.setWidthPercentage(tablePercentage);
		
		insertCell(table, "Unit Kerja", align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, String.format(": %s", rekapKegiatan.getNamaUnitKerja()), align, 3, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, "Program", align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, String.format(": %s", rekapKegiatan.getNamaProgram()), align, 3, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, "Kegiatan", align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, String.format(": %s", rekapKegiatan.getNamaKegiatan()), align, 3, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, "Pagu Anggaran", align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);
		insertCell(table, String.format(": Rp %d", rekapKegiatan.getPaguAnggaran()), align, 1, fontHeader, Rectangle.BOX, Color.WHITE, null);

		Date tanggalCetak = DateUtil.getDate();
		insertCell(table, String.format("Tanggal Cetak: %s", DateUtil.toFormattedStringDate(tanggalCetak, "-")), Element.ALIGN_RIGHT, 2, fontContent, Rectangle.BOX, Color.WHITE, null);
		
		subTitle.add(table);
		
		doc.add(subTitle);
	}

	@Override
	protected void createContent(Document doc, List<?> list) throws DocumentException {
		
		Paragraph content = new Paragraph();
		content.setAlignment(Element.ALIGN_CENTER);
		
		float[] columnWidth = {5, 5, 5, 5, 5, 5};

		PdfPTable table = new PdfPTable(columnWidth);
		table.setWidthPercentage(tablePercentage);

		insertCell(table, "Bulan", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "RPD", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "RPD Kumulatif", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Realisasi", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Penyerapan", align, 1, fontHeader, Rectangle.BOX, null, null);
		insertCell(table, "Tingkat Konsistensi", align, 1, fontHeader, Rectangle.BOX, null, null);
		table.setHeaderRows(1);
		
		for (Month month : Month.values()) {
			insertCell(table, month.name(), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("Rp %d", rekapKegiatan.getRencanaAnggaran(month)), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("Rp %d", rekapKegiatan.getRencanaAnggaranKumulatif(month)), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("Rp %d", rekapKegiatan.getRealisasiAnggaranKumulatif(month)), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("%f %s", rekapKegiatan.getPenyerapan(month), "%"), align, 1, fontContent, Rectangle.BOX, null, null);
			insertCell(table, String.format("%f %s", rekapKegiatan.getKonsistensi(month), "%"), align, 1, fontContent, Rectangle.BOX, null, null);
		}
		
		content.add(table);
		
		doc.add(content);
	}
	
	@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate(), 0.1f, 0.1f, 0.1f, 0.1f);
	}
}
