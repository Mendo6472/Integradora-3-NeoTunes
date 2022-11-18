package model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class ProducerUser {

    protected String name;
    protected String imageUrl;
    protected Date registerDate;
    protected ArrayList<Audio> audios;

    /**
     * @param name
     * @param imageUrl
     */
    public ProducerUser(String name, String imageUrl){
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
    
}
