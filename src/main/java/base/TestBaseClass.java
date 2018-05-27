package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBaseClass {
    private long initTime;

    @BeforeSuite
    public void beforeSuite() {
        initTime = System.currentTimeMillis();
        // e.g. initialize a lot of variables
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Suite time is: " + (System.currentTimeMillis() - initTime));
        // to measure execution time of the suite
    }

    //@Test(enabled = false)
    //@Test(groups = {"smoke", "regression"})

    //@Test(dependsONMethod = "loginTest") should NOT do that!
    //@Test(dependsOnGroups = "init.*")

    //@Test(threadPoolSize = 3, invocationCount = 10, timeOut = 1000) очень полезная штука
    // first - how many threads to use for parallel
    // second - how many times execute this test
    // delay before each test starts

}
