package main.java.com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> urlMap;
    private Map<String, String> reverseUrlMap;
    private static final String BASE_URL = "http://short.url/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int URL_LENGTH = 6;

    public LinkShortener() {
        urlMap = new HashMap<>();
        reverseUrlMap = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            throw new IllegalArgumentException("Invalid URL!");
        }
        if (reverseUrlMap.containsKey(longUrl)) {
            return BASE_URL + reverseUrlMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        while (urlMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl();
        }

        urlMap.put(shortUrl, longUrl);
        reverseUrlMap.put(longUrl, shortUrl);
        return BASE_URL + shortUrl;
    }

    public String expandUrl(String shortUrl) {
        if (shortUrl == null || shortUrl.isEmpty() || !shortUrl.startsWith(BASE_URL)) {
            throw new IllegalArgumentException("Invalid short URL!");
        }
        String key = shortUrl.replace(BASE_URL, "");
        return urlMap.getOrDefault(key, "URL not found!");
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(URL_LENGTH);
        Random random = new Random();
        for (int i = 0; i < URL_LENGTH; i++) {
            shortUrl.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return shortUrl.toString();
    }
}
