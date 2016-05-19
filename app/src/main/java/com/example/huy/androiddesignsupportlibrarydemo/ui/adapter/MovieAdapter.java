package com.example.huy.androiddesignsupportlibrarydemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.huy.androiddesignsupportlibrarydemo.R;
import com.example.huy.androiddesignsupportlibrarydemo.data.model.Movie;
import com.example.huy.androiddesignsupportlibrarydemo.util.ApiUrl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private int rowLayout;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movieList, int rowLayout) {
        this.movieList = movieList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.mTitle.setText(movieList.get(position).getTitle());
        holder.mSubTitle.setText(movieList.get(position).getReleaseDate());
        holder.mDescription.setText(movieList.get(position).getOverview());
        holder.mRating.setText(movieList.get(position).getVoteAverage().toString());
        String mUrlImagePoserW300
                = ApiUrl.BASE_URL_IMAGE_POSTER_W300 + movieList.get(position).getPosterPath();
        Glide
                .with(context)
                .load(mUrlImagePoserW300)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.mImagePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_poster)
        ImageView mImagePoster;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.subtitle)
        TextView mSubTitle;
        @BindView(R.id.description)
        TextView mDescription;
        @BindView(R.id.rating)
        TextView mRating;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
