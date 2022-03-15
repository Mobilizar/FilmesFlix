package com.br.natanfc.filmesflix.implementations

import android.util.Log
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.data.MovieDataSource
import com.br.natanfc.filmesflix.domain.Movie

class MovieDataSourceImplementation(private val movieRestApiTask: MovieRestApiTask) :
    MovieDataSource {

    companion object {
        const val TAG = "MovieRepository"
    }

    private val movieList = arrayListOf<Movie>()

    override fun getAllMovies(): List<Movie> {
        val request = movieRestApiTask.retrofitApi().getAllMovies().execute()

        if (request.isSuccessful) {
            request.body()?.let { movies ->
                movieList.addAll(movies)
            }
        } else {
            request.errorBody()?.let { error ->
                Log.d(TAG, error.toString())
            }
        }

        return movieList
    }


}