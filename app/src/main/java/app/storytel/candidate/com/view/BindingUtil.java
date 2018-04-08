package app.storytel.candidate.com.view;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingUtil {

    /**
     *  Custom binding adapter used to load images into ImageViews. Uses Glide to load image.
     *  For example: app:imageUrl="@{photo.thumbnailUrl}"
     * @param v
     * @param imgUrl
     */
    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView v, String imgUrl) {
        Glide.with(v.getContext()).load(imgUrl).into(v);
    }
}
