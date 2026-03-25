package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class HttpConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

    private static final String SERVICE_LINEAR_SEARCH = "http://ec2-100-28-225-246.compute-1.amazonaws.com:8080/linearsearch?list=10,70,80,2&value=80";

    public static void main(String[] args) throws IOException {


        try {
            System.out.println("SERVER 1: LINEAR SEARCH");
            StringBuffer respService1 = request(SERVICE_LINEAR_SEARCH);
            System.out.println(respService1);

        } catch (Exception e) {
            System.out.println("ERROR SERVER 1");
        }

    }


    private static StringBuffer request(String serviceUrl) throws IOException {

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