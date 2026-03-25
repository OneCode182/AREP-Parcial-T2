package org.example;
import jdk.jfr.Frequency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class HttpConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String SERVICE_EC2_N1 = "http://ec2-3-230-159-13.compute-1.amazonaws.com:8080";
    private final String SERVICE_EC2_N2 = "http://ec2-54-235-238-59.compute-1.amazonaws.com:8080";


    private final String baseService1 = "/linearsearch";
    private final String baseService2 = "/binarysearch";


    @GetMapping(baseService1)
    public String linearSearch(@RequestParam(value = "list") int[] items, @RequestParam(value = "value") int value) {
        try {
            return requestLinearSearch(SERVICE_EC2_N1, items, value);
        } catch (Exception e) {
            try {
                return requestLinearSearch(SERVICE_EC2_N2, items, value);
            } catch (Exception f) {
                String msg = "ERROR SERVER 1 and 2 | NO SERVICE AVALAIBLE";
                System.out.println(msg);
                return msg;
            }

        }
    }


    @GetMapping(baseService2)
    public String binarySearch(@RequestParam(value = "list") int[] items, @RequestParam(value = "value") int value) {
        try {
            return requestBinarySearch(SERVICE_EC2_N1, items, value);
        } catch (Exception e) {
            try {
                return requestBinarySearch(SERVICE_EC2_N2, items, value);
            } catch (Exception f) {
                String msg = "ERROR SERVER 1 and 2 | NO SERVICE AVALAIBLE";
                System.out.println(msg);
                return msg;
            }

        }
    }


    private String requestLinearSearch(String machine, int[] items, int value)  throws IOException {
        String url = "";
        if (machine.equals(SERVICE_EC2_N1)) {
            System.out.println("SERVER 1: LINEAR SEARCH");
            url = buildUrl(SERVICE_EC2_N1, baseService1, items, value);
        } else if (machine.equals(SERVICE_EC2_N2)) {
            System.out.println("SERVER 2: LINEAR SEARCH");
            url = buildUrl(SERVICE_EC2_N2, baseService1, items, value);
        }

        System.out.println("URL: " + url);

        StringBuffer respService = request(url);
        System.out.println(respService);
        return respService.toString();
    }


    private String requestBinarySearch(String machine, int[] items, int value)  throws IOException {
        String url = "";
        if (machine.equals(SERVICE_EC2_N1)) {
            System.out.println("SERVER 1: BINARY SEARCH");
            url = buildUrl(SERVICE_EC2_N1, baseService2, items, value);
        } else if (machine.equals(SERVICE_EC2_N2)) {
            System.out.println("SERVER 2: BINARY SEARCH");
            url = buildUrl(SERVICE_EC2_N2, baseService2, items, value);
        }

        StringBuffer respService = request(url);
        System.out.println(respService);
        return respService.toString();
    }



    private String intToStr(int[] items) {
        String data = "";
        for (int i = 0; i < items.length; i++) {
            if (i < items.length - 1) {
                data = data + items[i] + ",";
            } else {
                data = data + items[i];
            }
        }
        return data;
    }


    private String buildUrl(String urlService, String baseService, int[] items, int value) {

        return urlService + baseService + "?list=" + intToStr(items) + "&value=" + String.valueOf(value);
    }




    private StringBuffer request(String serviceUrl) throws IOException {

        URL obj = new URL(serviceUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response;
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");

        return new StringBuffer();

    }




}