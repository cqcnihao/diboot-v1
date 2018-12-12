import com.diboot.components.file.excel.ExcelReader;
import com.diboot.components.file.excel.ExcelWriter;
import com.diboot.framework.utils.JSON;

import java.util.List;
import java.util.Map;

/**
 * @author Mazc@dibo.ltd
 * @version 2018/12/3
 * Copyright © www.dibo.ltd
 */
public class Test {

    public static void main(String[] args) {
        try{
            List<String[]> list = ExcelReader.toList("D:/temp/test.xlsx");
            System.out.println(JSON.stringify(list));

            Map<String, List<String[]>> map = ExcelReader.toMap("D:/temp/test.xlsx");
            System.out.println(JSON.stringify(map));

            String file = ExcelWriter.generateExcel("test2.xlsx", list);
            System.out.println(file);

            file = ExcelWriter.generateExcel("test3.xlsx", map);
            System.out.println(file);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
