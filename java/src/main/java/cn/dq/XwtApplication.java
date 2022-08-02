/*
* 嘟旗信科集团
* 开放平台API对接示例 JAVA版本
* 作者：GAVIN
* */

package cn.dq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class XwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(XwtApplication.class,args);
    }
}
