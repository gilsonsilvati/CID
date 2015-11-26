package br.com.cid.controller.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

@Named
@ViewScoped
public class ImprimiDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Doenca> todasDoencas;
	
	private Doenca doencaSelecionada;
	
	@Inject
	private Doencas doencas;
	
	@PostConstruct
	public void inicializar() {
		todasDoencas = doencas.todos();
	}
	
	/* XLS */
	public void postProcessXLS(Object document) {
		HSSFWorkbook xls = (HSSFWorkbook) document;
		HSSFSheet sheet = xls.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = xls.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}
	
	/* PDF */
	public void preProcessPDF(Object document) throws IOException, BadElementException,
			DocumentException {

		Document pdf = (Document) document;

		pdf.setMargins(25f, 25f, 25f, 25f);
		pdf.setPageSize(PageSize.A4);
		pdf.addTitle("CID");
		
		pdf.open();

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		
		String logo = servletContext.getRealPath("") + File.separator + "resources/imagens"
				+ File.separator + "logo_pdf.png";

		Image image = Image.getInstance(logo);
		image.setAlignment(Image.ALIGN_CENTER);

		pdf.add(image);

		Paragraph paragraph = new Paragraph("Sistema de consulta cid ®\n\n");
		paragraph.setAlignment("center");
		pdf.add(paragraph);
	}  
	
	public List<Doenca> getDoencas() {
		return todasDoencas;
	}

	public Doenca getDoencaSelecionada() {
		return doencaSelecionada;
	}

	public void setDoencaSelecionada(Doenca doencaSelecionada) {
		this.doencaSelecionada = doencaSelecionada;
	}
	
}
