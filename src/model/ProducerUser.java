package model;

import java.sql.Date;

public abstract class ProducerUser {

    protected String name;
    protected String imageUrl;
    protected Date registerDate;

    public ProducerUser(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        this.registerDate = new Date(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }
    
}
