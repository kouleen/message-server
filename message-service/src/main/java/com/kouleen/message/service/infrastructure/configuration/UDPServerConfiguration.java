package com.kouleen.message.service.infrastructure.configuration;

import cn.hutool.poi.excel.WorkbookUtil;
import com.kouleen.message.api.infrastructure.constants.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.integration.ip.dsl.UdpInboundChannelAdapterSpec;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author zhangqing
 * @since 2023/8/24 14:17
 */
@Slf4j
@Configuration
public class UDPServerConfiguration {

    @Value("${server.port}")
    private int port;

    @Bean
    public IntegrationFlow integrationFlow(){
        log.info("UDP服务启动成功，端口号为: {}", port);
        UdpInboundChannelAdapterSpec udpInboundChannelAdapterSpec = Udp.inboundAdapter(port);
        IntegrationFlowBuilder integrationFlowBuilder = IntegrationFlows.from(udpInboundChannelAdapterSpec);
        IntegrationFlowBuilder integrationFlowBuilder1 = integrationFlowBuilder.channel(MessageConstant.UDP_CHANNEL);
        return integrationFlowBuilder1.get();
    }

    @Transformer(inputChannel = MessageConstant.UDP_CHANNEL, outputChannel = MessageConstant.UDP_FILTER)
    public byte[] transformer(@Payload byte[] payload,@Headers Map<String, Object> headers){

        return payload;
    }

    @Filter(inputChannel = MessageConstant.UDP_FILTER, outputChannel = MessageConstant.UDP_ROUTER)
    public boolean filter(byte[] payload, @Headers Map<String, Object> headers){
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        // todo 信息数据过滤
        return true;
    }

    @Router(inputChannel = MessageConstant.UDP_ROUTER)
    public String router(byte[] payload, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        // todo 筛选，走那个处理器
        if (new String(payload).equals("OK")) {
            return "udpHandle2";
        }
        return "udpHandle1";
    }

    @ServiceActivator(inputChannel = "udpHandle1")
    public void udpMessageHandle(byte[] payload) throws Exception {
        // todo 可以进行异步处理
        log.info("message:" + new String(payload, Charset.defaultCharset()));

        FileOutputStream fileOutputStream = new FileOutputStream("easypoi.docx",true);
        fileOutputStream.write(payload);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @ServiceActivator(inputChannel = "udpHandle2")
    public void udpMessageHandle2(byte[] payload) throws Exception {
        log.info("UDP2:" + new String(payload));
    }
}
