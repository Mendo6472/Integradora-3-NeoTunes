package model;
import java.util.ArrayList;

public class Playlist {

    private ArrayList<Audio> playlist;

    public Playlist(){
        playlist = new ArrayList<Audio>();
    }

    public ArrayList<Audio> getPlaylist() {
        return playlist;
    }

    public void addAudioToPlaylist(Audio audio){
        this.playlist.add(audio);
    }

}
