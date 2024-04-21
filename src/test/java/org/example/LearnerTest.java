package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Learner;
import org.example.Lesson;
import org.example.Coach;
public class LearnerTest {
    @Test
    void BookLearner() {
        Learner learner = new Learner(12345, "Me", "Female", 4, "123-456-7890", 1);
        Lesson lesson = new Lesson("Monday", "4-5pm", 4, new Coach("Mr. Part"), 1);
        learner.BookLesson(lesson);
        assertTrue(learner.getBookedLessons().contains(lesson));
    }
}
