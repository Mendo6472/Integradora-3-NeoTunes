package model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class ConsumerUser {
    
    protected String nickName;
    protected String id;
    protected Date registerDate;
    protected ArrayList<Playlist> playlists;

    public ConsumerUser(String nickName, String id){
        this.nickName = nickName;
        this.id = id;
        this.registerDate = new Date(System.currentTimeMillis());
        this.playlists = new ArrayList<Playlist>();
    }

    public String getNickName() {
        return nickName;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public abstract String downloadSong();

    public abstract String addPlaylist(String name, int playlistType);
    
}
