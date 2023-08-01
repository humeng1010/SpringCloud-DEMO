package cn.wuluwulu.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
public class GatewayTest {
    @Test
    public void testGetTime(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    //    2023-08-01T12:11:07.391+08:00[Asia/Shanghai]
    }
}
