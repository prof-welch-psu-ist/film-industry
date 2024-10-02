package edu.psu.ist;

import java.util.ArrayList;
import java.util.Collections;

public class FilmIndustry {

    public enum ActingStyle {
        Method, Character, Improv
    }

    public interface FilmIndustryWorker {

        // abstract methods
        String firstName();

        String lastName();

        int yearsInIndustry();
    }

    public record Director(String firstName, String lastName, int yearsInIndustry,
                           boolean shootsDigital)
            implements FilmIndustryWorker {
    }

    public record Producer(String firstName, String lastName, int yearsInIndustry)
            implements FilmIndustryWorker {
    }

    public record Actor( String firstName, String lastName, int yearsInIndustry, ActingStyle style)
            implements FilmIndustryWorker {
    }

    public enum MPAARating {
        R, PG, PG13, G
    }
    public record Movie(String title, Director director, Producer producer,
                        int yearReleased, MPAARating rating, ArrayList<Actor> actors) implements Comparable<Movie> {
        @Override public int compareTo(Movie o) { // <--- think "o" as in "other" Movie
            return this.yearReleased - o.yearReleased();
        }
    }

    public static void main(String[] args) {
        var producer = new Producer("J", "Abrams", 15);
        var director = new Director("Stephen", "Speilberg", 30, true);

        var actors = new ArrayList<Actor>();
        actors.add(new Actor("Arnold", "S", 45, ActingStyle.Method));
        actors.add(new Actor("Sarah", "C", 20, ActingStyle.Character));
        actors.add(new Actor("A", "b", 5, ActingStyle.Improv));

        var m1 = new Movie("Terminator 2", director, producer, 1994, MPAARating.R, actors);
        ArrayList<Movie> movies = new ArrayList<>();
        Collections.sort(movies);

        //var m2 = new Movie("The Martian", )
        /*
        Movie m3 = new MovieBuilder("The Martian")
                            .addActor(arnold)
                            .mpaa(MPAARating.R)
                            .addActor(sarahC)
                            .director(directorX)
                            .producer(JAbrams)
                            .build(); //<--- constructing the final (immutable) Movie object
         */


    }

}
