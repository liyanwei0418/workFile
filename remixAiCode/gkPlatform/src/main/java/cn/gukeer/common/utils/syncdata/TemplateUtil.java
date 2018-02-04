
package cn.gukeer.common.utils.syncdata;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;

public class TemplateUtil {
     /**
	 * freemarker模板解析
	 * @param templateStr
	 * @param vObject
	 * @return
	 */
	public static String freemarker(String templateStr, Map<String, Object> vObject) {

		Template t;
		StringWriter stringWriter = new StringWriter();
		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		
		try {
			//freemarker在解析数据格式的时候，自动默认将数字按3为分割（1，000），需要禁用掉
			String disableNumberParserStr = "<#setting number_format=\"#\">";
			t = new Template("dao template parser",
					new StringReader(disableNumberParserStr + templateStr), cfg);

			t.process(vObject, stringWriter);
			stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取表达式内容出现异常", e);
		}
		return stringWriter.toString();
	}
}
