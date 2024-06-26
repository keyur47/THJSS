package org.example;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Manages the all the operations and user interactions for the Swimming System .
 * maages learners, coaches, lessons, bookings, Reviews, and generating reports for Learners and Coaches.
 */
public class SwimmingSystem {

    private final List<Coach> coaches = new ArrayList<>();
    private final List<Learner> learners = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();

    private int incrementessonId;
    private final Scanner scanner = new Scanner(System.in);

    public SwimmingSystem() {
        this.incrementessonId = 1;
        generateCoaches();
        generateLessons();
        registeredLearners();
    }

    /**
     * generate 3 coaches.
     */
    private void generateCoaches() {
        coaches.add(new Coach("Meet"));
        coaches.add(new Coach("Akash"));
        coaches.add(new Coach("Dev"));
    }

    /**
     * generate lessons with coaches and grade levels.
     */
    private void generateLessons() {
        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
        String[][] timeSlots = {{"4-5pm", "5-6pm", "6-7pm"}, {"2-3pm", "3-4pm"}};
        for (int gradeLevel = 1; gradeLevel <= 5; gradeLevel++) {
            for (String day : days) {
                String[] slots = day.equals("Saturday") ? timeSlots[1] : timeSlots[0];
                for (String slot : slots) {
                    lessons.add(new Lesson(day, slot, gradeLevel, coaches.get(gradeLevel % coaches.size()), incrementessonId));
                    incrementessonId++;
                }
            }
        }
    }

    /**
     * generate 12 learners and add into system.
     */
    private void registeredLearners() {
        learners.add(new Learner(82645, "Mani", "Female", 10, "464-456-7890", 3));
        learners.add(new Learner(97390, "Banu", "Male", 10, "342-456-7890", 1));
        learners.add(new Learner(52865, "Moni", "Female", 7, "234-789-0123", 2));
        learners.add(new Learner(91215, "Konika", "Female", 7, "24-456-7890", 3));
        learners.add(new Learner(27578, "Zina", "Female", 4, "234-446-7890", 2));
        learners.add(new Learner(62852, "Keel", "Male", 10, "440-456-7890", 5));
        learners.add(new Learner(82947, "Heena", "Female", 10, "456-456-7894", 3));
        learners.add(new Learner(84345, "Hit", "Male", 4, "353-456-7890", 2));
        learners.add(new Learner(26363, "Krinsh", "Male", 7, "755-456-7890", 1));
        learners.add(new Learner(87878, "Devu", "Female", 10, "567-456-7890", 2));
        learners.add(new Learner(30104, "Kinjal", "Male", 6, "575-456-7890", 3));
        learners.add(new Learner(80812, "Mamu", "Female", 7, "848-456-7890", 5));
        learners.add(new Learner(80654, "Pinu", "Male", 10, "989-456-7890", 2));
        learners.add(new Learner(48478, "Keet", "Female", 7, "855-456-7890", 2));
        learners.add(new Learner(64367, "Hiku", "Female", 4, "456-456-7890", 1));

    }

