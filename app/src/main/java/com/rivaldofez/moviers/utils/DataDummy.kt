package com.rivaldofez.moviers.utils

import com.rivaldofez.moviers.data.source.local.entity.MovieEntity
import com.rivaldofez.moviers.data.source.local.entity.MovieResponse
import com.rivaldofez.moviers.data.source.local.entity.TvShowEntity
import com.rivaldofez.moviers.data.source.local.entity.TvShowResponse
import com.rivaldofez.moviers.data.source.remote.response.movie.*
import com.rivaldofez.moviers.data.source.remote.response.tvshow.*
import com.rivaldofez.moviers.data.source.remote.response.tvshow.GenresItem
import com.rivaldofez.moviers.data.source.remote.response.tvshow.SpokenLanguagesItem

object DataDummy {
    fun generatePopularMovies(): MovieListResponse{
        val movieItem = ArrayList<MovieItem>()

        movieItem.add(
            MovieItem(
                adult = false,
                backdropPath = "/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
                genreIds = mutableListOf(27),
                id = 632357,
                originalLanguage = "en",
                originalTitle = "The Unholy",
                overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                popularity = 6600.217,
                posterPath = "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                releaseDate = "2021-03-31",
                title = "The Unholy",
                video = false,
                voteAverage = 7.1,
                voteCount = 668
        ))

        movieItem.add(
            MovieItem(
                adult = false,
                backdropPath = "/9WlJFhOSCPnaaSmsrv0B4zA8iUb.jpg",
                genreIds = mutableListOf(28,27,53),
                id = 503736,
                originalLanguage = "en",
                originalTitle = "Army of the Dead",
                overview = "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                popularity = 4435.269,
                posterPath = "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                releaseDate = "2021-05-14",
                title = "Army of the Dead",
                video = false,
                voteAverage = 6.6,
                voteCount = 1139
            ))

        movieItem.add(
            MovieItem(
                adult = false,
                backdropPath = "/yyWNPhP1HR4BTLErHcZwIUsMBvA.jpg",
                genreIds = mutableListOf(80,18,9648,53),
                id = 823855,
                originalLanguage = "en",
                originalTitle = "I Am All Girls",
                overview = "A special crimes investigator forms an unlikely bond with a serial killer to bring down a global child sex trafficking syndicate.",
                popularity = 2615.127,
                posterPath = "/m6bUeV4mczG3z2YXXr5XDKPsQzv.jpg",
                releaseDate = "2021-05-14",
                title = "I Am All Girls",
                video = false,
                voteAverage = 7.0,
                voteCount = 85
            ))

        return MovieListResponse(
            page = 1,
            totalPages = 500,
            totalResults = 10000,
            results = movieItem
        )
    }

    fun generatePopularTvShows(): TvShowListResponse{
        val tvShowItem = ArrayList<TvShowItem>()

        tvShowItem.add(
            TvShowItem(
                backdropPath = "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                firstAirDate = "2016-01-25",
                genreIds = mutableListOf(80,10765),
                id = 63174,
                name = "Lucifer",
                originCountry = mutableListOf("US"),
                originalLanguage = "en",
                originalName = "Lucifer",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                popularity = 2045.499,
                posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                voteAverage = 8.5,
                voteCount = 8878
        ))

        tvShowItem.add(
            TvShowItem(
                backdropPath = "/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
                firstAirDate = "2014-10-07",
                genreIds = mutableListOf(18,10765),
                id = 60735,
                name = "The Flash",
                originCountry = mutableListOf("US"),
                originalLanguage = "en",
                originalName = "The Flash",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                popularity = 1044.283,
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                voteAverage = 7.7,
                voteCount = 7713
        ))

        tvShowItem.add(
            TvShowItem(
                backdropPath = "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                firstAirDate = "2017-09-25",
                genreIds = mutableListOf(18),
                id = 71712,
                name = "The Good Doctor",
                originCountry = mutableListOf("US"),
                originalLanguage = "en",
                originalName = "The Good Doctor",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                popularity = 944.21,
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                voteAverage = 8.6,
                voteCount = 8507
        ))

        return TvShowListResponse(
            page = 1,
            totalPages = 500,
            totalResults = 10000,
            results = tvShowItem
        )
    }

