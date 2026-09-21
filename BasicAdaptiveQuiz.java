import java.util.Scanner;
public class BasicAdaptiveQuiz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalQuestions = 5;
        int score = 0;
        String difficulty = "Medium"; // starting level

        // Simple parallel arrays of questions per difficulty
        String[] easyQ = {"2 + 2 = ?", "5 - 3 = ?"};
        String[] mediumQ = {"6 x 7 = ?", "9 + 8 = ?"};
        String[] hardQ = {"12 x 12 = ?", "144 / 12 = ?"};

        int[] easyA = {4, 2};
        int[] mediumA = {42, 17};
        int[] hardA = {144, 12};

        int easyIndex = 0, mediumIndex = 0, hardIndex = 0;

        System.out.println("=== Basic Adaptive Quiz ===");

        // ---- ITERATION: fixed number of rounds ----
        for (int i = 1; i <= totalQuestions; i++) {

            String question;
            int correctAnswer;

            // ---- SELECTION: choose question based on current difficulty ----
            if (difficulty.equals("Easy")) {
                question = easyQ[easyIndex % easyQ.length];
                correctAnswer = easyA[easyIndex % easyA.length];
                easyIndex++;
            } else if (difficulty.equals("Medium")) {
                question = mediumQ[mediumIndex % mediumQ.length];
                correctAnswer = mediumA[mediumIndex % mediumA.length];
                mediumIndex++;
            } else {
                question = hardQ[hardIndex % hardQ.length];
                correctAnswer = hardA[hardIndex % hardA.length];
                hardIndex++;
            }

            System.out.println("\nQ" + i + " [" + difficulty + "]: " + question);
            System.out.print("Your answer: ");
            int userAnswer = sc.nextInt();

            // ---- SELECTION: check answer and adapt difficulty ----
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!");
                score++;
                if (difficulty.equals("Easy")) {
                    difficulty = "Medium";
                } else if (difficulty.equals("Medium")) {
                    difficulty = "Hard";
                }
            } else {
                System.out.println("Wrong! Correct answer was " + correctAnswer);
                if (difficulty.equals("Hard")) {
                    difficulty = "Medium";
                } else if (difficulty.equals("Medium")) {
                    difficulty = "Easy";
                }
            }
        }

        System.out.println("\n=== Quiz Finished ===");
        System.out.println("Score: " + score + "/" + totalQuestions);
        sc.close();
    }
}
