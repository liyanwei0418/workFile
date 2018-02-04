package cn.gukeer.common.utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

public class FileUtils {

    public static final String VFS_ROOT_PATH = VFSUtil.getVFSRootPath();
    public static final String APP_DETAIL_PATH = "app/detail/";
    public static final String USER_HEADERS_PATH = "user/headers/";
    public static final String TEACHER_HEADERS_PATH = "teacher/headers/";
    public static final String SCHOOL_LOGO_PATH = "school/logo/";
    public static final String SCHOOL_BGPIC_PATH = "school/bgpic/";
    public static final String NOTIFY_ATTACH_PATH = "notify/attach/";
    public static final String DEFAULT_HEAD_PHOTO = "images/default_tou.png";
    public static final String CLASSCARD_PIC_PATH="classcard/classspace/pic/";
    public static final String CLASSCARD_VIDEO_PATH="classcard/classspace/video/";

    public static String readStreamAsString(InputStream ins) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        return builder.toString();

    }

    public static void writeLines(List<String> lines, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        for (String line : lines) {
            writer.print(line + "\r\n");
        }
        writer.close();
    }


    public static String getFileExtention(String fname) {
        int idx = fname.lastIndexOf(".");
        if (idx >= 0) {
            return fname.substring(idx);
        } else {
            return "";
        }
    }

    public static boolean isExists(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.exists();
    }


    public static boolean writeFileToDisk(String path, String fileName) {

        return true;
    }

    /*
    * 全路径拿后边的文件名
    * C:\Users\Administrator\Desktop\gukeer.exe  -----> gukeer.exe
    * */
    public static String showFileName(String fullPath) {
        String[] arr = fullPath.split("/");
        if (arr.length > 1) {
            String fileName = arr[arr.length - 1];
            String resName = fileName.substring(13,fileName.length());
            return resName;
        }
        return fullPath;
    }

    public static void main(String[] args) {
        System.out.println(showFileName("app/detail/201609/1475141395501.png"));
    }
}
