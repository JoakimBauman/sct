package app.storytel.candidate.com.presenter;

import java.util.List;

import app.storytel.candidate.com.model.Api;
import app.storytel.candidate.com.model.Photo;
import app.storytel.candidate.com.model.Post;
import app.storytel.candidate.com.model.PostAndImages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScrollingPresenter implements ScrollingContract.Presenter {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final Api service;
    private ScrollingContract.View view;

    public ScrollingPresenter(ScrollingContract.View view) {
        this.view = view;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Api.class);
    }

    public void detachView() {
        view = null;
    }

    private boolean viewIsReady() {
        return view != null;
    }

    @Override
    public void getPostsAndImages() {

        final PostAndImages postAndImages = new PostAndImages(null, null);

        if (viewIsReady()) {
            view.displayProgress();
        }

        service.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postAndImages.setPosts(response.body());
                    if (viewIsReady()) {
                        view.hideProgress();
                        view.displayPostsAndImages(postAndImages);
                    }
                } //TODO else handle error case
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (viewIsReady()) {
                    view.hideProgress();
                    view.displayErrorMessage(ErrorHelper.getErrorMessage(t));
                }
            }
        });

        service.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    postAndImages.setPhotos(response.body());
                    if (viewIsReady()) {
                        view.displayPostsAndImages(postAndImages);
                    }
                } //TODO else handle error case
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                //Silent fail if no photos were loaded
            }
        });
    }
}
