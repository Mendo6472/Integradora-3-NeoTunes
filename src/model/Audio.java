package model;

public abstract class Audio {

    protected String name;
    protected String url;
    protected int ammountOfPlays;
    protected String duration;
    protected int durationInSeconds;

    public Audio(String name, String url, String duration, int durationInSeconds){
        this.name = name;
        this.url = url;
        this.ammountOfPlays = 0;
        this.duration = duration;
        this.durationInSeconds = durationInSeconds;
    }

    public void addAmmountOfPlays(int addAmmount) {
        this.ammountOfPlays += addAmmount;
    }

}