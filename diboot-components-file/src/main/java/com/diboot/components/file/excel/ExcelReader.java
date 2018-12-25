package com.diboot.components.file.excel;

import com.diboot.framework.utils.S;
import com.diboot.framework.utils.V;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***
 * Excel 读取类
 * @author Mazc@dibo.ltd
 * @version 20161107
 *
 */
public class ExcelReader {
	private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

	private static DecimalFormat fmtDecimal = new DecimalFormat("0.##");

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String ERROR = "ERR:";

	/**
	 * 解析Excel文件，将所有sheet下的内容读取合并到一起
	 * @param excelFilePath excel文件全路径
	 * @return List<String[]>
	 */
	public static List<String[]> toList(String excelFilePath) throws Exception{
		File file = new File(excelFilePath);
		return toList(file);
	}

	/**
	 * 解析Excel文件，将所有sheet下的内容读取合并到一起
	 * @param file excel文件
	 * @return List<String[]>
	 */
	public static List<String[]> toList(File file) throws Exception{
		if(file.getName().endsWith(".xls")){
			return readXls(new FileInputStream(file));
		}
		else if(file.getName().endsWith(".xlsx")){
			return readXlsx(new FileInputStream(file));
		}
		else{
			throw new Exception("无法识别的Excel格式！");
		}
	}

	/**
	 * 解析Excel文件，将所有sheet下的内容读取合并到一起
	 * @param file 上传文件对象
	 * @return List<String[]>
	 */
	public static List<String[]> toList(MultipartFile file) throws Exception{
        if(file.getOriginalFilename().endsWith(".xls")){
			return readXls(file.getInputStream());
		}
		else if(file.getOriginalFilename().endsWith(".xlsx")){
			return readXlsx(file.getInputStream());
		}
		else{
			throw new Exception("无法识别的Excel格式！");
		}
	}

	/**
	 * 解析Excel文件，按sheet读取，key为sheet名，value为sheet中的数据
	 * @param excelFilePath excel文件全路径
	 * @return LinkedHashMap<String:sheet名, List<String[]>: 数据记录>
	 */
	public static Map<String, List<String[]>> toMap(String excelFilePath) throws Exception{
		File file = new File(excelFilePath);
		return toMap(file);
	}

	/**
	 * 解析Excel文件，按sheet读取，key为sheet名，value为sheet中的数据
	 * @param excelFile excel文件对象
	 * @return LinkedHashMap<String:sheet名, List<String[]>: 数据记录>
	 */
	public static Map<String, List<String[]>> toMap(File excelFile) throws Exception{
		if(excelFile.getName().endsWith(".xls")){
			return readXlsAsMap(new FileInputStream(excelFile));
		}
		else if(excelFile.getName().endsWith(".xlsx")){
			return readXlsxAsMap(new FileInputStream(excelFile));
		}
		else{
			throw new Exception("无法识别的Excel格式！");
		}
	}

	/**
	 * 解析Excel文件，按sheet读取，key为sheet名，value为sheet中的数据
	 * @param excelFile excel上传文件对象
	 * @return LinkedHashMap<String:sheet名, List<String[]>: 数据记录>
	 */
	public static Map<String, List<String[]>> toMap(MultipartFile excelFile) throws Exception{
		if(excelFile.getOriginalFilename().endsWith(".xls")){
			return readXlsAsMap(excelFile.getInputStream());
		}
		else if(excelFile.getOriginalFilename().endsWith(".xlsx")){
			return readXlsxAsMap(excelFile.getInputStream());
		}
		else{
			throw new Exception("无法识别的Excel格式！");
		}
	}

	/**
	 * 读取 Excel 2010+版本
	 * @param is Excel文件流
	 * @return
	 * @throws Exception
	 */
	 private static List<String[]> readXlsx(InputStream is) throws Exception {
		 Map<String, List<String[]>> resultMap = readXlsxAsMap(is);
		 return mergeSheetDataToList(resultMap);
	 }

