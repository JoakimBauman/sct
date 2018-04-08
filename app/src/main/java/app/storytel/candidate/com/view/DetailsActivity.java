package app.storytel.candidate.com.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import app.storytel.candidate.com.R;
import app.storytel.candidate.com.databinding.ActivityDetailsBinding;
import app.storytel.candidate.com.model.Comment;
import app.storytel.candidate.com.model.PostDetails;
import app.storytel.candidate.com.presenter.DetailsContract;
import app.storytel.candidate.com.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private static final String CALLING_INTENT_EXTRA = "DetailsActivity_postDetails";

    private DetailsPresenter presenter;
    private PostDetails postDetails;
    private ActivityDetailsBinding binding;

    public static Intent getCallingIntent(@NonNull Context context, @NonNull PostDetails postDetails) {
        Intent i = new Intent(context, DetailsActivity.class);
        i.putExtra(CALLING_INTENT_EXTRA, postDetails);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postDetails = getIntent().getParcelableExtra(CALLING_INTENT_EXTRA);
        binding.setPost(postDetails);
        setTitle(postDetails.title);

        presenter = new DetailsPresenter(this);
        presenter.getComments(postDetails.id);
    }

    @Override
    public void displayComments(List<Comment> comments) {
        binding.setList(comments);
    }

    @Override
    public void displayProgress() {
        binding.postProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.postProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayErrorMessage(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(error)
                .setTitle(R.string.oops)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.getComments(postDetails.id);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
