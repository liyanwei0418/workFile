package cn.gukeer.platform.common;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by conn on 2016/9/9.
 */
public class ConstantUtil {
    public final static String DELETE = "delete";
    public final static String UPDATE = "update";
    public final static String INSERT = "insert";
    public final static String SELECT = "select";

    // 年级
    public static List<KVEntity> njList = new ArrayList<KVEntity>();
    // 有效证件类型
    public static List<KVEntity> cardTypList = new ArrayList<KVEntity>();
    // 在校状态
    public static List<KVEntity> zxztList = new ArrayList<KVEntity>();
    // 招生类别
    public static List<KVEntity> zslbList = new ArrayList<KVEntity>();
    // 学生类别
    public static List<KVEntity> xslbList = new ArrayList<KVEntity>();
    // 班级类型
    public static List<KVEntity> bjlxList = new ArrayList<KVEntity>();
    // 政治面貌
    public static List<KVEntity> zzmmList = new ArrayList<KVEntity>();
    // 户口性质
    public static List<KVEntity> hkxzList = new ArrayList<KVEntity>();
    //性别
    public static List<KVEntity> xbList = new ArrayList<KVEntity>();
    //学段
    public static List<KVEntity> xdList = new ArrayList<KVEntity>();
    //是或否
    public static List<KVEntity> yornList = new ArrayList<KVEntity>();
    //来源地区
    public static List<KVEntity> lydqList = new ArrayList<KVEntity>();
    //家长
    public static List<KVEntity> parList = new ArrayList<>();

    public static Map<String, Object> map = new HashMap<String, Object>();

    static {

        KVEntity entry = new KVEntity("1", "一年级");
        njList.add(entry);
        entry = new KVEntity("2", "二年级");
        njList.add(entry);
        entry = new KVEntity("3", "三年级");
        njList.add(entry);
        entry = new KVEntity("4", "四年级");
        njList.add(entry);
        entry = new KVEntity("5", "五年级");
        njList.add(entry);
        entry = new KVEntity("6", "六年级");
        njList.add(entry);
        entry = new KVEntity("7", "七年级");
        njList.add(entry);
        entry = new KVEntity("8", "八年级");
        njList.add(entry);
        entry = new KVEntity("9", "九年级");
        njList.add(entry);

        entry = new KVEntity("1", "身份证");
        cardTypList.add(entry);
        entry = new KVEntity("2", "护照");
        cardTypList.add(entry);

        entry = new KVEntity("1", "男");
        xbList.add(entry);
        entry = new KVEntity("2", "女");
        xbList.add(entry);

        entry = new KVEntity("0", "在籍在校");
        zxztList.add(entry);
        entry = new KVEntity("1", "在籍离校");
        zxztList.add(entry);
        entry = new KVEntity("2", "在校不在籍");
        zxztList.add(entry);
        entry = new KVEntity("3", "不在籍不在校");
        zxztList.add(entry);

        entry = new KVEntity("0", "普通入学");
        zslbList.add(entry);
        entry = new KVEntity("1", "民族班");
        zslbList.add(entry);
        entry = new KVEntity("2", "体育特招");
        zslbList.add(entry);
        entry = new KVEntity("3", "外校转入");
        zslbList.add(entry);
        entry = new KVEntity("4", "恢复入学资格");
        zslbList.add(entry);
        entry = new KVEntity("5", "外籍");
        zslbList.add(entry);
        entry = new KVEntity("6", "其他");
        zslbList.add(entry);

        entry = new KVEntity("0", "普通学生");
        xslbList.add(entry);
        entry = new KVEntity("1", "随班就读生");
        xslbList.add(entry);
        entry = new KVEntity("2", "残障学生");
        xslbList.add(entry);
        entry = new KVEntity("3", "其他");
        xslbList.add(entry);

        entry = new KVEntity("0", "普通班级");
        bjlxList.add(entry);
        entry = new KVEntity("1", "民族班");
        bjlxList.add(entry);
        entry = new KVEntity("2", "体育班级");
        bjlxList.add(entry);
        entry = new KVEntity("3", "外语班级");
        bjlxList.add(entry);
        entry = new KVEntity("4", "其他特殊班");
        bjlxList.add(entry);

        entry = new KVEntity("0", "群众");
        zzmmList.add(entry);
        entry = new KVEntity("1", "团员");
        zzmmList.add(entry);
        entry = new KVEntity("2", "党员");
        zzmmList.add(entry);

        entry = new KVEntity("0", "农村");
        hkxzList.add(entry);
        entry = new KVEntity("1", "城镇");
        hkxzList.add(entry);
        entry = new KVEntity("2", "其他");
        hkxzList.add(entry);

        entry = new KVEntity("1", "小学");
        xdList.add(entry);
        entry = new KVEntity("2", "初中");
        xdList.add(entry);
        entry = new KVEntity("3", "高中");
        xdList.add(entry);

        entry = new KVEntity("1", "是");
        yornList.add(entry);
        entry = new KVEntity("2", "否");
        yornList.add(entry);

        entry = new KVEntity("0", "区县内");
        lydqList.add(entry);
        entry = new KVEntity("1", "省市内");
        lydqList.add(entry);
        entry = new KVEntity("2", "省市外");
        lydqList.add(entry);

        entry = new KVEntity("1", "父亲");
        parList.add(entry);
        entry = new KVEntity("2", "母亲");
        parList.add(entry);
        entry = new KVEntity("3", "其他");
        parList.add(entry);

        map.put("nj", njList);
        map.put("cardTyp", cardTypList);
        map.put("zxzt", zxztList);
        map.put("zslb", zslbList);
        map.put("xslb", xslbList);
        map.put("bjlx", bjlxList);
        map.put("zzmm", zzmmList);
        map.put("hkxz", hkxzList);
        map.put("xb", xbList);
        map.put("xd", xdList);
        map.put("yorn", yornList);
        map.put("lydq", lydqList);
        map.put("par", parList);
    }

