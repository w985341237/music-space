package com.neil.musicspace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.neil.musicspace.mybatis.dao"})
public class MusicSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicSpaceApplication.class, args);
    }

}
