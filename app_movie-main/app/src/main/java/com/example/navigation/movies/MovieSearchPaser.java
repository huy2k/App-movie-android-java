package com.example.navigation.movies;
import android.os.AsyncTask;

import com.example.navigation.model.MovieData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public abstract class MovieSearchPaser extends AsyncTask<String, Void, ArrayList<MovieData>> {

    public ArrayList<MovieData> moviesList = new ArrayList<>();

    @Override
    protected ArrayList<MovieData> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String param_type = params[0];
        final String BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=4840c7126f832af90ab18051d9481afb&language=en-US&query="
                + param_type+"&page=1&include_adult=false";
        try {

            URL url = new URL(BASE_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStreamReader stream = new InputStreamReader(urlConnection.getInputStream());
            reader = new BufferedReader(stream);

            final StringBuilder textBuilder = new StringBuilder();
            String line;
            String finalResult;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
            }
            finalResult = textBuilder.toString();
            JSONObject jsonObject = new JSONObject(finalResult);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject currentMovie = jsonArray.getJSONObject(i);
                String id = currentMovie.getString("id");
                String poster = currentMovie.getString("poster_path");
                String overview = currentMovie.getString("overview");
                String release = currentMovie.getString("release_date");
                String title = currentMovie.getString("title");
                String rate = currentMovie.getString("vote_average");
                String backdropPath = currentMovie.getString("backdrop_path");

                MovieData movieData = new MovieData(poster, overview, release, id, title, rate, backdropPath);
                moviesList.add(movieData);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    @Override
    protected abstract void onPostExecute(ArrayList<MovieData> movieData);
}
