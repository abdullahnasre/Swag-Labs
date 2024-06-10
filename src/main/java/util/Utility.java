package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Utility {

    public static List<Integer> getShuffledItemIndices() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        // Randomly select a subset of 1 to 6 items
        Random rand = new Random();
        int numberOfItems = rand.nextInt(6) + 1; // Ensure at least one item is selected

        return indices.subList(0, numberOfItems);
    }


        public static List<Integer> generateUniqueRandomNumbers(int count) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            return numbers;
        }
    public static float parsePriceFromString(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty");
        }

        // Remove leading and trailing whitespaces (optional)
        priceString = priceString.trim();

        // Check if the string starts with a dollar sign ($)
        if (!priceString.startsWith("$")) {
            throw new IllegalArgumentException("Price string must start with a dollar sign ($)");
        }

        // Extract the number part (everything after the dollar sign)
        String numberString = priceString.substring(1);

        // Parse the number string to a float
        try {
            return Float.parseFloat(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Please provide a valid number after the dollar sign ($)", e);
        }
    }


        public static void main(String[] args) {
            System.out.println(parsePriceFromString("$9.99"));
        }
}






















//    public static HashSet<Integer> generateRandomUniqueIntFrom1To6() {
//        HashSet<Integer> numbers = new HashSet<>();
//        Random random = new Random();
//        while (numbers.size() < 6) {
//            int t=random.nextInt(6) + 1;
//            System.out.println(t);
//            numbers.add(t);
//        }
//        return numbers; // Convert HashSet to List and pick the first element
//    }
//
//    public static void main(String[] args) {
//        System.out.println(generateRandomUniqueIntFrom1To6().size());
//
//        for(int i=0;i<6;i++)
//            System.out.println(new ArrayList<Integer>(generateRandomUniqueIntFrom1To6()));
//    }

