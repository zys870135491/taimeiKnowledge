package com.zys.taimeiknowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TaimeiKnowledgeMain {

    public static void main(String[] args) {
        System.out.println("start --------------->");
        SpringApplication.run(TaimeiKnowledgeMain.class,args);
        System.out.println("end --------------->");
    }
}
