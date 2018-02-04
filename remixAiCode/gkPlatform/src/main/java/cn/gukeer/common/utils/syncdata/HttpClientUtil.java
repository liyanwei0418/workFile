package cn.gukeer.common.utils.syncdata;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private static Log logger = LogFactory.getLog(HttpClientUtil.class);
	
	/**
	 * 判断某个URL地址是否可用,超时时间为3秒
	 * @param url
	 * @return
	 */
	public static boolean isURLAvaiable(String url) {
		if(StringUtils.isEmpty(url)) {
			return false;
		}
		try{
			 URL u = new URL(url);
			 HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
			 httpURLConnection.setRequestMethod("HEAD");
			 httpURLConnection.setConnectTimeout(3000);

			 int status = httpURLConnection.getResponseCode();
			 logger.info(url + " Status Code: " + status);
			 httpURLConnection.disconnect();
			 return true;
		}catch(Exception ex){
			logger.error(url +"连接失败", ex);
			return false;
		}
	}
	/**
	 * 以post方式抓取网页内容
	 * @param url
	 * @param params
	 * @return
	 * @throws BaseException 读取失败的话
	 */
	public static String postContent(String url, String encode, Map<String, Object> params) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		return postContent(url, encode, params, 3);
	}
	
	public static String postContent(String url, String encode, String contentType, Map<String, Object> params) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		return postContent(url, encode, contentType, params, 3);
	}
	
	/**
	 * 以post方式抓取网页内容(带有cookie信息)
	 * @param url
	 * @param encode
	 * @param params
	 * @param connectionTimeoutSeconds
	 * @param request
	 * @return
	 */
	public static String postContent(String url, String encode, Map<String, Object> params, int connectionTimeoutSeconds) {
		return postContent(url, encode, "application/x-www-form-urlencoded", params, connectionTimeoutSeconds);
	}
	
	/**
	 * 以post方式抓取网页内容(带有cookie信息)
	 * @param url
	 * @param encode
	 * @param contentType
	 * @param params
	 * @param connectionTimeoutSeconds
	 * @param request
	 * @return
	 */
	public static String postContent(String url, String encode, String contentType, Map<String, Object> params, int connectionTimeoutSeconds) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeoutSeconds).build();
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.1) ");
		post.setHeader("Content-Type", contentType);
		post.setConfig(requestConfig);
		setParameters(post, params);
		return getContent(httpclient, post, encode);
	}
	
	public static void setParameters(HttpPost post, Map<String, Object> params) {
		if(params != null && !params.isEmpty()) {
			List<NameValuePair> clientParams = new ArrayList<NameValuePair>();
			for (Entry<String, Object> param : params.entrySet()) {
			    if(param.getValue() == null) {
			        continue;
			    }
				clientParams.add(new BasicNameValuePair( param.getKey() , String.valueOf(param.getValue())));
			}
			try {
				post.setEntity(new UrlEncodedFormEntity(clientParams, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.debug("编码失败", e);
			}
		}
	}
	
	
	public static String getContent(HttpClient client, HttpPost post,  String encode) {
		HttpResponse response = null;
		try {
			response = client.execute(post);
			if(response.getStatusLine().getStatusCode() != 200) {
				post.abort();
				logger.error("抓取网页【"+ post.getURI() +"】内容错误：" + response.getStatusLine().getStatusCode());
				return null;
			}
			String result = EntityUtils.toString(response.getEntity(), encode);
			if(logger.isDebugEnabled()) {
				logger.debug("抓取网页内容：" + result);
			}
			return result;
		} catch (Exception e) {
			post.abort();
			throw new RuntimeException("读取网页"+ post.getURI() +"出错", e);
		} finally{
			if(response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static String getContent(HttpClient client, HttpGet get, HttpContext httpContext) {
		HttpResponse response = null;
		try {
			if(httpContext != null) {
				response = client.execute(get, httpContext);
			}else{
				response = client.execute(get);
			}
			if(response.getStatusLine().getStatusCode() != 200) {
				get.abort();
				logger.error("抓取网页【"+ get.getURI() +"】内容错误：" + response.getStatusLine().getStatusCode());
				return null;
			}
			String result = EntityUtils.toString(response.getEntity());
			if(logger.isDebugEnabled()) {
				logger.debug("抓取网页内容：" + result);
			}
			return result;
		} catch (Exception e) {
			get.abort();
			throw new RuntimeException("读取网页"+ get.getURI() +"出错", e);
		} finally{
			if(response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



}
