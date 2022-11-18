package model;

public class Song extends Audio{

    private String album;
    private Genre genre;
    private double cost;
    private int ammountOfSells;
    private int valueOfTotalSells;
    

    /**
     * @param name
     * @param url
     * @param duration
     * @param durationInSeconds
     * @param album
     * @param genre
     * @param cost
     */
    public Song(String name, String creator, String url, String duration, String album, int genre, double cost){
        super(name, url, duration, creator);
        this.ammountOfSells = 0;
        this.valueOfTotalSells = 0;
        this.album = album;
        this.cost = cost;
        switch(genre){
            case 1 -> this.genre = Genre.ROCK;
            case 2 -> this.genre = Genre.POP;
            case 3 -> this.genre = Genre.TRAP;
            case 4 -> this.genre = Genre.HOUSE;
        }
    }

    public String getAlbum() {
        return album;
    }

    public int getAmmountOfSells() {
        return ammountOfSells;
    }

    public int getValueOfTotalSells() {
        return valueOfTotalSells;
    }

    public void addAmmountOfSells(){
        ammountOfSells++;
        valueOfTotalSells += cost;
    }

    public double getCost() {
        return cost;
    }

    public Genre getGenre() {
        return genre;
    }
}
