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

    public int searchPlaylistPos(String name){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < playlists.size() && !found; i++){
            if(playlists.get(i).getName().equals(name)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    public int searchAudioPos(String name, int playlistPos){
        int pos = playlists.get(playlistPos).searchAudioPos(name);
        return pos;
    }

    public void removeAudioFromPlaylist(int audioPos, int playlistPos){
        playlists.get(playlistPos).removeAudioFromPlaylist(audioPos);
    }

    public String addAudioToPlaylist(Audio audio, int playlistPos){
        String msj = "";
        msj = playlists.get(playlistPos).addAudioToPlaylist(audio);
        return msj;
    }

    public abstract String downloadSong();

    public abstract String addPlaylist(String name, int playlistType);
    
}
