
package retry;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        Throwable exception = result.getThrowable();

        if (!isRetryable(exception)) {
            return false;
        }

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            System.out.println(
                    "Retrying test: "
                            + result.getMethod().getMethodName()
                            + " | Retry attempt: "
                            + retryCount
            );

            return true;
        }

        System.out.println(
                "Retry limit reached for: "
                        + result.getMethod().getMethodName()
        );

        return false;
    }

    private boolean isRetryable(Throwable exception) {

        Throwable current = exception;

        while (current != null) {

            if (current instanceof StaleElementReferenceException
                    || current instanceof ElementClickInterceptedException
                    || current instanceof ElementNotInteractableException
                    || current instanceof TimeoutException) {

                return true;
            }

            current = current.getCause();
        }

        return false;
    }
}
