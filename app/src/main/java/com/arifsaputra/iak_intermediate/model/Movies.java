package com.arifsaputra.iak_intermediate.model;

import java.io.Serializable;

/**
 * Created by Chyrus on 8/13/17.
 * Muh Arif Saputra (Android Developer)
 */

public class Movies implements Serializable {

    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private double vote_average;
    private int vote_count;
    private String favorite;

    public Movies() {
    }

    public Movies(int id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, double vote_average, int vote_count) {
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    //contructor for sqlite
    public Movies(int id, String original_title, String favorite) {
        this.id = id;
        this.original_title = original_title;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
