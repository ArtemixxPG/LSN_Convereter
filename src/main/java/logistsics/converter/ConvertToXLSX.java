package logistsics.converter;

import Logistics.entyties.Locations;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertToXLSX {

    public static final String[] locationHeaders = {"address", "city", "code", "country",
            "latitude", "longitude", "name"};

    private String filename;

    private List<Locations> locations;

    public ConvertToXLSX(String filename){
        this.filename = filename;
    }

    private List<Object[]> scopeData(String[] headers, EntytyType type){
        List<Object[]> data = new ArrayList<Object[]>();

        Object[] headers_ = new Object[headers.length];
        int i = 0;
        for(String header: headers){
            headers_[i] = header;
            if(i == headers.length-1){
                break;
            }
            i++;
        }

        data.add(headers_);

        if(type == EntytyType.Location) {
            for (Locations locations : locations) {
                Object[] dataFromEntity = new Object[headers.length];
                dataFromEntity[0] = locations.getAddress();
                dataFromEntity[1] = locations.getCity();
                dataFromEntity[2] = locations.getCode();
                dataFromEntity[3] = locations.getCountry();
                dataFromEntity[4] = locations.getLatitude();
                dataFromEntity[5] = locations.getLongitude();
                dataFromEntity[6] = locations.getName();
                data.add(dataFromEntity);
            }
        }
        return data;
    }

    public void locationsToXLSX(String[] headers, EntytyType entytyType) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(this.filename);

        int rownum = 0;
        List<Object[]> data = scopeData(headers, entytyType);
        for(Object[] line : data){
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            for(Object obj : line){
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
        FileOutputStream out = new FileOutputStream(new File(filename));
        workbook.write(out);
        out.close();
    }


    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }
}

