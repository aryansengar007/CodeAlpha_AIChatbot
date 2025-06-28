package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NLPProcessor {

    private static final Set<String> stopWords = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "am", "i", "you", "he", "she", "it", "we", "they",
            "to", "of", "in", "and", "on", "how", "what", "why", "can", "could", "should", "would"));

    public static String clean(String text) {
        return text.toLowerCase().replaceAll("[^a-z0-9\\s]", "").trim();
    }

    public static List<String> tokenize(String text) {
        return Arrays.stream(clean(text).split("\\s+"))
                .filter(token -> !stopWords.contains(token))
                .collect(Collectors.toList());
    }

    public static Map<String, Double> computeTF(List<String> tokens) {
        Map<String, Double> tf = new HashMap<>();
        for (String word : tokens) {
            tf.put(word, tf.getOrDefault(word, 0.0) + 1.0);
        }
        int total = tokens.size();
        tf.replaceAll((k, v) -> v / total);
        return tf;
    }

    public static double computeSimilarity(String text1, String text2) {
        List<String> tokens1 = tokenize(text1);
        List<String> tokens2 = tokenize(text2);

        Map<String, Double> tf1 = computeTF(tokens1);
        Map<String, Double> tf2 = computeTF(tokens2);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(tf1.keySet());
        allWords.addAll(tf2.keySet());

        double dot = 0.0, mag1 = 0.0, mag2 = 0.0;
        for (String word : allWords) {
            double v1 = tf1.getOrDefault(word, 0.0);
            double v2 = tf2.getOrDefault(word, 0.0);
            dot += v1 * v2;
            mag1 += v1 * v1;
            mag2 += v2 * v2;
        }

        return (mag1 == 0 || mag2 == 0) ? 0.0 : dot / (Math.sqrt(mag1) * Math.sqrt(mag2));
    }
}