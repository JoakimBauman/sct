package app.storytel.candidate.com.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Post details data class. Parcelable to be sent via intent to details Activity.
 */
public class PostDetails implements Parcelable {
    public int id;
    public String title;
    public String body;
    public String url;

    public PostDetails(@NonNull Post post, @NonNull Photo photo) {
        id = post.id;
        title = post.title;
        body = post.body;
        url = photo.url;
    }

    protected PostDetails(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        url = in.readString();
    }

    public static final Creator<PostDetails> CREATOR = new Creator<PostDetails>() {
        @Override
        public PostDetails createFromParcel(Parcel in) {
            return new PostDetails(in);
        }

        @Override
        public PostDetails[] newArray(int size) {
            return new PostDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(url);
    }
}
