package com.apisecurity;

import com.apisecurity.auth.HMACSignatureBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class JettyClient {

    public static void main(String[] args) throws IOException {
        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {
        String clientKey = "clientKey";

        String queryParams = "end-date=2016-01-01&start-date=2015-02-02";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8080/test?" + queryParams);
        httpGet.addHeader("User-Agent", "TestingAgent");
        String requestDate = "Tue, 01 Jan 2013 19:00:00 +0000";
        httpGet.addHeader("Date", requestDate);

        HMACSignatureBuilder builder = new HMACSignatureBuilder(clientKey);
        String signature = builder.createSignature(requestDate + " " + queryParams);
        signature = URLEncoder.encode(signature, "utf-8");

        httpGet.addHeader("Auth", clientKey + ":" + signature);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        System.out.println("GET Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // print result
        System.out.println(response.toString());
        httpClient.close();
    }
}