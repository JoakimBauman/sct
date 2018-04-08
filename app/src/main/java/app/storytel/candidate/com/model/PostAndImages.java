package app.storytel.candidate.com.model;

import android.support.annotation.Nullable;

import java.util.List;
import java.util.Random;

/**
 * Data class holding posts and photos
 */
public class PostAndImages {
    private List<Post> posts;
    private List<Photo> photos;

    public PostAndImages(List<Post> post, List<Photo> photos) {
        this.posts = post;
        this.photos = photos;
    }

    public void setPosts(List<Post> mPosts) {
        this.posts = mPosts;
    }

    public void setPhotos(List<Photo> mPhotos) {
        this.photos = mPhotos;
    }

    @Nullable
    public Post getPost(int index) {
        return posts == null || posts.size() <= index ? null : posts.get(index);
    }

    public int getNumberOfPosts() {
        return posts == null ? 0 : posts.size();
    }

    @Nullable
    public Photo getRandomPhoto() {
        if (photos == null || photos.size() == 0) {
            return null;
        } else if(photos.size() == 1) {
            return photos.get(0);
        }

        final int index = new Random().nextInt(photos.size() - 1);
        return photos.get(index);
    }
}
