package com.diboot.components.file.excel;

import com.diboot.components.file.FileHelper;
import com.diboot.framework.utils.S;
import com.diboot.framework.utils.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/***
 * Excel Writer: 生成Excel
 * @author Mazc@dibo.ltd
 * @version 20161107
 */
public class ExcelWriter {
	private static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

	/***
	 * 生成单sheet的excel文件
	 * @param excelFileName
	 * @param rows List<LinkedHashMap> 或者 List<String[]>
	 * @return excel文件路径
	 * @see ExcelFile
	 */
	public static String generateExcel(String excelFileName, List rows){
		ExcelFile excelFile = new ExcelFile(excelFileName);
		excelFile.addSheet(S.substringBefore(FileHelper.getFileName(excelFileName), "\\."), rows);
		if(excelFile.generate()){
			return excelFile.getGeneratedFilePath();
		}
		logger.warn("生成excel失败！");
		return null;
	}

	/***
	 * 生成多sheet的excel文件
	 * @param excelFileName
	 * @param sheetMap
	 * @return excel文件路径
	 * @see ExcelFile
	 */
	public static String generateExcel(String excelFileName, Map<String, List<String[]>> sheetMap){
		ExcelFile excelFile = new ExcelFile(excelFileName);
		if(V.notEmpty(sheetMap)){
			for(Map.Entry<String, List<String[]>> entry : sheetMap.entrySet()){
				excelFile.addSheet(entry.getKey(), entry.getValue());
			}
		}
		if(excelFile.generate()){
			return excelFile.getGeneratedFilePath();
		}
		logger.warn("生成excel失败！");
		return null;
	}

	/**
	 * 根据有序map列表创建excel数据
	 * @param linkMapList
	 * @param headers
	 * @param rows
	 */
	public static void buildExcelData(List<LinkedHashMap> linkMapList, List<String> headers, List<String[]> rows){
		// build headers
		buildHeaders(linkMapList, headers);
		// Build rows
		for (LinkedHashMap map : linkMapList){
			List<String> rowData = new ArrayList<String>();
			for (String header : headers){
				if (map.get(header) != null){
					String value = String.valueOf(map.get(header));
					// Make format right
					if ("false".equals(value)){
						value = "否";
					} else if ("true".equals(value)){
						value = "是";
					} else if (V.notEmpty(value) && value.contains(":") && value.endsWith(".0")){
						value = S.substring(value, 0, value.length() - 2);
					}
					rowData.add(value);
				} else {
					rowData.add("");
				}
			}
			rows.add(rowData.toArray(new String[rowData.size()]));
		}
	}

	/***
	 * 由全部数据的mapList中提取Excel的表头 (提取mapList的key全集)
	 * @param mapList
	 * @param headers
	 */
	public static void buildHeaders(List<LinkedHashMap> mapList, List<String> headers){
		// Get max size of map
		headers.clear();
		int cnt = 0;
		for (LinkedHashMap map : mapList){
			if (map.size() > cnt){
				cnt = map.size();
			}
		}
		// Build headers
		for (LinkedHashMap map : mapList){
			if (map.size() == cnt){
				Iterator it = map.entrySet().iterator();
				while (it.hasNext()){
					Map.Entry<String, String> entry = (Map.Entry) it.next();
					if (V.isEmpty(headers) || headers.size() < cnt){
						headers.add(entry.getKey());
					}
				}
				break;
			}
		}
	}

	/**
	 * 根据有序map列表添加excel的sheet
	 * @param linkMapList
	 * @param excelFile
	 * @throws Exception
	 */
	public static void buildExcelSheet(ExcelFile excelFile, List<LinkedHashMap> linkMapList) throws Exception{
		List<String> headers = new ArrayList<>();
		List<String[]> rows = new ArrayList<>();
		buildExcelData(linkMapList, headers, rows);
		excelFile.addSheet(headers, rows);
	}

	/**
	 * 根据有序map列表创建excel文件
	 * @param fileName
	 * @param linkMapList
	 * @return
	 * @throws Exception
	 */
	public static String buildExcelFile(String fileName, List<LinkedHashMap> linkMapList) throws Exception{
		ExcelFile excelFile = new ExcelFile(fileName);
		buildExcelSheet(excelFile, linkMapList);
		try{
			boolean success = excelFile.generate();
			if (success){
				return excelFile.getGeneratedFilePath();
			}
		} catch (Exception e){
			logger.error("生成excel文件出错", e);
		}
		return null;
	}

}
