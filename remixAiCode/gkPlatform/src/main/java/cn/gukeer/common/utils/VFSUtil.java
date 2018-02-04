package cn.gukeer.common.utils;

import com.github.pagehelper.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Properties;

/**
 * 文件管理帮助类，处理应用之外的文件，如照片、邮件附件等不依赖于应用存在而存在的文件。 注意：里面的文件路径除了特殊说明外都是基于VFS根路径的
 *
 * @author guowei
 */
public abstract class VFSUtil {
    private static Log log = LogFactory.getLog(VFSUtil.class);

    /**
     * VFS 根路径(最后没有/号)
     */
    private static String VFS_ROOT_PATH;

    static {
        try {
            readVFSRootPath();// 给VFS_ROOT_PATH赋初始值
        } catch (Exception e) {
            log.error("读取配置文件出错！", e);
        }

    }

    /**
     * 读取VFS路径配置文件
     */
    private static void readVFSRootPath() {
        String key = null;
        if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
            key = "vfsroot.windows";
        } else {
            key = "vfsroot.linux";
        }
        try {
            Properties p = new Properties();
            InputStream inStream = new ClassPathResource("/db.properties").getInputStream();
            p.load(inStream);
            VFS_ROOT_PATH = p.getProperty(key);
        } catch (Exception e1) {
            VFS_ROOT_PATH = "";
            log.error("[vfsroot路径读取]配置文件模式出错！", e1);
        }

        if (StringUtil.isEmpty(VFS_ROOT_PATH)) {
            if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
                VFS_ROOT_PATH = "C:/gukeer/vfsroot/";
            } else {
                VFS_ROOT_PATH = "/opt/gukeer/vfsroot/";
            }
        }
    }

    /**
     * 获取当前的VfsRootPath
     *
     * @return
     */
    public static String getVFSRootPath() {
        return VFS_ROOT_PATH;
    }

    /**
     * 获取文件输入流
     *
     * @param file
     * @param fileStream
     * @return
     */
    public static InputStream getInputStream(File file, boolean fileStream) {
        if (fileStream == true) {//使用文件流
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(file);

            } catch (FileNotFoundException e) {
                if (log.isDebugEnabled()) {
                    log.debug(e);
                }
                String msg = "找不到指定的文件[" + file.getName() + "]。";
                if (log.isDebugEnabled()) {
                    log.debug(msg);
                }
                throw new FileOperationException(msg, e);
            }
            return fin;
        } else { // 使用内存流
            InputStream in = null;
            if (file != null && file.canRead() && file.isFile()) {
                try {
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    FileInputStream stream = new FileInputStream(file);
                    BufferedInputStream bin = new BufferedInputStream(stream);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = bin.read(b)) != -1) {
                        buffer.write(b, 0, len);
                    }

                    stream.close();
                    in = new ByteArrayInputStream(buffer.toByteArray());
                } catch (Exception e) {
                    String msg = "不能读取文件[" + file.getName() + "]";
                    if (log.isErrorEnabled()) {
                        log.error(msg, e);
                    }
                    throw new FileOperationException(msg, e);
                }
            } else {
                String msg = "不是文件或文件不可读[" + file.getName() + "]";
                if (log.isDebugEnabled()) {
                    log.debug(msg);
                }
                throw new FileOperationException("不是文件或文件不可读");
            }
            return in;
        }
    }
}
