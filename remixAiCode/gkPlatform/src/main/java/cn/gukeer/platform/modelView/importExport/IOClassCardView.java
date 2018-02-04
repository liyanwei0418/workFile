package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by alpha on 17-7-25.
 */
public class IOClassCardView implements Serializable{
    private String macId;
    private String equipmentName;

    @ExcelField(title = "mac地址", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

 /*   @ExcelField(title = "", align = 2, sort = 2, groups = {1, 2},isnull=1)*/
   /* public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }*/
}
