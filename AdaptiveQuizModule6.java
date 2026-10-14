
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;

class QuizQuestion {
    private String text;
    private int answer;
    private String difficulty;

    public QuizQuestion(String text, int answer, String difficulty) {
        this.text = text;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public int getAnswer() {
        return answer;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

class QuizResult {
    private String question;
    private String difficulty;
    private boolean correct;

    public QuizResult(String question, String difficulty,
                      boolean correct) {
        this.question = question;
        this.difficulty = difficulty;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isCorrect() {
        return correct;
    }
}

public class AdaptiveQuizModule6 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // 1. ArrayList: store quiz results
        List<QuizResult> results = new ArrayList<>();

        // 2. HashMap: store question banks by difficulty
        Map<String, List<QuizQuestion>> questionBank =
                new HashMap<>();

        questionBank.put("Easy", new ArrayList<>(Arrays.asList(
                new QuizQuestion("2 + 2 = ?", 4, "Easy"),
                new QuizQuestion("5 - 3 = ?", 2, "Easy")
        )));

        questionBank.put("Medium", new ArrayList<>(Arrays.asList(
                new QuizQuestion("6 x 7 = ?", 42, "Medium"),
                new QuizQuestion("9 + 8 = ?", 17, "Medium")
        )));

        questionBank.put("Hard", new ArrayList<>(Arrays.asList(
                new QuizQuestion("12 x 12 = ?", 144, "Hard"),
                new QuizQuestion("144 / 12 = ?", 12, "Hard")
        )));

        // 3. HashSet: store unique questions
        Set<String> uniqueQuestions = new HashSet<>();

        for (List<QuizQuestion> list : questionBank.values()) {
            for (QuizQuestion q : list) {
                uniqueQuestions.add(q.getText());
            }
        }

        // 4. TreeSet: keep difficulty names in sorted order
        Set<String> orderedDifficulties = new TreeSet<>(
                questionBank.keySet()
        );

        System.out.println("=== ADAPTIVE QUIZ ENGINE ===");
        System.out.println("Available levels: " + orderedDifficulties);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        // Supplier: provides a message when requested
        Supplier<String> welcome =
                () -> "Welcome, " + name + "!";

        System.out.println(welcome.get());

        String difficulty = "Medium";
        int totalQuestions = 5;

        // 5. Quiz loop
        for (int i = 0; i < totalQuestions; i++) {

            List<QuizQuestion> currentList =
                    questionBank.get(difficulty);

            QuizQuestion q = currentList.get(
                    i % currentList.size()
            );

            System.out.println("\nQuestion " + (i + 1));
            System.out.println("Difficulty: " + difficulty);
            System.out.println(q.getText());
            System.out.print("Your answer: ");

            int userAnswer;

            try {
                userAnswer = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                sc.nextLine();
                userAnswer = Integer.MIN_VALUE;
            }

            boolean correct = userAnswer == q.getAnswer();

            // Add result to the ArrayList
            results.add(new QuizResult(
                    q.getText(), difficulty, correct
            ));

            if (correct) {
                System.out.println("Correct!");

                if (difficulty.equals("Easy")) {
                    difficulty = "Medium";
                } else if (difficulty.equals("Medium")) {
                    difficulty = "Hard";
                }
            } else {
                System.out.println(
                        "Wrong! Correct answer: " + q.getAnswer()
                );

                if (difficulty.equals("Hard")) {
                    difficulty = "Medium";
                } else if (difficulty.equals("Medium")) {
                    difficulty = "Easy";
                }
            }
        }

        // 6. Predicate: select correct answers
        Predicate<QuizResult> isCorrect =
                r -> r.isCorrect();

        List<QuizResult> correctResults = results.stream()
                .filter(isCorrect)
                .collect(Collectors.toList());

        // 7. Function: convert a result into report text
        Function<QuizResult, String> formatResult =
                r -> r.getDifficulty() + ": " + r.getQuestion()
                        + " - " + (r.isCorrect()
                        ? "Correct" : "Incorrect");

        // 8. Consumer: display each result
        Consumer<QuizResult> printResult =
                r -> System.out.println(formatResult.apply(r));

        System.out.println("\n=== ANSWER REPORT ===");

        results.forEach(printResult);

        // 9. Count correct answers
        long correctCount = results.stream()
                .filter(isCorrect)
                .count();

        // Autoboxing: int converted to Integer
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("Correct", (int) correctCount);
        scoreMap.put("Incorrect",
                results.size() - scoreMap.get("Correct"));

        // 10. Reduce: calculate total correct answers
        int totalCorrect = results.stream()
                .map(r -> r.isCorrect() ? 1 : 0)
                .reduce(0, Integer::sum);

        // 11. Comparator and sorted()
        Comparator<QuizResult> byQuestion =
                Comparator.comparing(QuizResult::getQuestion);

        List<QuizResult> sortedResults = results.stream()
                .sorted(byQuestion)
                .collect(Collectors.toList());

        // 12. distinct(), limit() and method reference
        System.out.println("\n=== SAMPLE UNIQUE QUESTIONS ===");

        results.stream()
                .map(QuizResult::getQuestion)
                .distinct()
                .limit(3)
                .forEach(System.out::println);

        // 13. groupingBy() and counting()
        Map<String, Long> levelSummary = results.stream()
                .collect(Collectors.groupingBy(
                        QuizResult::getDifficulty,
                        TreeMap::new,
                        Collectors.counting()
                ));

        // 14. joining(): build a summary string
        String questionSummary = results.stream()
                .map(QuizResult::getQuestion)
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println("\n=== FINAL SUMMARY ===");
        System.out.println("Student: " + name);
        System.out.println("Score: " + totalCorrect
                + "/" + totalQuestions);
        System.out.println("Correct: " + scoreMap.get("Correct"));
        System.out.println("Incorrect: " + scoreMap.get("Incorrect"));
        System.out.println("Correct answers counted: " + correctResults.size());
        System.out.println("Questions by difficulty: " + levelSummary);
        System.out.println("Questions attempted: " + questionSummary);

        // Display sorted results
        System.out.println("\n=== SORTED RESULTS ===");
        sortedResults.forEach(printResult);

        // 15. Write report to a file
        List<String> report = new ArrayList<>();

        report.add("Adaptive Quiz Report");
        report.add("Student: " + name);
        report.add("Score: " + totalCorrect + "/" + totalQuestions);
        report.add("Difficulty summary: " + levelSummary);

        results.stream()
                .map(formatResult)
                .forEach(report::add);

        Path reportPath = Paths.get("quiz_report.txt");
        Files.write(reportPath, report);

        // 16. Read file lines as a stream
        System.out.println("\n=== REPORT FROM FILE ===");

        try (Stream<String> lines = Files.lines(reportPath)) {
            lines.forEach(System.out::println);
        }

        System.out.println("\nReport saved to quiz_report.txt");

        sc.close();
    }
}
