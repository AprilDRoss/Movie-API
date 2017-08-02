package com.example.Movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class BasicController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/now-playing")
    public String nowPlaying (Model model){
        List <Movie> movies = getMovies("https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb");
        model.addAttribute("movies", movies);
        return "now-playing";
    }

    @RequestMapping("/medium-popular-long-name")
    public String popular (Model model){
        List<Movie> movies = getMovies("https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb");
        List<Movie> popularMovieList = movies.stream()
                .filter(movie -> movie.getTitle().length()>=10)
                .filter(movie -> movie.getPopularity()>=30 && movie.getPopularity()<=80)
                .collect(Collectors.toList());
        model.addAttribute("movies",popularMovieList);
        return "medium-popular-long-name";
    }

    public static List<Movie> getMovies(String route){
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject(route, ResultsPage.class);
        List <Movie> movies = resultsPage.getResults();
        return movies;

     }

    }

