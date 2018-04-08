package app.bookyfever.com;

import org.junit.Test;

import java.net.SocketTimeoutException;

import app.storytel.candidate.com.presenter.ErrorHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ErrorHelperTest {

    @Test
    public void whenGetErrorMessage_isUnknownError_shouldReturnCorrectMessage() {
        // Arrange
        Throwable t = new Throwable();

        /// / Act
        String result = ErrorHelper.getErrorMessage(t);

        // Assert
        assertThat(result, is(ErrorHelper.UNKNOWN_ERROR));
    }

    @Test
    public void whenGetErrorMessage_isTimeOutError_shouldReturnCorrectMessage() {
        // Arrange
        Throwable t = new SocketTimeoutException();

        /// / Act
        String result = ErrorHelper.getErrorMessage(t);

        // Assert
        assertThat(result, is(ErrorHelper.TIMEOUT_ERROR));
    }
}
