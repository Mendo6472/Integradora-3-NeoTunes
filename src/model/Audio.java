package model;

public abstract class Audio {

    protected String name;
    protected String url;
    protected int ammountOfPlays;
    protected String duration;
    protected String creator;

    /**
     * @param name
     * @param url
     * @param duration
     * @param durationInSeconds
     */
    public Audio(String name, String url, String duration, String creator){
        this.name = name;
        this.url = url;
        this.ammountOfPlays = 0;
        this.duration = duration;
        this.creator = creator;
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

    public void addAmmountOfPlays(){
        ammountOfPlays++;
    }

    public String getCreator() {
        return creator;
    }

    public int getAmmountOfPlays() {
        return ammountOfPlays;
    }

}