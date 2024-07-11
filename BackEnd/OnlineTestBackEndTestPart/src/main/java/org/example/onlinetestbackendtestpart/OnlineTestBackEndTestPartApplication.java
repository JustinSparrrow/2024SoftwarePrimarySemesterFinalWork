package org.example.onlinetestbackendtestpart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "org.example.onlinetestbackendtestpart.Mapper")
@SpringBootApplication
public class OnlineTestBackEndTestPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTestBackEndTestPartApplication.class, args);
    }

}
