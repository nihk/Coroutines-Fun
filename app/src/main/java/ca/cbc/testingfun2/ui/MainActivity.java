package ca.cbc.testingfun2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ca.cbc.testingfun2.R;
import ca.cbc.testingfun2.data.GitHubJob;
import ca.cbc.testingfun2.util.Resource;
import ca.cbc.testingfun2.vm.GitHubJobsViewModel;
import ca.cbc.testingfun2.vm.ViewModelFactory;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private GitHubJobsViewModel viewModel;
    private GitHubJobsAdapter adapter = new GitHubJobsAdapter();
    private int id = 666;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button insertARow;
    private Button refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GitHubJobsViewModel.class);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.progress_bar);

        insertARow = findViewById(R.id.button);
        insertARow.setOnClickListener(__ -> {
            GitHubJob gitHubJob = new GitHubJob(String.valueOf(id++), "A title", "A company");
            viewModel.setPendingScrollAction(ScrollAction.ScrollToTop);
            disableButtons();
            viewModel.insertJob(gitHubJob);
        });

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(__ -> {
            viewModel.setPendingScrollAction(ScrollAction.ScrollToTop);
            disableButtons();
            viewModel.fetchGitHubJobs();
        });

        observeGitHubJobs();
        disableButtons();
        viewModel.fetchGitHubJobs();
    }

    private void observeGitHubJobs() {
        viewModel.getGitHubJobs().observe(this, resource -> {
            if (resource instanceof Resource.Loading) {
                progressBar.setVisibility(View.VISIBLE);
                if (resource.getData() != null) {
                    adapter.submitList(resource.getData());
                }
            } else if (resource instanceof Resource.Success) {
                progressBar.setVisibility(View.GONE);
                adapter.submitList(resource.getData());
                performScrollAction();
                enableButtons();
            } else if (resource instanceof Resource.Error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Failed to fetch new Jobs", Toast.LENGTH_LONG)
                        .show();
                adapter.submitList(resource.getData());
                performScrollAction();
                enableButtons();
            }
        });
    }

    private void performScrollAction() {
        ScrollAction scrollAction = viewModel.getPendingScrollAction();
        if (scrollAction == ScrollAction.ScrollToTop) {
            recyclerView.post(() -> {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    // fixme: what's the right way to simply scroll to the 0th position
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, 0);
                }
            });
        }
    }

    private void disableButtons() {
        setButtonsEnabled(false);
    }

    private void enableButtons() {
        setButtonsEnabled(true);
    }

    private void setButtonsEnabled(boolean isEnabled) {
        insertARow.setEnabled(isEnabled);
        refresh.setEnabled(isEnabled);
    }
}
