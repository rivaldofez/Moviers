package com.rivaldofez.moviers.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rivaldofez.moviers.R
import com.rivaldofez.moviers.utils.*
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest{
    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test01_LoadMovies(){
        //check recylerview and scroll to last data from api
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun test02_loadDetailMovie(){
        //Click on first item
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        //check displayed item
        var movieAttribute = listOf(R.id.img_backdrop, R.id.img_poster, R.id.chart_popularity,
            R.id.tv_date, R.id.tv_duration)
        for(idAttr in movieAttribute){
            onView(withId(idAttr)).check(matches(isDisplayed()))
        }

        //scroll page to end
        onView(withId(R.id.btn_home_page)).perform(ViewActions.scrollTo())

        //check next displayed item after scrolling
        movieAttribute = listOf(R.id.tv_synopsis, R.id.tv_synopsis_title, R.id.tv_more_title,
            R.id.tv_original_title, R.id.tv_original, R.id.tv_genre_title, R.id.ll_genre, R.id.tv_language_title,
            R.id.ll_language, R.id.tv_homepage_title, R.id.tv_homepage, R.id.tv_budget, R.id.tv_budget_title,
            R.id.tv_revenue_title, R.id.tv_revenue, R.id.tv_status_title, R.id.tv_status, R.id.btn_home_page, R.id.btn_favorite)

        for(idAttr in movieAttribute){
            onView(withId(idAttr)).check(matches(isDisplayed()))
        }

    }

    @Test
    fun test03_LoadFavoriteMoviesWithNoItem(){
        //Run one time only
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.tv_message)).check(matches(isDisplayed()))
    }

    @Test
    fun test04_LoadFavoriteMoviesWithItem(){
        //Run one time only
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun test05_DeleteItemFavorite(){
        //Run one time only
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }

    @Test
    fun test06_loadHomePageMovies(){
        //check web view movies home page
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.btn_home_page)).perform(ViewActions.scrollTo())
        onView(withId(R.id.btn_home_page)).perform(click())
        onView(withId(R.id.web_trailer)).check(matches(isDisplayed()))
    }

    @Test
    fun test07_loadTvShows(){
        //check recylerview and scroll to last data from api
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun test08_LoadDetailTvShow(){
        //Click on first item
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        //check displayed item
        var tvShowAttribute = listOf(R.id.img_backdrop, R.id.img_poster, R.id.chart_popularity,
            R.id.tv_date, R.id.tv_latest_episode)
        for(idAttr in tvShowAttribute){
            onView(withId(idAttr)).check(matches(isDisplayed()))
        }

        //scroll page to end
        onView(withId(R.id.btn_home_page)).perform(ViewActions.scrollTo())

        //check next displayed item after scrolling
        tvShowAttribute = listOf(R.id.tv_synopsis, R.id.tv_synopsis_title, R.id.tv_more_title,
            R.id.tv_original_title, R.id.tv_original, R.id.tv_genre_title, R.id.ll_genre, R.id.tv_language_title,
            R.id.ll_language, R.id.tv_season_title, R.id.tv_season, R.id.tv_episode_title, R.id.tv_episode,
            R.id.tv_homepage_title, R.id.tv_homepage, R.id.tv_status, R.id.btn_home_page, R.id.btn_favorite)

        for(idAttr in tvShowAttribute){
            onView(withId(idAttr)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun test09_LoadFavoriteTvShowWithNoItem(){
        //Run one time only
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.tv_tvshow_message)).check(matches(isDisplayed()))
    }

    @Test
    fun test10_LoadFavoriteTvShowWithItem(){
        //Run one time only
        onView(withId(R.id.rv_movies)).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
    }

    @Test
    fun test11_LoadHomepageTv(){
        //check web view movies home page
        onView(isRoot()).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.btn_home_page)).perform(ViewActions.scrollTo())
        onView(withId(R.id.btn_home_page)).perform(click())
        onView(withId(R.id.web_trailer)).check(matches(isDisplayed()))
    }

    @Test
    fun test12_DeleteItemTvShowFavorite(){
        //Run one time only
        onView(withId(R.id.rv_movies)).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }
}