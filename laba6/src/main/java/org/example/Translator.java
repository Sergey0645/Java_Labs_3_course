package org.example;

import java.util.HashMap;

public class Translator {

    private HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.trim().toLowerCase(), ukrainian.trim());
    }

    public String translate(String phrase) {
        String[] words = phrase.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String translation = dictionary.getOrDefault(
                    word.toLowerCase(),
                    word
            );

            result.append(translation).append(" ");
        }

        return result.toString().trim();
    }
}