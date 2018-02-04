package cn.gukeer.classcard.util.ffmpeg;

/**
 * Created by alpha on 18-1-30.
 */
public class ConverVideoTest {
    public void run() {
        try {
            // 转换并截图
            String filePath = "C:\\Users\\wangdy\\Desktop\\pics\\05.尚硅谷_SVN_启动服务器.wmv";
            ConverVideoUtils cv = new ConverVideoUtils(filePath);
            String targetExtension = ".mp4";
            boolean isDelSourseFile = true;
            boolean beginConver = cv.beginConver(targetExtension,isDelSourseFile);
            System.out.println(beginConver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ConverVideoTest c = new ConverVideoTest();
        c.run();
    }
}