    fun generateDetailTvShow(): TvShowEntityResponse {
        return TvShowEntityResponse(
            backdropPath = "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
            createdBy = mutableListOf(
                CreatedByItem(
                    id = 1222585,
                    creditId = "55fdc50ec3a368132a001852",
                    name = "",
                    gender = 2,
                    profilePath = "",
                )
            ),
            episodeRunTime = mutableListOf(
                45
            ),
            firstAirDate = "2016-01-25",
            genres = mutableListOf(
                GenresItem(
                    id = 80,
                    name = "Crime"
                ),
                GenresItem(
                    id = 10765,
                    name = "Sci-Fi & Fantasy"
                )
            ),
            homepage = "https://www.netflix.com/title/80057918",
            id = 63174,
            inProduction = true,
            languages = mutableListOf(
                "en"
            ),
            lastAirDate = "2021-05-28",
            lastEpisodeToAir = LastEpisodeToAir(
                airDate = "2021-05-28",
                episodeNumber = 16,
                id = 2856945,
                name = "A Chance at a Happy Ending",
                overview = "peler and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                productionCode = "",
                seasonNumber = 5,
                stillPath = "/cYY0U8DAkCRAWO6rnIcZ2gW17Fz.jpg",
                voteAverage = 10F,
                voteCount = 1
            ),
            name = "Lucifer",
            nextEpisodeToAir = "null",
            networks = mutableListOf(
                NetworksItem(
                    name = "FOX",
                    id = 19,
                    logoPath = "/1DSpHrWyOORkL9N2QHX7Adt31mQ.png",
                    originCountry = "US"
                ),
                NetworksItem(
                    name = "Netflix",
                    id = 213,
                    logoPath = "/wwemzKWzjKYJFfCeiB57q3r4Bcm.png",
                    originCountry = ""
                )
            ),
            numberOfEpisodes = 93,
            numberOfSeasons = 6,
            originCountry = mutableListOf(
                "US"
            ),
            originalLanguage = "en",
            originalName = "Lucifer",
            popularity = 2154.69,
            posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            productionCompanies = mutableListOf(
                ProductionCompaniesItem(
                    id = 43346,
                    logoPath = "null",
                    name = "Fox Productions",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 1957,
                    logoPath = "/3T19XSr6yqaLNK8uJWFImPgRax0.png",
                    name = "Warner Bros. Television",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 57542,
                    logoPath = "null",
                    name = "Aggressive Mediocrity",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 9993,
                    logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                    name = "DC Entertainment",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 40041,
                    logoPath = "/oP8TmVSh9DCP1yhR2yvjnKfMgbg.png",
                    name = "Jerry Bruckheimer Television",
                    originCountry = "US"
                )
            ),
            productionCountries = mutableListOf(
                ProductionCountriesItem(
                    name = "United States of America",
                    iso31661 = "US"
                )
            ),
            seasons = mutableListOf(
                SeasonsItem(
                    airDate = "2015-07-10",
                    episodeCount = 4,
                    id = 70781,
                    name = "Specials",
                    overview = "",
                    posterPath = "/bQ5FupU7DFTbx9pSgPsEZQwyZKj.jpg",
                    seasonNumber = 0,
                )
            ),
            spokenLanguages = mutableListOf(
                SpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Returning Series",
            tagline = "It's good to be bad.",
            type = "Scripted",
            voteCount = 8928,
            voteAverage = 8.5F,
            overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
        )
    }

    fun generateDetailMovie(): MovieEntityResponse{
        return MovieEntityResponse(
            adult = false,
            backdropPath = "/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
            belongsToCollection = BelongsToCollection(
                name = "",
                id = 0,
                posterPath = "",
                backdropPath = ""
            ),
            budget = 10000000,
            genres = mutableListOf(
                com.rivaldofez.moviers.data.source.remote.response.movie.GenresItem(
                    id = 27,
                    name = "Horror"
                )
            ),
            homepage = "https://www.sonypictures.com/movies/theunholy",
            id = 632357,
            imdbId = "tt9419056",
            originalLanguage = "en",
            originalTitle = "The Unholy",
            overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            popularity = 6600.217,
            posterPath = "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
            productionCompanies = mutableListOf(
                ProductionCompaniesItem(
                    id = 768,
                    logoPath = "null",
                    name = "Ghost House Pictures",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 3287,
                    logoPath = "/bz6GbCQQXGNE56LTW9dwgksW0Iw.png",
                    name = "Screen Gems",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 34,
                    logoPath = "/GagSvqWlyPdkFHMfQ3pNq6ix9P.png",
                    name = "Sony Pictures",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 6951,
                    logoPath = "null",
                    name = "Chapman/Leonard Studio Equipment",
                    originCountry = ""
                ),
            ),
            productionCountries = mutableListOf(
                ProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            releaseDate = "2021-03-31",
            revenue = 29082988,
            runtime = 99,
            spokenLanguages = mutableListOf(
                com.rivaldofez.moviers.data.source.remote.response.movie.SpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Released",
            tagline = "Be careful who you pray to.",
            title = "The Unholy",
            video = false,
            voteAverage = 7.1F,
            voteCount = 669
        )
    }

    fun generateDummyDetailMovies(data: MovieEntityResponse, isFavorite: Boolean): MovieEntity{
        val languages = data.spokenLanguages.joinToString { it.englishName }
        val genres = data.genres.joinToString { it.name }
        return MovieEntity(
            id = data.id,
            originalLanguage = data.originalLanguage,
            imdbId = data.imdbId,
            video = data.video,
            title = data.title,
            backdropPath = data.backdropPath,
            revenue = data.revenue,
            popularity = data.popularity,
            voteCount = data.voteCount,
            budget = data.budget,
            overview = data.overview,
            originalTitle = data.originalTitle,
            runtime = data.runtime,
            posterPath = data.posterPath,
            releaseDate = data.releaseDate,
            voteAverage = data.voteAverage,
            tagline = data.tagline,
            adult = data.adult,
            homepage = data.homepage,
            status = data.status,
            language = languages,
            genre = genres,
            isFavorite = isFavorite)
    }

    fun generateDummyDetailTvShows(data: TvShowEntityResponse, isFavorite: Boolean): TvShowEntity{
        val languages = data.spokenLanguages.joinToString { it.englishName }
        val genres = data.genres.joinToString { it.name }
        return TvShowEntity(
            id = data.id,
            originalLanguage = data.originalLanguage,
            numberOfEpisodes = data.numberOfEpisodes,
            type = data.type,
            backdropPath = data.backdropPath.toString(),
            popularity = data.popularity,
            numberOfSeasons = data.numberOfSeasons,
            voteCount = data.voteCount,
            firstAirDate = data.firstAirDate,
            overview = data.overview,
            posterPath = data.posterPath,
            originalName = data.originalName,
            voteAverage = data.voteAverage,
            name = data.name,
            tagline = data.tagline,
            inProduction = data.inProduction,
            lastAirDate = data.lastAirDate,
            homepage = data.homepage,
            status = data.status,
            language = languages,
            genre = genres,
            latestEpisode = data.lastEpisodeToAir.episodeNumber.toString(),
            isFavorite = isFavorite
        )
    }


    fun generateMovieListResponses(): List<MovieResponse>{
        val popularMovieList = ArrayList<MovieResponse>()
        for(response in generatePopularMovies().results){
            val movie = MovieResponse(
                id = response.id,
                overview = response.overview,
                originalLanguage = response.originalLanguage,
                originalTitle = response.originalTitle,
                video = response.video,
                title = response.title,
                posterPath = response.posterPath,
                backdropPath = response.backdropPath,
                releaseDate = response.releaseDate,
                popularity = response.popularity,
                voteAverage = response.voteAverage,
                adult = response.adult,
                voteCount = response.voteCount

            )
            popularMovieList.add(movie)
        }
        return popularMovieList
    }

    fun generateTvShowListResponses(): List<TvShowResponse>{
        val popularTvShowList = ArrayList<TvShowResponse>()
        for(response in generatePopularTvShows().results){
            val tvshow = TvShowResponse(
                id = response.id,
                firstAirDate = response.firstAirDate,
                overview = response.overview,
                originalLanguage = response.originalLanguage,
                posterPath = response.posterPath,
                backdropPath = response.backdropPath,
                originalName = response.originalName,
                popularity = response.popularity,
                voteAverage = response.voteAverage,
                name = response.name,
                voteCount = response.voteCount
            )
            popularTvShowList.add(tvshow)
        }
        return popularTvShowList
    }

    fun generateFavoriteMovieEntity(): List<MovieEntity>{
        val favoriteArrayList = ArrayList<MovieEntity>()
        favoriteArrayList.add(generateDummyDetailMovies(generateDetailMovie(), true))

        return favoriteArrayList
    }

    fun generateFavoriteTvShowEntity(): List<TvShowEntity>{
        val favoriteArrayList = ArrayList<TvShowEntity>()
        favoriteArrayList.add(generateDummyDetailTvShows(generateDetailTvShow(), true))

        return favoriteArrayList
    }
}