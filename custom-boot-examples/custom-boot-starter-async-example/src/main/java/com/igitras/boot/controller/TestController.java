package com.igitras.boot.controller;

import javafx.concurrent.Task;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by mason on 11/5/15.
 */
@RestController
public class TestController {

    @RequestMapping(value = "async/callable", method = RequestMethod.GET)
    public Callable<String> asyncCallable() {
        return () -> printCurrentTimestamp();
    }

    @RequestMapping(value = "async/future", method = RequestMethod.GET)
    public Future<String> asyncFuture() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                return printCurrentTimestamp();
            }
        };
    }

    @RequestMapping(value = "async/result", method = RequestMethod.GET)
    public AsyncResult<String> asyncResult() {
        return new AsyncResult<>(printCurrentTimestamp());
    }

    private String printCurrentTimestamp() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
        }
        return String.format("current time is : %d", System.currentTimeMillis());
    }

}
