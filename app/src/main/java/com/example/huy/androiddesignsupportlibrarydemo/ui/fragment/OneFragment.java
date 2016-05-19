package com.example.huy.androiddesignsupportlibrarydemo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.huy.androiddesignsupportlibrarydemo.R;
import com.example.huy.androiddesignsupportlibrarydemo.data.model.Movie;
import com.example.huy.androiddesignsupportlibrarydemo.data.model.MovieResponse;
import com.example.huy.androiddesignsupportlibrarydemo.rest.ApiInterfaceMovie;
import com.example.huy.androiddesignsupportlibrarydemo.ui.adapter.MovieAdapter;
import com.example.huy.androiddesignsupportlibrarydemo.util.ApiClient;
import com.example.huy.androiddesignsupportlibrarydemo.util.ApiUrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneFragment extends Fragment {

    private static final String TAG = "OneFragment";
    private boolean isFirstLoadData = true;
    private View mRootView;
    private List<Movie> mMovieList = new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    @BindView(R.id.recycler_view_film)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_first_load)
    ProgressBar mProcessBarFirstLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, mRootView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMovieAdapter = new MovieAdapter(getContext(), mMovieList,
                R.layout.list_item_movie);
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.setAdapter(mMovieAdapter);
        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isFirstLoadData) {
            firstLoadData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstLoadData) hideProcessBarFirstLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void firstLoadData() {
        if (ApiUrl.API_KEY.isEmpty()) {
            Log.e(TAG, "firstLoadData: API Key is NULL");
            return;
        }
        ApiInterfaceMovie apiInterfaceMovie = ApiClient.getClient().create(ApiInterfaceMovie.class);
        Call<MovieResponse> movieResponseCall = apiInterfaceMovie.getTopRatedMovies(ApiUrl.API_KEY);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> mResponseMovieList = response.body().getResults();
                mMovieList.addAll(mResponseMovieList);
                mMovieAdapter.notifyDataSetChanged();
                if (!mMovieList.isEmpty()) {
                    isFirstLoadData = false;
                    hideProcessBarFirstLoad();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void hideProcessBarFirstLoad() {
        mProcessBarFirstLoad.setVisibility(View.GONE);
    }
}
