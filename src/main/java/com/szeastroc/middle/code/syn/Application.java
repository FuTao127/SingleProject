package com.szeastroc.middle.code.syn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.szeastroc")
@Slf4j
//@PropertySource({"file:/var/server-config/otoc/otoc-realtime-syn/application.properties"})
@PropertySource({"file:E:\\application.properties"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("service start success");
    }

}
