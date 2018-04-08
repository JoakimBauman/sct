package app.storytel.candidate.com.presenter;

import java.util.List;

import app.storytel.candidate.com.model.Comment;

public interface DetailsContract {
    interface View extends BaseView {
        void displayComments(List<Comment> comments);
    }

    interface Presenter {
        void getComments(int id);
    }
}
