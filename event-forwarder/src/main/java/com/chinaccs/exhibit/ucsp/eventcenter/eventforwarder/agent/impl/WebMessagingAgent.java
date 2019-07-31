package com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.entity.EventForwardLogEntity;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.DateUtils;
import com.chinaccs.exhibit.ucsp.eventcenter.eventdata.utils.StringUtil;
import com.chinaccs.exhibit.ucsp.eventcenter.eventforwarder.agent.MessagingAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("webMessagingAgent")
public class WebMessagingAgent implements MessagingAgent {

    @Value("${internal-api.web-msg.url}")
    private String url;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void invoke(EventForwardLogEntity forwardLogEntity) throws UnsupportedEncodingException {
        logger.debug("WebMessagingAgent.invoke()");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(20000)
                .setConnectionRequestTimeout(10000)
                .build());

        httpPost.addHeader("Content-Type", "application/json");

        String payLoad = JSON.toJSONString(this.buildPayload(forwardLogEntity));
        httpPost.setEntity(new StringEntity(payLoad));

        String context = StringUtils.EMPTY;
        CloseableHttpResponse response = null;
        try {
            logger.debug("send request to {}", url);
            logger.debug("payload: {}", payLoad);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            context = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            logger.debug("response: {}", context);
        } catch (Exception e) {
            logger.debug("error", e);
            e.getStackTrace();
        } finally {
            try {
                response.close();
                httpPost.abort();
                httpClient.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

    }

    private Map<String, Object> buildPayload(EventForwardLogEntity forwardLogEntity){
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("appCode", forwardLogEntity.getAppCode());
        data.put("typeId", forwardLogEntity.getTypeId());
        data.put("level", forwardLogEntity.getLevel());
        data.put("title", forwardLogEntity.getTitle());
        data.put("message", forwardLogEntity.getMessage());
//        data.put("occurTime", forwardLogEntity.getOccurTime());
        data.put("occurTime", DateUtils.format(forwardLogEntity.getOccurTime(), DateUtils.DATE_TIME_PATTERN));
//        data.put("target", forwardLogEntity.getTargets());
        List<Map<String, Object>> targets = JSON.parseArray(forwardLogEntity.getTargets()).toJavaList((Class<Map<String,Object>>)(Class)Map.class);
        List<String> targetIds = targets.stream().map(x -> Convert.toStr(x.get("id"))).collect(Collectors.toList());
        data.put("target", String.join(",", targetIds));

        return data;
    }
}
