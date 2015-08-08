package com.unitedvision.sangihe.document.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.unitedvision.sangihe.monev.configuration.ApplicationConfig;
import com.unitedvision.sangihe.monev.document.pdf.RekapSingleKegiatanPdfView;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.service.KegiatanService;

public class RekapSingleKegiatanPdfViewTest extends RekapSingleKegiatanPdfView {

	private static RekapSingleKegiatanPdfViewTest pdf = new RekapSingleKegiatanPdfViewTest();
	private static ApplicationContext appContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	private static KegiatanService kegiatanService = appContext.getBean(KegiatanService.class);
	
	public static void main(String[] args) {
        Document document = pdf.newDocument();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("E:\\rekap-single-kegiatan.pdf"));

            document.open();

            RekapKegiatan rekap = kegiatanService.rekapKegiatan(1L);

            Map<String, Object> model = new HashMap<>();
            model.put("rekap", rekap);
            
            pdf.create(model, document);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
        
        System.out.println("DONE...");
	}
	
	@Override
	protected Document newDocument() {
		return super.newDocument();
	}
	
}
