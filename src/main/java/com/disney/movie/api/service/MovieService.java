package com.disney.movie.api.service;

import com.disney.movie.api.MovieApplication;
import com.disney.movie.api.exception.BadRequestException;
import com.disney.movie.api.model.Movie;
import com.disney.movie.api.repository.MovieRepository;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public MovieService(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(String id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createMovie(Movie movie) {
        validateMovie(movie);
        Movie persistedMovie = movieRepository.save(movie);
        notifySubscriberAboutNewMovie(movie.title);
        return persistedMovie;
    }

    private void notifySubscriberAboutNewMovie(String title) {
        try {
            rabbitTemplate.convertAndSend(MovieApplication.topicExchangeName, "foo.bar.baz", "New Movie Notification: " + title);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    private void validateMovie(Movie movie) {
        if(movie == null) {
            throw new BadRequestException("movie should not be null");
        }
        if(movie.title == null || movie.title.length() <= 0) {
            throw new BadRequestException("title is a required field.");
        }
        if(movie.rating == null || movie.rating.length() <= 0) {
            throw new BadRequestException("rating is a required field.");
        }
        if(movie.releaseYear == null || movie.releaseYear.length() <= 0) {
            throw new BadRequestException("releaseYear is a required field.");
        }
        if(movie.runtime == null || movie.runtime.length() <= 0) {
            throw new BadRequestException("runtime is a required field.");
        }
        if(movie.umid == null || movie.umid.length() <= 0) {
            throw new BadRequestException("umid is a required field.");
        }
    }
}
