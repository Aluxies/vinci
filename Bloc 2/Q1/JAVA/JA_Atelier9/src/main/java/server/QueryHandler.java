package server;

import domaine.Query;
import domaine.QueryImpl;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

public class QueryHandler extends Thread {

    private Query query;

    public QueryHandler( Query query ) {

        this.query = query;

    }

    public void run() {

        try {
            Thread.sleep( 10000 );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            ClassicHttpRequest httpGet = null;

            if ( this.query.getMethod() == Query.QueryMethod.GET ) {

                httpGet = ClassicRequestBuilder.get(this.query.getUrl())
                        .build();

            }

            httpclient.execute(httpGet, response -> {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                final HttpEntity entity1 = response.getEntity();
                System.out.println( EntityUtils.toString(entity1));
                EntityUtils.consume(entity1);
                return null;
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
