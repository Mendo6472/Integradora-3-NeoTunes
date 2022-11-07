package model;

public abstract class Audio {

    protected String name;
    protected String url;
    protected int ammountOfPlays;
    protected String duration;
    protected int durationInSeconds;

    /**
     * @param name
     * @param url
     * @param duration
     * @param durationInSeconds
     */
    public Audio(String name, String url, String duration, int durationInSeconds){
        this.name = name;
        this.url = url;
        this.ammountOfPlays = 0;
        this.duration = duration;
        this.durationInSeconds = durationInSeconds;
    }

    /**
     * @param addAmmount
     */
    public void addAmmountOfPlays(int addAmmount) {
        this.ammountOfPlays += addAmmount;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

}