    public static String getGradeNj(int nj) {
        String val = "";
        for (KVEntity entity : njList) {
            if (entity.getKey().equals(String.valueOf(nj))) {
                val = entity.getValue();
                break;
            }
        }
        return val;
    }


    /*
    * nj 年级
    * cardTyp 证件类型
    * zxzt 在校状态
    * zslb 招生类别
    * xslb 学生类别
    * bjlx 班级类别
    * zzmm 政治面貌
    * hkxz 户口性质
    * xb 性别
    * xd 学段
    * yorn 是否判断
    * lydq 来源地区
    * */
    public static String getValueByKeyAndFlag(int key, String which) {
        String val = "";
        List<KVEntity> kvEntityList = (List<KVEntity>) map.get(which);
        for (KVEntity entity : kvEntityList) {
            if (entity.getKey().equals(String.valueOf(key))) {
                val = entity.getValue();
                break;
            }
        }
        return val;
    }

    public static Integer getKeyByValueAndFlag(String value, String which) {
        int val = 0;
        List<KVEntity> kvEntityList = (List<KVEntity>) map.get(which);
        for (KVEntity entity : kvEntityList) {
            if (entity.getValue().equals(value)) {
                val = Integer.parseInt(entity.getKey());
                break;
            }
        }
        return val;
    }

    public static List<String> splitWithOutNull(String param) {
        String[] res = param.split(",");
        List<String> out = new ArrayList<>();
        for (String v : res) {
            if (!StringUtils.isEmpty(v))
                out.add(v);
        }
        return out;
    }

    public static List searchParam(String paramList) {
        if (StringUtils.isEmpty(paramList))
            return null;
        String str = paramList.replace("[", "").replace("]", "");
        String[] resS = str.split(",");
        List resL = new ArrayList();
        resL = Arrays.asList(resS);
        return resL;
    }

    //自动翻译  初一===初中一年级
    public static String translate(String origin) {
        String[] arry1 = {"高一", "高二", "高三", "初一", "初二", "初三", "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级"};
        String[] arry2 = {"高中1", "高中2", "高中3", "初中1", "初中2", "初中3", "小学1", "小学2", "小学3", "小学4", "小学5", "小学6"};
        String[] arry3 = {"高一", "高二", "高三", "初中一年级", "初中二年级", "初中三年级", "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级"};
        int index1 = Arrays.asList(arry1).indexOf(origin);
        int index2 = Arrays.asList(arry2).indexOf(origin);
        int index3 = Arrays.asList(arry3).indexOf(origin);
        if (index1 >= 0) {
            return arry2[index1];
        }
        if (index2 >= 0) {
            return arry1[index2];
        }
        if (index3 >= 0) {
            return arry1[index3];
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(getKeyByValueAndFlag("一年级","nj"));
        System.out.println(getKeyByValueAndFlag("二年级","nj"));
        System.out.println(getKeyByValueAndFlag("三年级","nj"));
        System.out.println(splitWithOutNull("asdasd"));
    }
}
