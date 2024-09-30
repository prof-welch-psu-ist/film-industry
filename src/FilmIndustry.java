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
            return false;
        }
    }

    public record Actor(String firstName, String lastName, ActingStyle style, int industryYears)
            implements FilmIndustryWorker {}

    public static void main(String[] args) {


    }
}
