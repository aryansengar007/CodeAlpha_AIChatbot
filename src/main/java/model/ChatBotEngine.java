package model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import utils.NLPProcessor;

public class ChatBotEngine {

    private final Map<String, String> faqMap = new HashMap<>();

    public ChatBotEngine() {
        loadFAQs("/faqs.json");
    }

    private void loadFAQs(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            JSONParser parser = new JSONParser();
            JSONArray faqs = (JSONArray) parser.parse(new InputStreamReader(is));

            for (Object obj : faqs) {
                JSONObject entry = (JSONObject) obj;
                String q = (String) entry.get("question");
                String a = (String) entry.get("answer");

                faqMap.put(q, a);
            }
        } catch (Exception e) {
            System.err.println("Failed to load FAQs: " + e.getMessage());
        }
    }

    public String getResponse(String userInput) {
        userInput = userInput.toLowerCase().trim();

        if (faqMap.containsKey(userInput)) {
            return faqMap.get(userInput);
        }

        String bestMatch = getBestMatchingQuestion(userInput);
        if (bestMatch != null) {
            return faqMap.get(bestMatch);
        }

        return "I'm still learning! Try rephrasing or ask something else.";
    }

    private String getBestMatchingQuestion(String userInput) {
        double bestScore = 0.0;
        String bestMatch = null;

        for (String question : faqMap.keySet()) {
            double score = NLPProcessor.computeSimilarity(userInput, question);
            if (score > bestScore && score >= 0.3) {
                bestScore = score;
                bestMatch = question;
            }
        }

        return bestMatch;
    }
}