	/**
	 * 读取Excel 2010+版本，各sheet的记录独立返回
	 * @param is Excel文件流
	 * @return LinkedHashMap
	 * @throws Exception
	 */
	private static Map<String, List<String[]>> readXlsxAsMap(InputStream is) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Map<String, List<String[]>> map = new LinkedHashMap<>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet sheet = xssfWorkbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			List<String[]> list = new ArrayList<>();
			// Read the Row
			int cellCount = 0;//获取总列数
			for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
				XSSFRow xssfRow = sheet.getRow(rowNum);
				if(xssfRow == null){
					break;
				}
				if (rowNum == 0){
					cellCount = xssfRow.getPhysicalNumberOfCells();
				}
				String[] rowValues = buildRowValues(xssfRow, rowNum, cellCount);
				list.add(rowValues);
			}
			String sheetName = sheet.getSheetName();
			if(V.isEmpty(sheetName)){
				sheetName = "Sheet"+(map.size()+1);
			}
			map.put(sheetName, list);
		}
		try{
			xssfWorkbook.close();
		}
		catch(Exception e){
			logger.error("关闭Excel异常:", e);
		}
		return map;
	}

	 /**
	 * 读取Excel 2003-2007版本
	 * @param is Excel文件流
	 * @return
	 * @throws Exception
	 */
	 private static List<String[]> readXls(InputStream is) throws Exception {
		 Map<String, List<String[]>> resultMap = readXlsAsMap(is);
		 return mergeSheetDataToList(resultMap);
	}

	/***
	 * 合并全部sheet数据到list
	 * @param resultMap
	 * @return
	 */
	private static List<String[]> mergeSheetDataToList(Map<String, List<String[]>> resultMap){
		List<String[]> list = new ArrayList<>();
		if(V.notEmpty(resultMap)){
			int columnSize = 0, sheetIndex = 0;
			for(Map.Entry<String, List<String[]>> entry : resultMap.entrySet()){
				if(V.notEmpty(entry.getValue())){
					String[] rows = entry.getValue().get(0);
					if(columnSize == 0){
						columnSize = rows.length;
					}
					else if(columnSize != rows.length){
						logger.warn("sheet合并读取时异常: 各sheet中的excel列数量不一致，暂不合并该sheet！");
						continue;
					}
					if(sheetIndex > 0){
						// 剔除其余sheet的表头
						entry.getValue().remove(0);
					}
					if(V.notEmpty(entry.getValue())){
						list.addAll(entry.getValue());
					}
					sheetIndex++;
				}
			}
		}
		return list;
	}

	/**
	 * 读取Excel 2003-2007版本，各sheet的记录独立返回
	 * @param is Excel文件流
	 * @return LinkedHashMap
	 * @throws Exception
	 */
	private static Map<String, List<String[]>> readXlsAsMap(InputStream is) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Map<String, List<String[]>> map = new LinkedHashMap<>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet sheet = hssfWorkbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			List<String[]> list = new ArrayList<>();
			// Read the Row
			int cellCount = 0;//获取总列数
			for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
				HSSFRow hssfRow = sheet.getRow(rowNum);
				if(hssfRow == null){
					break;
				}
				if (rowNum == 0){//获取总列数
					cellCount = hssfRow.getPhysicalNumberOfCells();
				}
				String[] rowValues = buildRowValues(hssfRow, rowNum, cellCount);
				list.add(rowValues);
			}
			String sheetName = sheet.getSheetName();
			if(V.isEmpty(sheetName)){
				sheetName = "Sheet"+(map.size()+1);
			}
			map.put(sheetName, list);
		}
		try{
			hssfWorkbook.close();
		}
		catch(Exception e){
			logger.error("关闭Excel异常:", e);
		}
		return map;
	}

	/**
	 * 组装行数据
	 * @param hssfRow
	 * @param rowNum
	 * @param cellCount
	 * @return
	 */
	private static String[] buildRowValues(Row hssfRow, int rowNum, int cellCount) throws Exception{
		String[] rowValues = new String[cellCount];
		List<String> errors = null;
		for(int i=0; i<cellCount; i++){
			Cell cell = hssfRow.getCell(i);
			String val = "";
			if(cell != null){
				val = getValue(cell);
				if(val == null){
					val = "";
				}
				if(V.notEmpty(val) && val.startsWith(ERROR)){
					String location = "["+(rowNum+1)+"行"+(cellCount+1)+"列]";
					val = val.replace(ERROR, ERROR+location);
					if(errors == null){
						errors = new ArrayList<>();
					}
					errors.add(val);
				}
				rowValues[i] = val;
			}
			rowValues[i] = val;
		}
		if(V.notEmpty(errors)){
			throw new Exception(S.join(errors));
		}
		// 返回正确结果
		return rowValues;
	}
	
	 /**
	  * 获取Excel单元格的值
	  * @param cell
	  * @return
	  */
	private static String getValue(Cell cell){
		if (CellType.BOOLEAN.equals(cell.getCellType())) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		else if (CellType.NUMERIC.equals(cell.getCellType())) {
			if(DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				return dateFormat.format(cell.getDateCellValue()); //日期型
			}
			else {
				return fmtDecimal.format(cell.getNumericCellValue()); //数字
			}
		}
		else if(CellType.BLANK.equals(cell.getCellType())){
			return "";
		}
		else if(CellType.FORMULA.equals(cell.getCellType())){
			return ERROR+"公式错误!";
		}
		else if(CellType.ERROR.equals(cell.getCellType())){
			return ERROR+"数据格式错误!";
		}
		else{
			return cell.getStringCellValue();
		}
	}

}