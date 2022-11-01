package model;

import java.sql.Date;

public abstract class ConsumerUser {
    
    protected String nickName;
    protected String id;
    protected Date registerDate;

    public ConsumerUser(String nickName, String id){
        this.nickName = nickName;
        this.id = id;
        this.registerDate = new Date(System.currentTimeMillis());
    }

    public String getNickName() {
        return nickName;
    }

    public String getId() {
        return id;
    }
    
}
