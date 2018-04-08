package app.storytel.candidate.com.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsById(@Path("id") int id);
}
