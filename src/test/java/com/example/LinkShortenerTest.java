package test.java.com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkShortenerTest {

    @Test
    public void testShortenAndExpandUrl() {
        LinkShortener linkShortener = new LinkShortener();
        String longUrl = "https://www.example.com";
        String shortUrl = linkShortener.shortenUrl(longUrl);
        String expandedUrl = linkShortener.expandUrl(shortUrl);

        assertEquals(longUrl, expandedUrl);
    }

    @Test
    public void testShortenUrlErrorHandling() {
        LinkShortener linkShortener = new LinkShortener();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            linkShortener.shortenUrl(null);
        });
        assertEquals("Invalid URL!", exception.getMessage());
    }

    @Test
    public void testExpandUrlErrorHandling() {
        LinkShortener linkShortener = new LinkShortener();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            linkShortener.expandUrl("invalid_short_url");
        });
        assertEquals("Invalid short URL!", exception.getMessage());
    }
}
