package model;

public abstract class Audio {

    protected String name;
    protected String url;
    protected int ammountOfPlays;

    public Audio(String name, String url){
        this.name = name;
        this.url = url;
        this.ammountOfPlays = 0;
    }

    public void addAmmountOfPlays(int addAmmount) {
        this.ammountOfPlays += addAmmount;
    }

}