package model;

import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class GPTService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static boolean isKeyAvailable() {
        return System.getenv("OPENAI_API_KEY") != null;
    }

    public static String getResponse(String userInput) {
        try {
            String apiKey = System.getenv("OPENAI_API_KEY");
            if (apiKey == null) {
                return "API key not found. Please set OPENAI_API_KEY in environment.";
            }

            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", userInput);

            JSONArray messages = new JSONArray();
            messages.add(message);

            JSONObject body = new JSONObject();
            body.put("model", "gpt-3.5-turbo");
            body.put("messages", messages);
            body.put("temperature", 0.7);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(body.toJSONString().getBytes());
                os.flush();
            }

            int status = connection.getResponseCode();
            if (status != 200) {
                return "Error from GPT API: HTTP " + status;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(in);
            in.close();

            JSONArray choices = (JSONArray) response.get("choices");
            JSONObject choice = (JSONObject) choices.get(0);
            JSONObject messageObj = (JSONObject) choice.get("message");
            return (String) messageObj.get("content");

        } catch (Exception e) {
            return "Error talking to GPT: " + e.getMessage();
        }
    }
}