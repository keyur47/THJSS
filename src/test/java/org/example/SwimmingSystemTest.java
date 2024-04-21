package org.example;

import org.example.Learner;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.example.SwimmingSystem;
public class SwimmingSystemTest {
    @Test
    void LearnerNewRegister() {
        SwimmingSystem system = new SwimmingSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("Meet\nF\n4\n123-456-7890\n1\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter learner's name:");
        String name = scanner.nextLine();
        assertEquals("Meet", name);
        System.out.print("Enter Gender (M/F): ");
        String genderInput = scanner.nextLine();
        String gender;
        if (genderInput.equalsIgnoreCase("M")) {
            gender = "Male";
        } else if (genderInput.equalsIgnoreCase("F")) {
            gender = "Female";
        } else {
            System.out.println("Invalid gender input. Defaulting to 'Male'.");
            gender = "Male"; // Default to 'Male' if invalid input
        }
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter emergency contact number:");
        String contact = scanner.nextLine();
        System.out.println("Enter current grade level (1-5):");
        int gradeLevel = scanner.nextInt();
        scanner.nextLine();
        int learnerId = Learner.generateLearnerId();
        System.out.println(name + " Congratulations! You are registered successfully. Your learnerId is: " + learnerId);
        System.out.println("Use Your Learner ID while booking lessons");
    }
}
