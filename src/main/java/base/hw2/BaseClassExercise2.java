package base.hw2;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClassExercise2 {

    private long startTime;

    @BeforeSuite
    public void beforeSuite() {
        long startTime = System.currentTimeMillis();
        System.out.println("Current time:" + startTime);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Test suite is completed in" + (System.currentTimeMillis() - startTime));
    }
}
