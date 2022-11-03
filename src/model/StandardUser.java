package model;

public class StandardUser extends ConsumerUser{

    private final int MAX_PLAYLISTS = 20;
    private final int MAX_DOWNLOADS = 100;
    private int ammountOfPlaylists = 0;
    private int ammountOfDownloads = 0;

    public StandardUser(String nickName, String id){
        super(nickName, id);
    }

    public String downloadSong(){
        String msj = "";
        return msj;
    }

    public String addPlaylist(String name, int playlistType){
        String msj = "Playlist añadida exitosamente";
        if(ammountOfPlaylists >= MAX_PLAYLISTS){
            return msj = "Capacidad maxima de playlists alcanzada.";
        }
        Playlist playlist = new Playlist(name, playlistType);
        playlists.add(playlist);
        this.ammountOfPlaylists += 1;
        return msj;
    }

}