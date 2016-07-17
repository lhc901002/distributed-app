package org.michaelliu.http.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michael on 6/30/16.
 */
public class RequestThread implements Runnable {

    public static AtomicInteger success_count = new AtomicInteger(0);

    @Override
    public void run() {
        String url = "http://localhost:8080/orm/record/increase?id=1";
//        String url = "http://localhost:8080/orm/record/autodecrease?id=1";
        for (int i = 0; i < 50; i++) {
            String rspString = new HttpRequester().setUrl(url).sendGetRequest();
            if (Integer.parseInt(rspString) > 0) {
                success_count.addAndGet(1);
            }
            System.out.println(Thread.currentThread().getId() + "\t" +
                    rspString + "\t" + success_count.intValue());
        }
    }

}
