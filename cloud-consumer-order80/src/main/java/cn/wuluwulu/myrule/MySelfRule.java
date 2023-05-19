package cn.wuluwulu.myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    /**
     * 替换默认的轮询规则
     * <p>
     * 默认的负载均衡轮询规则详细说明:
     * <p>
     * 访问次数 % 服务器台数 = 访问下标
     * <p>
     * 举例:
     * <p>
     * 两台服务器:xxx:8001,xxx:8002, 得到 List[0] = xxx:8002,List[1] = xxx:8001
     * <p>
     * 当请求次数是1: 1 % 2 = 1  ==>  List[1]  ==> xxx:8001
     * <p>
     * 当请求次数是2: 2 % 2 = 0  ==>  List[0]  ==> xxx:8002
     * <p>
     * 当请求次数是3: 3 % 2 = 1  ==>  List[1]  ==> xxx:8001
     * <p>
     * 当请求次数是4: 4 % 2 = 1  ==>  List[1]  ==> xxx:8001
     * <p>
     * ......
     * <p>
     * <p>
     * 下面是替换为:随机规则
     *
     * @return IRule
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();// 定义为随机
    }
}
