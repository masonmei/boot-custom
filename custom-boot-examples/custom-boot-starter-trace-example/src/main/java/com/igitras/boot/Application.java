package com.igitras.boot;

import com.igitras.boot.common.RequestInfoHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mason on 10/30/15.
 */
@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = {"home"}, method = RequestMethod.GET)
    public String home() {
        return String.format("Trace home with id %s start at %s",
                RequestInfoHolder.getThreadTraceId(),
                RequestInfoHolder.getThreadTraceTimestamp());
    }
}
