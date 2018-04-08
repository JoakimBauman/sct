package app.storytel.candidate.com.view;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.storytel.candidate.com.R;
import app.storytel.candidate.com.databinding.ActivityScrollingBinding;
import app.storytel.candidate.com.model.PostAndImages;
import app.storytel.candidate.com.model.PostDetails;
import app.storytel.candidate.com.presenter.ScrollingContract;
import app.storytel.candidate.com.presenter.ScrollingPresenter;

public class ScrollingActivity extends AppCompatActivity implements ScrollingContract.View {

    private PostAdapter postAdapter;
    private ActivityScrollingBinding binding;
    private ScrollingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ScrollingPresenter(this);
        presenter.getPostsAndImages();

        postAdapter = new PostAdapter(new PostAdapter.PostAdapterListener() {
            @Override
            public void onPostClick(PostDetails details) {
                startActivity(DetailsActivity.getCallingIntent(ScrollingActivity.this, details));
            }
        });

        binding.recyclerView.setAdapter(postAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayPostsAndImages(PostAndImages postAndImages) {
        postAdapter.setData(postAndImages);
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
                        presenter.getPostsAndImages();
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
