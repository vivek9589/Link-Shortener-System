






import java.util.*;

public class LinkShortener {
    private static final String BASE_URL = "http://short.ly/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    private final Map<String, String> shortToLongMap = new HashMap<>();
    private final Map<String, String> longToShortMap = new HashMap<>();
    private final Random random = new Random();

    // Method to shorten a long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return BASE_URL + longToShortMap.get(longURL);
        }

        String shortKey;
        do {
            shortKey = generateShortKey();
        } while (shortToLongMap.containsKey(shortKey));

        shortToLongMap.put(shortKey, longURL);
        longToShortMap.put(longURL, shortKey);

        return BASE_URL + shortKey;
    }

    // Method to expand a short URL back to the original
    public String expandURL(String shortURL) {
        if (!shortURL.startsWith(BASE_URL)) {
            return "Invalid short URL format!";
        }

        String shortKey = shortURL.substring(BASE_URL.length());
        return shortToLongMap.getOrDefault(shortKey, "Short URL not found!");
    }

    // Generate a random short key
    private String generateShortKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            key.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return key.toString();
    }
}
