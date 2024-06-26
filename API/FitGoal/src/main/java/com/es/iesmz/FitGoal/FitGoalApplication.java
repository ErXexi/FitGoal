package com.es.iesmz.FitGoal;

import com.es.iesmz.transita3.Transita.Utils.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FitGoalApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(FitGoalApplication.class, args);
        Util.readConfigFile();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(FitGoalApplication.class);
    }
}
