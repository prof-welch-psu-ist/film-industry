import java.awt.*;
import java.util.ArrayList;

public class FilmIndustry {

    public enum MovieGenre {
        Horror, SciFi, Romance, Comedy, Drama, Action
    }

    public enum ActingStyle {Method, Character, Improv}

    public interface FilmIndustryWorker {
        // abstract methods:
        String firstName();
        String lastName();
        int industryYears();

        // returns true only if THIS film industry worker specializes in movie genre g
        boolean specializesInGenre(MovieGenre g);
    }

    public record Director(String firstName, String lastName, int industryYears,
                           boolean shootDigitalFilm)
            implements FilmIndustryWorker {

        @Override public boolean specializesInGenre(MovieGenre g) {
            return switch (g)  {
                case Romance, Comedy, Horror, Action, Drama -> true;
                case SciFi                                  -> shootDigitalFilm;
            };
        }
    }

    public record Producer(String firstName, String lastName, int industryYears)
            implements FilmIndustryWorker {

        @Override public boolean specializesInGenre(MovieGenre g) {
            boolean theAnswer = switch (g) {
                case SciFi  -> industryYears > 5;
                default     -> true;
            };
            return theAnswer; // note: can be simplified
        }
    }

    public record Actor(String firstName, String lastName, ActingStyle style, int industryYears)
            implements FilmIndustryWorker {
        @Override public boolean specializesInGenre(MovieGenre g) {
            var theAnswer = switch (g) {
                case Action, Romance -> true;
                case Horror, SciFi -> industryYears > 10;
                case Comedy , Drama -> style == ActingStyle.Improv || style == ActingStyle.Character;
            };
            return theAnswer;
        }
    }

    public enum MPAARating {
        R, PG13, G, PG
    }

    // a record type to represent a movie
    public record Movie(String title, Director director, Producer producer, int yearReleased,
                     MPAARating rating, ArrayList<Actor> actors, MovieGenre g) implements Comparable<Movie> {

        public int countNumberOfImprovActorsInMovie() {
            int result = 0;
            for (Actor a : actors) {
                if (a.style() == ActingStyle.Improv) {
                    result++;
                }
            }
            return result;
        }

        @Override public int compareTo(Movie o) {
            return this.yearReleased - o.yearReleased;
        }
    }

    public static void main(String[] args) {

        Producer producer = new Producer("J", "Abrams", 15);
        Director director = new Director("Steven", "S", 30, true);

        ArrayList<Actor> actors = new ArrayList<>();

        actors.add(new Actor("Arnold", "S", ActingStyle.Improv, 40));
        actors.add(new Actor("Sarah", "C", ActingStyle.Method, 50));
        actors.add(new Actor("Stephen", "S", ActingStyle.Method, 3));
        Movie terminator2 = new Movie("Terminator 2", director, producer, 1994, MPAARating.R, actors, MovieGenre.Action);
        // ^^^^^^ unwieldy when creating a movie (too many things to pass in...)
        // would be a good place for a builder...
        /*
        Movie terminator3 = new MovieBuilder("Terminator 3")
                .addActor(a1)
                .addActor(a2)
                .rating(MPAARating.R).genre(MovieGenre.Horror)
                .build();
         */

    }
}
