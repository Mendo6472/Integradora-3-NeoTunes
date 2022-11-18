package model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class ConsumerUser {
    
    protected String nickName;
    protected String id;
    protected Date registerDate;
    protected ArrayList<Playlist> playlists;

    /**
     * @param nickName
     * @param id
     */
    public ConsumerUser(String nickName, String id){
        this.nickName = nickName;
        this.id = id;
        this.registerDate = new Date(System.currentTimeMillis());
        this.playlists = new ArrayList<Playlist>();
    }

    /**
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * @param playlists
     */
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * @param name
     * @return
     */
    public int searchPlaylistPos(String code){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < playlists.size() && !found; i++){
            if(playlists.get(i).getCode().equals(code)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }
    
    public Playlist getPlaylist(int playlistPos){
        Playlist playlist = playlists.get(playlistPos);
        return playlist;
    }

    /**
     * @param name
     * @param playlistPos
     * @return
     */
    public int searchAudioPos(String name, int playlistPos){
        int pos = playlists.get(playlistPos).searchAudioPos(name);
        return pos;
    }

    /**
     * @param audioPos
     * @param playlistPos
     */
    public void removeAudioFromPlaylist(int audioPos, int playlistPos){
        playlists.get(playlistPos).removeAudioFromPlaylist(audioPos);
    }

    /**
     * @param audio
     * @param playlistPos
     * @return
     */
    public String addAudioToPlaylist(Audio audio, int playlistPos){
        String msj = "";
        msj = playlists.get(playlistPos).addAudioToPlaylist(audio);
        return msj;
    }

    /**
     * @return
     */
    public abstract String downloadSong();

    /**
     * @param name
     * @param playlistType
     * @return
     */
    public abstract String addPlaylist(String name, int playlistType);

    public abstract String addPlaylistWithPlaylist(Playlist playlist);
    
}
