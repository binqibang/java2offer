package algorithm;

import algorithm.design.Allocator;
import algorithm.design.BrowserHistory;
import algorithm.design.FoodRatings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesignTest {

    @Test
    void test_Allocator() {
        Allocator allocator = new Allocator(7);
        assertEquals(0, allocator.allocate(3, 1));
        assertEquals(-1, allocator.allocate(5, 2));
        assertEquals(3, allocator.freeMemory(1));
        assertEquals(0, allocator.freeMemory(2));
    }

    @Test
    void test_BrowserHistory() {
        BrowserHistory history = new BrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");
        assertEquals("facebook.com", history.back(1));
        assertEquals("google.com", history.back(1));
        assertEquals("facebook.com", history.forward(1));
        history.visit("linkedin.com");
        assertEquals("linkedin.com", history.forward(2));
        assertEquals("google.com", history.back(2));
        assertEquals("leetcode.com", history.back(7));
    }

    @Test
    void test_FoodRatings() {
        FoodRatings foodRatings = new FoodRatings(
                new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"},
                new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"},
                new int[]{9, 12, 8, 15, 14, 7}
        );
        assertEquals("kimchi", foodRatings.highestRated("korean"));
        assertEquals("ramen", foodRatings.highestRated("japanese"));
        foodRatings.changeRating("sushi", 15);
        assertEquals("sushi", foodRatings.highestRated("japanese"));
    }
}