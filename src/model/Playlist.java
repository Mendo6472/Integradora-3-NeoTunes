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

    public String getName() {
        return name;
    }

    public String addAudioToPlaylist(Audio audio){
        String msj = "Audio añadido con exito";
        if(this.playlistType == PlaylistType.SONGS){
            msj = addSongToPlaylist(audio);
        } else if (this.playlistType == PlaylistType.PODCASTS){
            msj = addPodcastToPlaylist(audio);
        } else {
            playlist.add(audio);
        }
        return msj;
    }

    public String addSongToPlaylist(Audio audio){
        String msj = "Audio añadido con exito";
        if(!(audio instanceof Song)){
            return msj = "La playlist solo acepta canciones.";
        }
        playlist.add(audio);
        return msj;
    }

    public String addPodcastToPlaylist(Audio audio){
        String msj = "Audio añadido con exito";
        if(!(audio instanceof Podcast)){
            return msj = "La playlist solo acepta podcasts.";
        }
        playlist.add(audio);
        return msj;
    }

    public int searchAudioPos(String name){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < playlist.size() && !found; i++){
            if(playlist.get(i).getName().equals(name)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    public void removeAudioFromPlaylist(int audioPos){
        playlist.remove(audioPos);
    }

}
