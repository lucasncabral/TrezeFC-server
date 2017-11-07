package controller;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FCMService {

    public FCMService(){
    }


    public final static String AUTH_KEY_FCM = "AAAACMY0lbw:APA91bFLFw9_y8U3p39vohfDzT2yTWiCOLqXheEfKoJoyq2qLyTAwSrRbFgHTZKYrPEUTRGl3c_yhT2TXxQqxJL-PqDzxilduB8uX1EvsuaD5KbDum9Cfc8vixIqB2XIIrbgn6Yn5GBk";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static boolean sendPushNotification(String deviceToken, String title, String body)
            throws IOException {
        boolean result = false;
        URL url = new URL(API_URL_FCM);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("to", deviceToken.trim());
        JSONObject info = new JSONObject();
        info.put("title", title); // Notification title
        info.put("body", body); // Notification
        // body
        json.put("notification", info);
        try {
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        System.out.println("GCM Notification is sent successfully");
        return result;
    }
}
