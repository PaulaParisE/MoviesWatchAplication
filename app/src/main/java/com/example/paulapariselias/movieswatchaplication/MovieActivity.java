package com.example.paulapariselias.movieswatchaplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.example.paulapariselias.movieswatchaplication.models.Movie;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        checkMovieV = (CheckBox) findViewById(R.id.watchMovie);
        long idMovie = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);
        movie= Movie.findById(Movie.class,idMovie);
        getSupportActionBar().setTitle(movie.getName());

    }


    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(checkMovieV.isChecked());
        movie.save();
    }

    private Movie movie;
    private CheckBox checkMovieV;
}
