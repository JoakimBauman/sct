package app.storytel.candidate.com.presenter;

import app.storytel.candidate.com.model.PostAndImages;

public interface ScrollingContract {
    interface View extends BaseView {
        void displayPostsAndImages(PostAndImages postAndImages);
    }

    interface Presenter {
        void getPostsAndImages();
    }
}
