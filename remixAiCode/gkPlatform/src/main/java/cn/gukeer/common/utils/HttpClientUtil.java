package cn.gukeer.common.utils;

import cn.gukeer.common.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class HttpClientUtil {
	private static Log logger = LogFactory.getLog(HttpClientUtil.class);

	public static String getContent(String url, String encode) {
		return getContent(url, encode, null);
	}
	
	public static String getContent(String url, String encode, Integer connectionTimeoutSeconds) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		HttpParams httparams = null;
		if(connectionTimeoutSeconds != null) {
			httparams= new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httparams, connectionTimeoutSeconds * 1000);
			HttpConnectionParams.setSoTimeout(httparams, connectionTimeoutSeconds * 1000);
		}
		HttpGet post = new HttpGet( url );
		post.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.1) ");
		post.setHeader("Content-Type", "text/html;encode=" + encode);
		return HttpClientUtil.getContent(HttpClientUtil.getInstance(httparams), post, null);
	}
	
	/**
	 * 以post方式抓取网页内容
	 * @param url
	 * @param params
	 * @return
	 * @throws BaseException 读取失败的话
	 */
	public static String postContent(String url, String encode, Map<String, String> params) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		return postContent(url, encode, params, 3, null);
	}
	
	public static String postContent(String url, String encode, String contentType, Map<String, String> params) {
		if(logger.isDebugEnabled()) {
			logger.debug("开始抓取网页：" + url);
		}
		return postContent(url, encode, contentType, params, 3, null);
	}
	
	/**
	 * 以post方式抓取网页内容(带有cookie信息)
	 * @param url
	 * @param encode
	 * @param params
	 * @param connectionTimeoutSeconds
	 * @param
	 * @return
	 */
	public static String postContent(String url, String encode, Map<String, String> params, int connectionTimeoutSeconds, CookieStore cookieStore) {
		return postContent(url, encode, "application/x-www-form-urlencoded", params, connectionTimeoutSeconds, cookieStore);
	}
	
	/**
	 * 以post方式抓取网页内容(带有cookie信息)
	 * @param url
	 * @param encode
	 * @param contentType
	 * @param params
	 * @param connectionTimeoutSeconds
	 * @param
	 * @return
	 */
	public static String postContent(String url, String encode, String contentType, Map<String, String> params, int connectionTimeoutSeconds, CookieStore cookieStore) {
		logger.info("开始抓取网页：" + url);
		HttpParams httparams= new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httparams, connectionTimeoutSeconds * 1000);
		HttpConnectionParams.setSoTimeout(httparams, connectionTimeoutSeconds * 1000);
		
		DefaultHttpClient client = new DefaultHttpClient();

		HttpContext httpContext = null;
		if(cookieStore != null) {
			client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
			client.setCookieStore(cookieStore);
			
			httpContext = new BasicHttpContext();
			httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		}
		HttpPost post = new HttpPost( url );
		post.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.1) ");
		post.setHeader("Content-Type", contentType);
		setParameters(post, params);
		
		return getContent(client, post, httpContext);
	}
	
	public static void setParameters(HttpPost post, Map<String, String> params) {
		if(params != null && !params.isEmpty()) {
			List<NameValuePair> clientParams = new ArrayList<NameValuePair>();
			for (Entry<String, String> param : params.entrySet()) {
				clientParams.add(new BasicNameValuePair( param.getKey() , param.getValue() ));
				logger.info(param.getKey() + "=" + param.getValue());
			}
			try {
				post.setEntity(new UrlEncodedFormEntity(clientParams, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.debug("编码失败", e);
			}
		}
	}
	
	
	public static String getContent(HttpClient client, HttpPost post, HttpContext httpContext) {
		try {
			HttpResponse response;
			if(httpContext != null) {
				response = client.execute(post, httpContext);
			}else{
				response = client.execute(post);
			}
			
			String result = EntityUtils.toString(response.getEntity());
			if(logger.isDebugEnabled()) {
				logger.debug("抓取网页内容：" + result);
			}
			//System.out.println("-----------------------" + ((DefaultHttpClient)client).getCookieStore().getCookies());
			return result;
		} catch (Exception e) {
			throw new BaseException("读取网页"+ post.getURI() +"出错", e);
		} finally{
			client.getConnectionManager().shutdown();
		}
	}
	
	public static String getContent(HttpClient client, HttpGet get, HttpContext httpContext) {
		try {
			HttpResponse response;
			if(httpContext != null) {
				response = client.execute(get, httpContext);
			}else{
				response = client.execute(get);
			}
			
			String result = EntityUtils.toString(response.getEntity());
			if(logger.isDebugEnabled()) {
				logger.debug("抓取网页内容：" + result);
			}
			//System.out.println("-----------------------" + ((DefaultHttpClient)client).getCookieStore().getCookies());
			return result;
		} catch (Exception e) {
			throw new BaseException("读取网页"+ get.getURI() +"出错", e);
		} finally{
			client.getConnectionManager().shutdown();
		}
	}

	public static DefaultHttpClient getInstance(HttpParams params) {
		DefaultHttpClient httpClient = new DefaultHttpClient(params);

		httpClient.setRedirectStrategy(new DefaultRedirectStrategy() {                
			public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context)  {
				boolean isRedirect=false;
				try {
					isRedirect = super.isRedirected(request, response, context);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!isRedirect) {
					int responseCode = response.getStatusLine().getStatusCode();
					if (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY) {
						return true;
					}
				}
				return isRedirect;
			}
		});
		return httpClient;
	}
	

	public static void main(String[] args) {

		Map map = new HashMap();
		map.put("id","420750171214129");
		map.put("name","袁曦");
		String ido = "4207501712";
		String id = "4207501712";
		String name ="陶怡然";
		for (int i = 100; i < 150 ; i++) {
			for (int j = 3; j < 45; j++) {
				if (j<10)
					id = id+String.valueOf(i)+"0"+String.valueOf(j);
				else
					id = id+String.valueOf(i)+String.valueOf(j);
				String aa = postContent("http://demo.zjmainstay.cn/php/get_cet_result.php?id="+id+"&name="+name,null,null,map);
				id= ido;
				System.out.println(aa);
			}
		}
	}


}
