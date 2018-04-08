package app.storytel.candidate.com.presenter;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorHelper {
    public static final String UNKNOWN_ERROR = "Unknown error";
    public static final String TIMEOUT_ERROR = "Timeout error";

    public static String getErrorMessage(Throwable t) {
        if (t instanceof HttpException) {
            return ((HttpException) t).message();
        } else if (t instanceof SocketTimeoutException) {
            return TIMEOUT_ERROR;
        } else if (t instanceof IOException) {
            return UNKNOWN_ERROR;
        } else {
            return UNKNOWN_ERROR;
        }
    }
}
