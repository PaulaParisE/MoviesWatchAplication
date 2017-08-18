package com.example.paulapariselias.movieswatchaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paulapariselias.movieswatchaplication.models.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Movie> movies;
    public static final String MOVIE_ID = "com.example.paulapariselias.movieswatchaplication.KEY.MOVIE_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameMovieEditText = (EditText) findViewById(R.id.nameMovie);



        Button lastMovie = (Button) findViewById(R.id.latsMovieBtn);
        lastMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = movies.size();
                if (size > 0){
                   Movie movie = movies.get(size -1);
                    long movieid = movie.getId();

                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra (MOVIE_ID, movieid );
                    startActivity(intent);

                } else {

                    Toast.makeText(MainActivity.this, "No hay películas", Toast.LENGTH_SHORT).show();
                }





            }
        });



        Button save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie = nameMovieEditText.getText().toString();
                if (movie.trim().length() > 0){
                    Movie film = new Movie(movie, false);
                    film.save();
                    nameMovieEditText.setText("");
                    Toast.makeText(MainActivity.this, "Película Guardada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Ingresa una Película", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    private List<Movie> getMovies() {
        return Movie.find(Movie.class, "watched = 0");

    }

    @Override
    protected void onResume() {
        super.onResume();
        movies =getMovies();
    }

}
