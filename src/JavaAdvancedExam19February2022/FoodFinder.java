package JavaAdvancedExam19February2022;

import java.util.*;
import java.util.stream.Collectors;

public class FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = new String[]{"pear", "flour", "pork", "olive"};
        String[] wordsToBeFound = new String[]{"****", "*****", "****", "*****"};

        Queue<Character> vowels = new ArrayDeque<>();
        Stack<Character> consonants = new Stack<>();

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Arrays.stream(firstLine.split(" ")).forEach(c -> vowels.offer(c.charAt(0)));
        Arrays.stream(secondLine.split(" ")).forEach(c -> consonants.push(c.charAt(0)));

        while(!consonants.isEmpty()) {
            char vowelQueue = vowels.poll();
            char consonantStack = consonants.pop();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int indexOfVowel = word.indexOf(vowelQueue);
                int indexOfConsonant = word.indexOf(consonantStack);

                if (indexOfVowel >=0 ) {
                    wordsToBeFound[i] = wordsToBeFound[i].substring(0, indexOfVowel) + vowelQueue + wordsToBeFound[i].substring(indexOfVowel + 1);
                }

                if (indexOfConsonant >= 0) {
                    wordsToBeFound[i] = wordsToBeFound[i].substring(0, indexOfConsonant) + consonantStack + wordsToBeFound[i].substring(indexOfConsonant + 1);
                }
            }
            vowels.offer(vowelQueue);
        }
        List<String> foundWords = Arrays.stream(wordsToBeFound).filter(w -> !w.contains("*")).collect(Collectors.toList());

        System.out.printf("Words found: %d%n", foundWords.size());
        foundWords.forEach(System.out::println);

    }
}
