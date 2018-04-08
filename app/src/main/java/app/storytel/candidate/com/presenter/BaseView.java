package app.storytel.candidate.com.presenter;

public interface BaseView {
    void displayProgress();

    void hideProgress();

    void displayErrorMessage(String error);
}
