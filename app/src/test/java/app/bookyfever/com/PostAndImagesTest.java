package app.bookyfever.com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import app.storytel.candidate.com.model.Photo;
import app.storytel.candidate.com.model.Post;
import app.storytel.candidate.com.model.PostAndImages;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PostAndImagesTest {

    @Test
    public void whenGetNumberOfPosts_isPostsSize4_shouldReturn4() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        PostAndImages postAndImages = new PostAndImages(posts, null);

        // Act
        int result = postAndImages.getNumberOfPosts();

        // Assert
        assertThat(result, is(4));
    }

    @Test
    public void whenGetNumberOfPosts_isPostsNull_shouldReturn0() {
        // Arrange
        PostAndImages postAndImages = new PostAndImages(null, null);

        // Act
        int result = postAndImages.getNumberOfPosts();

        // Assert
        assertThat(result, is(0));
    }

    @Test
    public void whenGetPost_isIndexWithinBounds_shouldReturnCorrectPost() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        Post post2 = new Post();
        posts.add(post1);
        posts.add(post2);
        PostAndImages postAndImages = new PostAndImages(posts, null);

        // Act
        Post result = postAndImages.getPost(1);

        // Assert
        assertThat(result, is(post2));
    }

    @Test
    public void whenGetPost_isIndexNotWithinBounds_shouldReturnNull() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        Post post2 = new Post();
        posts.add(post1);
        posts.add(post2);
        PostAndImages postAndImages = new PostAndImages(posts, null);

        // Act
        Post result = postAndImages.getPost(10);

        // Assert
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenGetPhoto_isPhotosNull_shouldReturnNull() {
        // Arrange
        PostAndImages postAndImages = new PostAndImages(null, null);

        // Act
        Photo result = postAndImages.getRandomPhoto();

        // Assert
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenGetPhoto_isPhotosSizeZero_shouldReturnNull() {
        // Arrange
        List<Photo> photos = new ArrayList<>();
        PostAndImages postAndImages = new PostAndImages(null, photos);

        // Act
        Photo result = postAndImages.getRandomPhoto();

        // Assert
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenGetPhoto_isPhotosAvailable_shouldReturnPhoto() {
        // Arrange
        List<Photo> photos = new ArrayList<>();
        Photo photo = new Photo();
        photos.add(photo);
        PostAndImages postAndImages = new PostAndImages(null, photos);

        // Act
        Photo result = postAndImages.getRandomPhoto();

        // Assert
        assertThat(result, is(photo));
    }
}
