package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

/**
 * Created by LL on 2017/9/25.
 */
public class IOCDiskTeacher {
    private String name;
    private String account;

    @ExcelField(title = "用户姓名", align = 2, sort = 1, groups = {1, 2})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "教师编号", align = 2, sort = 1, groups = {1, 2})
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
