package com.kis.searchaddress.api.helper;

import com.kis.searchaddress.api.constants.ApiConfig;
import com.kis.searchaddress.api.constants.ApiKey;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Map;

import static com.kis.searchaddress.api.helper.TypeConverter.mapToHttpParams;

@Component
@Slf4j
public class WebClientHelper {
    @Inject
    public ApiKey apiKey;

    private WebClient webClient;

    @PostConstruct
    public void initWebClient() {
        webClient = WebClient.builder().baseUrl(ApiConfig.API_SERVER_HOST)
                .defaultHeader("Authorization","KakaoAK " + apiKey.getAdminKey())
                .defaultHeader("Content-Type","application/json; charset=utf-8")
                .filter(logFilter())
                .build();
    }

    private ExchangeFilterFunction logFilter() {
        return (clientRequest, next) -> {
            log.info("Request URL {}", clientRequest.url());
            log.info("Header {}",clientRequest.headers());
            log.info("Body {}",clientRequest.body());
            return next.exchange(clientRequest);
        };
    }

    public String request(final String apiPath) {
        return request(HttpMethod.GET, apiPath, null);
    }

    public String request(final HttpMethod httpMethod, final String apiPath) {
        return request(httpMethod, apiPath, null);
    }

    public String request(HttpMethod httpMethod, final String apiPath, final Map<String, Object> params) {
        if (httpMethod == HttpMethod.POST) {
            return postRequest(apiPath, params);
        } else {    //GET
            return getRequest(apiPath, params);
        }
    }

    public String getRequest(final String apiPath, final Map<String, Object> params) {
        StringBuilder requestUrl = new StringBuilder();
        String paramStr = mapToHttpParams(params);

        requestUrl.append(apiPath + ".json");
        requestUrl.append("?");
        requestUrl.append(paramStr);
        return webClient.get()
                .uri(requestUrl.toString()) // review: uri 생성시 uri builder 와 multivalueMap을 잘 혼합해서 사용하면 문자열 조작을 하지 않고도 직관적으로 쿼리파람을 달 수 있을거 같아요 ㅋㅋ
                /**
                - webclient querystring 관련: https://www.baeldung.com/webflux-webclient-parameters#single-value-parameters
                - UriBuilder.queryParam: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/UriBuilder.html#queryParams-org.springframework.util.MultiValueMap-
                - LinkedMultiValueMap map param 생성자 : https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/LinkedMultiValueMap.html#LinkedMultiValueMap-java.util.Map-
                 */
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String postRequest(final String apiPath, final Map<String, Object> params) {
        String requestUrl = apiPath + ".json";
        String paramStr = mapToHttpParams(params);
        return webClient.post()
                .uri(requestUrl)
                .body(paramStr, String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
