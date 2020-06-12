package com.chouxiaozi.vhr.vo;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.chouxiaozi.vhr.model.Department;
import com.chouxiaozi.vhr.model.JobLevel;
import com.chouxiaozi.vhr.model.Nation;
import com.chouxiaozi.vhr.model.Politicsstatus;
import com.chouxiaozi.vhr.model.Position;

public class CustomConverter implements Converter {
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Object convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return cellData.getStringValue();
    }

    @Override
    public CellData convertToExcelData(Object o, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String name = "";
        if(o instanceof Nation){
            name = ((Nation) o).getName();
        }else if(o instanceof Politicsstatus){
            name = ((Politicsstatus) o).getName();
        }else if(o instanceof Department){
            name = ((Department) o).getName();
        }else if(o instanceof JobLevel){
            name = ((JobLevel) o).getName();
        }else if(o instanceof Position){
            name = ((Position) o).getName();
        }
        return new CellData(name);
    }
}
