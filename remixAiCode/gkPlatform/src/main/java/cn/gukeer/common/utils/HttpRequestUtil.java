package cn.gukeer.common.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HttpRequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
    private static int socketTimeout = 10000;//3秒
    private static int connectTimeout = 10000;//3秒
    private static Boolean setTimeOut = true;

    private static SSLContext buildSSLContext()
            throws NoSuchAlgorithmException, KeyManagementException,
            KeyStoreException {
        SSLContext sslcontext = org.apache.http.ssl.SSLContexts.custom()
                .setSecureRandom(new SecureRandom())
                .loadTrustMaterial(null, new TrustStrategy() {

                    public boolean isTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        return true;
                    }
                })
                .build();
        return sslcontext;
    }

    private static CloseableHttpClient getHttpClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        try {

            SSLContext sslcontext = buildSSLContext();

            SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    NoopHostnameVerifier.INSTANCE);

            registryBuilder.register("https", sslSf);

        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        //设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        //构建客户端
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }

    // http get
    public static String get(String url, Map<String, String> headers, Map<String, String> queries) throws IOException {

        //支持https
        CloseableHttpClient httpClient = getHttpClient();

        StringBuilder sb = new StringBuilder(url);

        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        HttpGet httpGet = new HttpGet(sb.toString());
        if (null != headers) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (setTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);
        }
        String rst = null;
        CloseableHttpResponse response = null;
        try {
            //请求数据
            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            logger.debug("code:%d, status:%s", response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase());
            if (entity != null) {
                rst = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (response != null)
                response.close();

            httpClient.close();
            httpGet.abort();
        }
        return rst;
    }

    //response_type=code&scope=baseInfo&client_id=hHTJiVN5&source=app&username=admin@xiao&password=000000

    public static void test(String fetchUrl) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(fetchUrl)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String rst = response.body().string();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        test("http://47.94.98.20:8080/platform/api/classcard/checkattendance/count");
       /* for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Map map = new HashMap();
                        map.put("time","12345");
                        map.put("macId","22:22:5C:B0:37:0A");
                        try {
                            String test = post("http://47.94.98.20:8080/platform/api/classcard/checkattendance/count",null,map,map);
                            System.out.println(test);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }*/

    }

    public static Integer a = 0;

    public static Integer getI() {
        a++;
        return a;
    }

    // http SSL post
    public static String post(String url, Map<String, String> headers, Map<String, String> queries, Map<String, String> params) throws IOException {

        //支持https
        CloseableHttpClient httpClient = getHttpClient();

        StringBuilder sb = new StringBuilder(url);

        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        HttpPost httpPost = new HttpPost(sb.toString());

        if (setTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
        }

        if (null != headers) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        List<NameValuePair> reqParams = new ArrayList<NameValuePair>();
        if (params != null && params.keySet().size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                reqParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(reqParams, Consts.UTF_8));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        String rst = null;
        try {

            HttpEntity entity = response.getEntity();
            logger.debug("code:%d, status:%s", response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase());
            if (entity != null) {
                rst = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
            httpClient.close();
            httpPost.abort();
        }
        return rst;
    }


    // http SSL post
    public static String delete(String url, Map<String, String> headers, Map<String, String> queries) throws IOException {

        //支持https
        CloseableHttpClient httpClient = getHttpClient();

        StringBuilder sb = new StringBuilder(url);

        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        HttpDelete httpDelete = new HttpDelete(sb.toString());

        if (setTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpDelete.setConfig(requestConfig);
        }

        if (null != headers) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpDelete);
        String rst = null;
        try {

            HttpEntity entity = response.getEntity();

            logger.debug("code:%d, status:%s", response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase());
            if (entity != null) {
                rst = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
            httpClient.close();
            httpDelete.abort();
        }
        return rst;
    }

    // http SSL post
    public static String post(String url, Map<String, String> headers, JSONPObject entityStr) throws IOException {

        //支持https
        CloseableHttpClient httpClient = getHttpClient();

        HttpPost httpPost = new HttpPost(url);

        if (setTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
        }

        if (null != headers) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        httpPost.setEntity(new StringEntity(entityStr.toString(), Consts.UTF_8));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String rst = null;
        try {

            HttpEntity entity = response.getEntity();

            logger.debug("code:%d, status:%s", response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase());
            if (entity != null) {
                rst = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
            httpClient.close();
            httpPost.abort();
        }
        return rst;
    }


    // http SSL post
    public static String delete4Json(String url, Map<String, String> headers) throws IOException {

        //支持https
        CloseableHttpClient httpClient = getHttpClient();

        HttpDelete httpDelete = new HttpDelete(url);

        if (setTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpDelete.setConfig(requestConfig);
        }

        if (null != headers) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpDelete);
        String rst = null;
        try {

            HttpEntity entity = response.getEntity();

            logger.debug("code:%d, status:%s", response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase());
            if (entity != null) {
                rst = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
            httpClient.close();
            httpDelete.abort();

        }
        return rst;
    }

    public static boolean verifyRequestValid(HttpServletRequest request) {
        //0wwwicanmakeitcnreverse
        String key = new String(Base64Utils.decode(Base64Utils.decodeFromString("Ym1OMGFXVnJZVzF1WVdOcGQzZDNNQT09")));

        boolean valid = false;

        String t = request.getParameter("t");
        String token = request.getHeader("token");

        return true;
    }
}
