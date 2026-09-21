import java.util.Scanner;

class QuizEngine {

    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("       ADAPTIVE QUIZ & EXAM ENGINE");
        System.out.println("==========================================");

        System.out.print("Enter your name: ");
        String studentName = input.nextLine();

        final int TOTAL_QUESTIONS = 5;
        final double PASS_MARK = 50.0;

      
        int score = 0;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        int questionNumber = 1;

        // 1 = Easy
        // 2 = Medium
        // 3 = Hard
        int difficulty = 1;

        boolean quizStarted = true;
        while (questionNumber <= TOTAL_QUESTIONS) {

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Question " + questionNumber);

            // --------------------------------------
            // DISPLAY DIFFICULTY
            // --------------------------------------

            if (difficulty == 1) {
                System.out.println("Difficulty: EASY");
            }
            else if (difficulty == 2) {
                System.out.println("Difficulty: MEDIUM");
            }
            else {
                System.out.println("Difficulty: HARD");
            }

            // --------------------------------------
            // QUESTION 1
            // --------------------------------------

            if (questionNumber == 1) {

                System.out.println("What is 5 + 5?");
                System.out.println("A. 10");
                System.out.println("B. 15");
                System.out.println("C. 20");
                System.out.println("D. 25");

                System.out.print("Enter your answer: ");

                char answer = input.next().toUpperCase().charAt(0);

                char correctAnswer = 'A';

                if (answer == correctAnswer) {

                    System.out.println("Correct!");

                    score++;
                    correctAnswers++;

                    // Correct → increase difficulty
                    if (difficulty < 3) {
                        difficulty++;
                    }

                }
                else {

                    System.out.println("Incorrect!");

                    incorrectAnswers++;

                    // Incorrect → decrease difficulty
                    if (difficulty > 1) {
                        difficulty--;
                    }
                }
            }

            // --------------------------------------
            // QUESTION 2
            // --------------------------------------

            else if (questionNumber == 2) {

                System.out.println("What is 10 * 2?");
                System.out.println("A. 10");
                System.out.println("B. 20");
                System.out.println("C. 30");
                System.out.println("D. 40");

                System.out.print("Enter your answer: ");

                char answer = input.next().toUpperCase().charAt(0);

                char correctAnswer = 'B';

                if (answer == correctAnswer) {

                    System.out.println("Correct!");

                    score++;
                    correctAnswers++;

                    if (difficulty < 3) {
                        difficulty++;
                    }

                }
                else {

                    System.out.println("Incorrect!");

                    incorrectAnswers++;

                    if (difficulty > 1) {
                        difficulty--;
                    }
                }
            }

            // --------------------------------------
            // QUESTION 3
            // --------------------------------------

            else if (questionNumber == 3) {

                System.out.println(
                    "Which data type stores whole numbers in Java?"
                );

                System.out.println("A. double");
                System.out.println("B. boolean");
                System.out.println("C. int");
                System.out.println("D. char");

                System.out.print("Enter your answer: ");

                char answer = input.next().toUpperCase().charAt(0);

                char correctAnswer = 'C';

                if (answer == correctAnswer) {

                    System.out.println("Correct!");

                    score++;
                    correctAnswers++;

                    if (difficulty < 3) {
                        difficulty++;
                    }

                }
                else {

                    System.out.println("Incorrect!");

                    incorrectAnswers++;

                    if (difficulty > 1) {
                        difficulty--;
                    }
                }
            }

            // --------------------------------------
            // QUESTION 4
            // --------------------------------------

            else if (questionNumber == 4) {

                System.out.println(
                    "Which operator checks equality in Java?"
                );

                System.out.println("A. =");
                System.out.println("B. ==");
                System.out.println("C. !=");
                System.out.println("D. >=");

                System.out.print("Enter your answer: ");

                char answer = input.next().toUpperCase().charAt(0);

                char correctAnswer = 'B';

                if (answer == correctAnswer) {

                    System.out.println("Correct!");

                    score++;
                    correctAnswers++;

                    if (difficulty < 3) {
                        difficulty++;
                    }

                }
                else {

                    System.out.println("Incorrect!");

                    incorrectAnswers++;

                    if (difficulty > 1) {
                        difficulty--;
                    }
                }
            }

            // --------------------------------------
            // QUESTION 5
            // --------------------------------------

            else {

                System.out.println("What does JVM stand for?");

                System.out.println("A. Java Variable Machine");
                System.out.println("B. Java Virtual Machine");
                System.out.println("C. Java Verified Machine");
                System.out.println("D. Java Visual Machine");

                System.out.print("Enter your answer: ");

                char answer = input.next().toUpperCase().charAt(0);

                char correctAnswer = 'B';

                if (answer == correctAnswer) {

                    System.out.println("Correct!");

                    score++;
                    correctAnswers++;

                    if (difficulty < 3) {
                        difficulty++;
                    }

                }
                else {

                    System.out.println("Incorrect!");

                    incorrectAnswers++;

                    if (difficulty > 1) {
                        difficulty--;
                    }
                }
            }

            // --------------------------------------
            // MOVE TO NEXT QUESTION
            // --------------------------------------

            questionNumber++;
        }

        // ==========================================
        // OUTPUT
        // ==========================================

        double percentage =
            (score * 100.0) / TOTAL_QUESTIONS;

        System.out.println();
        System.out.println("==========================================");
        System.out.println("              FINAL RESULT");
        System.out.println("==========================================");

        System.out.println("Student Name: " + studentName);
        System.out.println("Total Questions: " + TOTAL_QUESTIONS);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
        System.out.println("Score: " + score);
        System.out.println("Percentage: " + percentage + "%");

        // ==========================================
        // FINAL DIFFICULTY
        // ==========================================

        if (difficulty == 1) {
            System.out.println("Final Difficulty: EASY");
        }
        else if (difficulty == 2) {
            System.out.println("Final Difficulty: MEDIUM");
        }
        else {
            System.out.println("Final Difficulty: HARD");
        }

        // ==========================================
        // PASS / FAIL
        // ==========================================

        boolean passed = percentage >= PASS_MARK;

        if (passed && quizStarted) {
            System.out.println("Result: PASS");
        }
        else {
            System.out.println("Result: FAIL");
        }

        // ==========================================
        // TERNARY OPERATOR
        // ==========================================

        String performance =
            percentage >= 80 ? "Excellent" :
            percentage >= 60 ? "Good" :
            percentage >= 50 ? "Average" :
            "Needs Improvement";

        System.out.println("Performance: " + performance);

        // ==========================================
        // CLOSE SCANNER
        // ==========================================

        input.close();

        System.out.println();
        System.out.println("Thank you for using the system!!");

    }
}