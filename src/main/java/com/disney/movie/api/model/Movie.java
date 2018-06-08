package com.disney.movie.api.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table( name = "movie")
public class Movie {

    @Id
    public String id;

    @Column
    public String title;

    @Column
    public String umid;

    @Column
    public String releaseYear;

    @Column
    public String runtime;

    @Column
    public String rating;

    public Movie() {
        this.id = UUID.randomUUID().toString();
    }

    public Movie(String title) {
        this.title = title;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }
}
