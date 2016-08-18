package org.michaelliu.http.client.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michael on 6/30/16.
 */
public class HttpTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 150; i++) {
            executorService.execute(new RequestThread());
        }
        executorService.shutdown();
    }

}
