import java.util.Scanner;

public class gradecalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of subjects and marks
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        int totalMarks = 0;

        System.out.println("Enter marks out of 100 for each subject:");
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Subject " + i + ": ");
            totalMarks += scanner.nextInt();
        }

        // Calculate average percentage
        double average = (double) totalMarks / numSubjects;

        // Determine grade using if-else
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", average) + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
