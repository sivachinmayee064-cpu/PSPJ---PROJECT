import java.util.Scanner;

public class AdaptiveQuiz {

    // 2D array: questions for 3 difficulty levels
    // Row 0 = Easy, Row 1 = Medium, Row 2 = Hard
    static String[][] questions = {
        {"2 + 2 = ?", "5 - 3 = ?"},
        {"6 x 7 = ?", "9 + 8 = ?"},
        {"12 x 12 = ?", "144 / 12 = ?"}
    };

    // 2D array: correct answers
    static int[][] answers = {
        {4, 2},
        {42, 17},
        {144, 12}
    };

    // Method to display the current question
    static void displayQuestion(String question, String difficulty) {
        System.out.println("\n[" + difficulty + "] " + question);
    }

    // Method with parameters and return value
    static boolean checkAnswer(int userAnswer, int correctAnswer) {
        return userAnswer == correctAnswer;
    }

    // Method to change difficulty
    static int updateDifficulty(int difficulty, boolean correct) {

        if (correct && difficulty < 2) {
            difficulty++;
        } 
        else if (!correct && difficulty > 0) {
            difficulty--;
        }

        return difficulty;
    }

    // Method to find the highest value
    static int findHighest(int[] scores) {
        int highest = scores[0];

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
        }

        return highest;
    }

    // Method to count correct answers
    static int countCorrect(int[] results) {
        int count = 0;

        for (int result : results) {
            if (result == 1) {
                count++;
            }
        }

        return count;
    }

    // Method overloading
    static void showResult(int score) {
        System.out.println("Your score is: " + score);
    }

    static void showResult(int score, int total) {
        System.out.println("Your score is: " + score + "/" + total);
    }

    // Recursive method to calculate factorial
    static int factorial(int n) {

        // Base case
        if (n <= 1) {
            return 1;
        }

        // Recursive case
        return n * factorial(n - 1);
    }

    // Method using Math class
    static int calculatePercentage(int score, int total) {
        return (int) Math.round((score * 100.0) / total);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalQuestions = 5;
        int score = 0;

        // 0 = Easy, 1 = Medium, 2 = Hard
        int difficulty = 1;

        // Stores whether each answer was correct
        int[] results = new int[totalQuestions];

        System.out.println("=== ADAPTIVE QUIZ ===");

        // Iteration
        for (int i = 0; i < totalQuestions; i++) {

            // Select question using 2D array
            int questionIndex = i % 2;

            String question = questions[difficulty][questionIndex];
            int correctAnswer = answers[difficulty][questionIndex];

            String difficultyName;

            if (difficulty == 0) {
                difficultyName = "Easy";
            } 
            else if (difficulty == 1) {
                difficultyName = "Medium";
            } 
            else {
                difficultyName = "Hard";
            }

            displayQuestion(question, difficultyName);

            System.out.print("Your answer: ");
            int userAnswer = sc.nextInt();

            // Check answer using method
            boolean correct = checkAnswer(userAnswer, correctAnswer);

            if (correct) {
                System.out.println("Correct!");
                score++;
                results[i] = 1;
            } 
            else {
                System.out.println("Wrong!");
                System.out.println("Correct answer: " + correctAnswer);
                results[i] = 0;
            }

            // Update difficulty
            difficulty = updateDifficulty(difficulty, correct);
        }

        // Array processing
        int correctCount = countCorrect(results);

        System.out.println("\n=== QUIZ FINISHED ===");

        showResult(score, totalQuestions);

        int percentage = calculatePercentage(score, totalQuestions);
        System.out.println("Percentage: " + percentage + "%");

        System.out.println("Correct answers: " + correctCount);

        // Demonstrating Math class and recursion
        System.out.println("Example factorial: " + factorial(5));

        sc.close();
    }
}