    /**
     * Display the main menu and processes user can select from choices.
     */
    void mainSystemOfHJSS() {
        while (true) {
            System.out.println("\n---  Welcome to The Hatfield Junior Swimming School System ---");
            System.out.println("Select an option:");
            System.out.println("1. Register a new learner");
            System.out.println("2. Book a swimming lesson");
            System.out.println("3. change or cancel lesson");
            System.out.println("4. Attend a swimming lesson");
            System.out.println("5. Monthly learner report");
            System.out.println("6. Monthly coach report");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choiceID = scanner.nextInt();
            scanner.nextLine();

            switch (choiceID) {
                case 1 -> newRegistration();
                case 2 -> BookingLesson();
                case 3 -> ChangeOrCancelBooking();
                case 4 -> AttendLesson();
                case 5 -> GenerateUserLearnerReport();
                case 6 -> GenerateCoachReport();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Register a new learner to the system.
     */
    private void newRegistration() {
        System.out.println("Enter learner's name:");
        String name = scanner.nextLine();
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
        learners.add(new Learner(learnerId, name, gender, age, contact, gradeLevel));
        System.out.println(name + " Congratulations! You are registered successfully. Your learnerId is: " + learnerId);
        System.out.println("Use Your Learner ID while booking lessons");
    }


    /**
     * Displays the timetable by Day.
     */
    public void timeTableByDay(String day) {
        System.out.println("Timetable for " + day + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println("-".repeat(70));
                System.out.println("Time: " + lesson.getTimeSlot() + ", Grade Level: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Displays the timetable by GradeLevel.
     */
    public void timeTableByGrade(int gradeLevel) {
        System.out.println("Timetable for Grade " + gradeLevel + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevel) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() + ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Displays the timetable by Coach.
     */
    public void timetableByCoach(String coachName) {
        System.out.println("Timetable for Coach " + coachName + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() + ", GradeLevel: " + lesson.getGradeLevel() + ", Lesson ID: " + lesson.getLessonID() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Display Available Days and Time slots
     */
    public void availableDaysAndTimeSlots() {
        System.out.println("Available Days and Times:");
        System.out.println("Monday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Wednesday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Friday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Saturday: 2-3pm, 3-4pm");
    }

    /**
     * Display Available coaches with their ratings
     */
    public void availableCoachRating() {
        System.out.println("Available Coaches:");
        for (Coach coach : coaches) {
            double averageRating = coach.getCoachAverageRating();
            System.out.println(coach.getName() + " -Rating: " + averageRating);
        }
    }

    /**
     * Booking system for book the lesson.
     */
    private void BookingLesson() {
        System.out.println("Select an option:");
        System.out.println("1. Display timetable by day");
        System.out.println("2. Display timetable by grade");
        System.out.println("3. Display timetable by coach");
        System.out.println("Enter Your Choice:");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option) {
            case 1 -> {
                availableDaysAndTimeSlots();
                System.out.println("Enter Day: ");
                String day = scanner.nextLine();
                timeTableByDay(day);
                break;
            }
            case 2 -> {
                System.out.println("Enter GradeLevel: ");
                int grade = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                timeTableByGrade(grade);
                break;
            }
            case 3 -> {
                availableCoachRating();
                System.out.println("Enter Coach's Name: ");
                String coachName = scanner.nextLine();
                timetableByCoach(coachName);
                break;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        System.out.println("Enter Learner ID: ");
        int id = scanner.nextInt();
        Learner learner = learners.stream().filter(l -> l.getLearnerID() == id).findFirst().orElse(null);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        System.out.print("Enter lesson ID: ");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if the lesson ID is already booked by the learner
        if (learner.getBookedLessons().stream().anyMatch(lesson -> lesson.getLessonID() == lessonId)) {
            System.out.println("You have already booked this lesson. Please choose another lesson.");
            return;
        }

        Optional<Lesson> optionalLesson = lessons.stream().filter(l -> l.getLessonID() == lessonId).findFirst();

        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            lesson.BookLesson(learner);
            learner.BookLesson(lesson);
            System.out.println("Lesson booked successfully.");
        } else {
            System.out.println("Failed to book the lesson. Please check the details and try again.");
        }

    }

    /**
     * change or cancle the booking.
     */

    private void ChangeOrCancelBooking() {

        System.out.println("Select an Option: ");
        System.out.println("1. Change the lesson");
        System.out.println("2. Cancel the Lesson: ");
        System.out.println("Enter Your Choice: ");

        int opt = scanner.nextInt();
        scanner.nextLine(); // consume new line

        switch (opt) {
            case 1 -> ChangeBookedLesson();
            case 2 -> CancelBookedLesson();
            default -> System.out.println("Invalid option. Please try again.");
        }

    }


    /**
     * Change the previous booked lesson.
     */

    private void ChangeBookedLesson() {
        System.out.println("Enter learner ID for change or cancel the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerdetailId(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        BookedLessonsByLearner(learnerId);

        // Prompt for lesson ID to  change
        System.out.println("Enter the lesson ID you want to change or change:");
        int oldLessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> oldLesson = findLessonDetailsId(oldLessonId);

        if (oldLesson.isPresent()) {
            // Prompt for new lesson ID
            System.out.println("Enter the new lesson ID:");
            int newLessonId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Find the new lesson
            Optional<Lesson> newLesson = findLessonDetailsId(newLessonId);

            if (newLesson.isPresent()) {
                // change the booking
                learner.displayChangeBooking(oldLesson.get(), newLesson.get());
                System.out.println("Booking changed successfully.");
            } else {
                System.out.println("New lesson not found.");
            }
        } else {
            System.out.println("Old lesson not found.");
        }


    }

    /**
     * Cancel the booked lesson.
     */

    private void CancelBookedLesson() {
        System.out.println("Enter learner ID for change or cancel the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerdetailId(learnerId);


        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        BookedLessonsByLearner(learnerId);

        // Prompt for lesson ID to cancel
        System.out.println("Enter the lesson ID you want to cancel:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToCancel = findLessonDetailsId(lessonId);

        if (lessonToCancel.isPresent()) {
            learner.cancelLesson(lessonToCancel.get());
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Lesson not found.");
        }


    }


    /**
     * Attend the booked lessonm.
     */

    private void AttendLesson() {
        System.out.println("Enter learner ID for attending the lesson: ");
        int learnerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Learner learner = findLearnerdetailId(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        BookedLessonsByLearner(learnerId);

        // Prompt for lesson ID to attend
        System.out.println("Enter the lesson ID you want to attend:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToAttend = findLessonDetailsId(lessonId);

        if (lessonToAttend.isPresent()) {
            // Attend the lesson
            learner.AttendLesson(lessonToAttend.get());
            System.out.println("Lesson attended successfully.");

            System.out.println("Enter your rating (1 to 5): ");
            int rating = scanner.nextInt();
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Rating must be between 1 and 5.");
                return;
            }

            scanner.nextLine(); // Consume newline
            System.out.println("Enter your review about lesson you have attended:");
            String review = scanner.nextLine();

            learner.addFeedbackForLesson(lessonToAttend.get(), review);
            learner.updateingRatingForLesson(lessonToAttend.get(), rating);
            System.out.println("Feedback and rating submitted. Thank you!");
            // Pass rating directly to coach
            lessonToAttend.get().getCoach().updateRating(rating);
        }

    }

    /**
     * find the learner  information by their learner id
     *
     * @param learnerId
     */
    private Learner findLearnerdetailId(int learnerId) {
        return learners.stream().filter(learner -> learner.getLearnerID() == learnerId).findFirst().orElse(null);
    }

    /**
     * find the lesson information by lesson id
     *
     * @param lessonId
     */
    private Optional<Lesson> findLessonDetailsId(int lessonId) {
        return lessons.stream().filter(lesson -> lesson.getLessonID() == lessonId).findFirst();
    }

    /**
     * Display the booked lesson information by learner using learner id
     *
     * @param learnerId
     */
    private void BookedLessonsByLearner(int learnerId) {
        Learner learner = findLearnerdetailId(learnerId);
        if (learner != null) {
            System.out.println("Booked Lessons for Learner with ID " + learnerId + ":");
            for (Lesson lesson : learner.getBookedLessons()) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Day: " + lesson.getDay());
                System.out.println("Time: " + lesson.getTimeSlot());
                System.out.println("Coach: " + lesson.getCoach().getName());
                System.out.println("Grade Level: " + lesson.getGradeLevel());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Learner not found.");
        }
    }
    /**
     * Generates reports about learners  activities with booked, cancelled and attended lessons.
     */
    private void GenerateUserLearnerReport() {
        System.out.println("-".repeat(100));
        System.out.format("%-12s%-8s%-5s%-10s%-12s%-8s%-8s%-10s%-8s%-8s%n", "Learner ID", "Name", "Age", "Gender", "GradeLevel", "Booked", "Cancel", "Attended", "Time", "Coach");
        System.out.println("-".repeat(100));
        IntStream.range(0, learners.size()).forEach(i -> {
            Learner learner = learners.get(i);
            Lesson lesson = lessons.get(i);
            System.out.format("%-12s%-8s%-5s%-10s%-12s%-8s%-8s%-10s%-8s%-8s%n", learner.getLearnerID(), learner.getName(), learner.getAge(), learner.getGender(), learner.getGradeLevel(), learner.getTotalBookedLessons(), learner.getTotalCancelledLessons(), learner.getTotalAttendedLessons(), lesson.getTimeSlot(), lesson.getCoach().getName());
        });
    }

    /**
     * Generates report about  each coaches with their names and respective ratings.
     */
    private void GenerateCoachReport() {
        System.out.println("-".repeat(40));
        System.out.format("%-20s%-20s%n", "Coach Name", "Average Rating");
        System.out.println("-".repeat(40));
        IntStream.range(0, coaches.size()).forEach(i -> {
            Coach coach = coaches.get(i);
            System.out.format("%-20s%-20s%n", coach.getName(), coach.getCoachAverageRating());
        });
    }
}