package model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class ProducerUser {

    protected String name;
    protected String imageUrl;
    protected Date registerDate;
    protected ArrayList<Audio> audios;
    protected int ammountOfPlays;

    /**
     * @param name
     * @param imageUrl
     */
    public ProducerUser(String name, String imageUrl){
        this.ammountOfPlays = 0;
        this.name = name;
        this.imageUrl = imageUrl;
        this.registerDate = new Date(System.currentTimeMillis());
        this.audios = new ArrayList<Audio>();
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    public int getAmmountOfPlays() {
        return ammountOfPlays;
    }

    public void addAmmountOfPlays(){
        ammountOfPlays++;
    }
    
}
