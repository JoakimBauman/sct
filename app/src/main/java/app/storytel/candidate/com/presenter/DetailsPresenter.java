package app.storytel.candidate.com.presenter;

import java.util.List;

import app.storytel.candidate.com.model.Api;
import app.storytel.candidate.com.model.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsPresenter implements DetailsContract.Presenter {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private DetailsContract.View view;
    private final Api service;

    public DetailsPresenter(DetailsContract.View view) {
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
    public void getComments(int id) {

        if (viewIsReady()) {
            view.displayProgress();
        }

        service.getCommentsById(id).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    if (viewIsReady()) {
                        view.hideProgress();
                        view.displayComments(response.body());
                    }
                } //TODO else handle error case
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                if (viewIsReady()) {
                    view.hideProgress();
                    view.displayErrorMessage(ErrorHelper.getErrorMessage(t));
                }
            }
        });
    }
}
