package com.rivaldofez.moviers.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    val sortFilter = listOf("Name", "Release Date", "Rating")

    fun getSortedPopularMovieQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieresponse ")

        when(filter){
            sortFilter[0] -> simpleQuery.append("ORDER by title ASC")
            sortFilter[1] -> simpleQuery.append("ORDER by releaseDate ASC")
            sortFilter[2] -> simpleQuery.append("ORDER by voteAverage DESC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedPopularTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshowresponse ")

        when(filter){
            sortFilter[0] -> simpleQuery.append("ORDER by name ASC")
            sortFilter[1] -> simpleQuery.append("ORDER by firstAirDate ASC")
            sortFilter[2] -> simpleQuery.append("ORDER by voteAverage DESC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}