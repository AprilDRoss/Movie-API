package com.example.Movie;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    List<Movie> results = new ArrayList<>();

    public ResultsPage() {
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
