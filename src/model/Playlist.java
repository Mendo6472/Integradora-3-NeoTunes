package model;
import java.util.ArrayList;

public class Playlist {

    private ArrayList<Audio> playlist;
    private String name;
    private PlaylistType playlistType;

    public Playlist(String name, int playlistType){
        playlist = new ArrayList<Audio>();
        this.name = name;
        switch(playlistType){
            case 1 -> this.playlistType = PlaylistType.SONGS;
            case 2 -> this.playlistType = PlaylistType.PODCASTS;
            case 3 -> this.playlistType = PlaylistType.SONGS_AND_PODCASTS;
        }
    }

    public ArrayList<Audio> getPlaylist() {
        return playlist;
    }

    public void addAudioToPlaylist(Audio audio){
        this.playlist.add(audio);
    }

}
