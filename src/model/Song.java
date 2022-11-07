package model;

public class Song extends Audio{

    private String album;
    private Genre genre;
    private double cost;
    

    /**
     * @param name
     * @param url
     * @param duration
     * @param durationInSeconds
     * @param album
     * @param genre
     * @param cost
     */
    public Song(String name, String url, String duration, int durationInSeconds, String album, int genre, double cost){
        super(name, url, duration, durationInSeconds);
        this.album = album;
        this.cost = cost;
        switch(genre){
            case 1 -> this.genre = Genre.ROCK;
            case 2 -> this.genre = Genre.POP;
            case 3 -> this.genre = Genre.TRAP;
            case 4 -> this.genre = Genre.HOUSE;
        }
    }
}
