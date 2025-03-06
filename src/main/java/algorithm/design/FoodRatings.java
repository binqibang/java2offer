package algorithm.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class FoodRatings {

    private static class FoodDetail {
        private final String name;
        private final String cuisine;
        private int rating;

        public FoodDetail(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FoodDetail that = (FoodDetail) o;
            return rating == that.rating && Objects.equals(name, that.name) && Objects.equals(cuisine, that.cuisine);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, cuisine, rating);
        }
    }

    private Map<String, FoodDetail> foodMap;
    private Map<String, TreeSet<FoodDetail>> ratingMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>();
        ratingMap = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            FoodDetail foodDetail = new FoodDetail(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], foodDetail);
            ratingMap.computeIfAbsent(cuisines[i], cuisine -> new TreeSet<>((a, b) -> {
                if (a.rating == b.rating) {
                    return a.name.compareTo(b.name);
                } else {
                    return b.rating - a.rating;
                }
            })).add(foodDetail);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodDetail foodDetail = foodMap.get(food);
        ratingMap.get(foodDetail.cuisine).remove(foodDetail);
        foodDetail.rating = newRating;
        foodMap.put(food, foodDetail);
        ratingMap.get(foodDetail.cuisine).add(foodDetail);
    }

    public String highestRated(String cuisine) {
        return ratingMap.get(cuisine).first().name;
    }
}
