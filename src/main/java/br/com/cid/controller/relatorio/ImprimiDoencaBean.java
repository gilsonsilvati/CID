package br.com.cid.controller.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
import br.com.cid.util.Repositorios;

@ManagedBean
@ViewScoped
public class ImprimiDoencaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private List<Doenca> doencas = new ArrayList<>();
	private Doenca doencaSelecionada;
	
	@PostConstruct
	public void inicializar() {
		Doencas doencas = this.repositorios.getDoencas();
		this.doencas = doencas.todos();
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
		// pdf.addTitle("Título here brother");
		
		pdf.open();

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		
		String logo = servletContext.getRealPath("") + File.separator + "resources/imagens"
				+ File.separator + "logo_pdf.png";

		Image image = Image.getInstance(logo);
		image.setAlignment(Image.ALIGN_CENTER);

		pdf.add(image);

		Paragraph paragraph = new Paragraph("\nCódigo e descrição cid.\n\n");
		paragraph.setAlignment("center");
		pdf.add(paragraph);
	}  
	
	public List<Doenca> getDoencas() {
		return doencas;
	}

	public Doenca getDoencaSelecionada() {
		return doencaSelecionada;
	}

	public void setDoencaSelecionada(Doenca doencaSelecionada) {
		this.doencaSelecionada = doencaSelecionada;
	}
	
}
