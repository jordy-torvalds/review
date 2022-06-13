package com.kis.searchaddress.api.helper;

import com.kis.searchaddress.api.constants.ApiConfig;
import com.kis.searchaddress.api.constants.ApiKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import static com.kis.searchaddress.api.helper.TypeConverter.mapToHttpParams;

@Component
@Slf4j
public class KakaoRestApiHelper {

    @Inject
    public ApiKey apiKey;

    public String request(final String apiPath) {
        return request(HttpMethod.GET, apiPath, null);
    }

    public String request(final HttpMethod httpMethod, final String apiPath) {
        return request(httpMethod, apiPath, null);
    }

    public String request(HttpMethod httpMethod, final String apiPath, final Map<String, Object> params) {

        String requestUrl = ApiConfig.API_SERVER_HOST + apiPath + ".json";
        String paramStr = mapToHttpParams(params);

        if (httpMethod == null) {
            httpMethod = HttpMethod.GET;
        }

        if (params != null && !params.isEmpty()
                && (httpMethod == HttpMethod.GET || httpMethod == HttpMethod.DELETE)) {
            requestUrl += "?";
            requestUrl += paramStr;
        }

        HttpsURLConnection conn;
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        try {
            final URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toString());

            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey.getAdminKey());

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (params != null && paramStr.length() > 0 && httpMethod == HttpMethod.POST) {
                conn.setDoOutput(true);
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(paramStr);
                writer.flush();
            }

            final int responseCode = conn.getResponseCode();
            log.info(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
            log.info("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream());
            else
                isr = new InputStreamReader(conn.getErrorStream());

            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            log.info(buffer.toString());
            return buffer.toString();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
            if (reader != null) try { reader.close(); } catch (Exception ignore) { }
            if (isr != null) try { isr.close(); } catch (Exception ignore) { }
        }

        return null;
    }
}
