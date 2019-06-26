package vel.data.common.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public boolean isXLSX() {
		return isXLSX;
	}

	public void setXLSX(boolean isXLSX) {
		this.isXLSX = isXLSX;
	}
	private String filePath;
	private boolean isXLSX=false;
	private  Workbook workbook;
	private InputStream inputStream ;
	
	public ExcelReader(String filepath)throws Exception{
		this.filePath=filepath;
		this.isXLSX= this.filePath!=null && (this.filePath.endsWith(".xlsx") ||this.filePath.endsWith(".XLXS"));
		this.readFile();
	}
	
	private void readFile() throws Exception{
		 this.inputStream = new FileInputStream(new File(this.filePath));
		if(this.isXLSX){
		
			 workbook = new XSSFWorkbook(inputStream);
		}
		else{
			 workbook = new HSSFWorkbook(inputStream);
		}
		   
	}
	public FormulaEvaluator getHSSFFormulaEvaluator() {
		if(this.isXLSX){
			
			return new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
		}
		else{
			return new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
		}
		
	}
	public Sheet getSheetAt(int index){
		return this.workbook.getSheetAt(index);
	}
	
	public Sheet getSheet(String name){
	
		return this.workbook.getSheet(name);
	}
	public void close()throws IOException{
		this.inputStream.close();
	}
	
}
