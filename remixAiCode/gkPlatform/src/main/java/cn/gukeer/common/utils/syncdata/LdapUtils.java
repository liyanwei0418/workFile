package cn.gukeer.common.utils.syncdata;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;



/**
 * Ldap工具类
 * 
 * @author guowei
 *
 */
public class LdapUtils {

	private static LdapContext ctx;
	private static Log log = LogFactory.getLog(LdapUtils.class);

	public static LdapContext getCtx() {
		Properties prop = getProperties("/ldapConfig.properties");
		String url = prop.getProperty("contextPath");// 服务访问路径
		String domain = prop.getProperty("root");// ldap根目录
		String user = prop.getProperty("account");// ldap管理员
		String password = prop.getProperty("password");// ldap管理员密码
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, prop.getProperty("ldapFactory")); // LDAP工厂
		env.put(Context.SECURITY_AUTHENTICATION, prop.getProperty("authentication")); // LDAP访问安全级别
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_PRINCIPAL, user + "," + domain);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put("java.naming.ldap.attributes.binary", "objectSid objectGUID");
		LdapContext ldapCtx = null;
		try {
			ctx = new InitialLdapContext(env, null);
			// queryUser(ldapCtx);
		} catch (NamingException e) {
			log.warn("连接ldap服务器失败:配置错误", e);
			e.printStackTrace();
		} finally {
			if (ldapCtx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					log.warn("关闭ldap连接失败", e);
				}
			}
		}
		return ctx;
	}


	/**
	 * 拼接字符串方法
	 * 
	 * @param strings
	 * @return
	 */
	public static StringBuilder appendString(Object[] objects) {
		StringBuilder sb = new StringBuilder();
		if (objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				if (i == 0) {
					sb.append(",");
				}
				if (i + 1 >= objects.length) {
					sb.append(objects[i]);
				} else {
					sb.append(objects[i]).append(",");
				}
			}
		}
		return sb;
	}

	/**
	 * 获取properties方法
	 * 
	 * @return
	 */
	public static Properties getProperties(String path) {
		Properties prop = null;
		InputStream inStream = null;
		try {
			prop = new Properties();
			inStream = new ClassPathResource(path).getInputStream();
			prop.load(inStream);
			inStream.close();
		} catch (Exception e) {
			log.warn("获取properties文件失败", e);
		}
		return prop;
	}

}