package app.storytel.candidate.com.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.storytel.candidate.com.BR;
import app.storytel.candidate.com.databinding.PostItemBinding;
import app.storytel.candidate.com.model.PostAndImages;
import app.storytel.candidate.com.model.PostDetails;

/**
 * Created by finne on 14/12/2017.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final PostAdapterListener listener;
    private PostAndImages data;

    public PostAdapter(PostAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PostItemBinding itemBinding = PostItemBinding
                .inflate(layoutInflater, parent, false);
        return new PostViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        holder.bind(data.getPost(position), data.getRandomPhoto());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPostClick(new PostDetails(holder.binding.getPost(), holder.binding.getPhoto()));
            }
        });
    }

    public void setData(PostAndImages data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getNumberOfPosts();
    }

    public interface PostAdapterListener {
        void onPostClick(PostDetails details);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        PostItemBinding binding;

        public PostViewHolder(PostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj, Object obj2) {
            binding.setVariable(BR.post, obj);
            binding.setVariable(BR.photo, obj2);
            binding.executePendingBindings();
        }
    }
}
