package model;

public class Song extends Audio{

    private String album;
    private Genre genre;
    

    public Song(String name, String url){
        super(name, url);
    }
}
