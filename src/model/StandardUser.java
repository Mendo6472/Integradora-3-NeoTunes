package model;

public class StandardUser extends ConsumerUser{

    private final int MAX_PLAYLISTS = 20;
    private final int MAX_DOWNLOADS = 100;
    private int ammountOfPlaylists = 0;
    private int ammountOfDownloads = 0;

    public StandardUser(String nickName, String id){
        super(nickName, id);
    }

    public String buySong(){
        String msj = "";
        return msj;
    }

    public String addPlaylist(String name, int playlistType){
        String msj = "Playlist añadida exitosamente";
        if(ammountOfPlaylists >= MAX_PLAYLISTS){
            return msj = "Capacidad maxima de playlists alcanzada.";
        }
        Playlist playlist = new Playlist(name, playlistType);
        String code = playlist.getCode();
        playlists.add(playlist);
        this.ammountOfPlaylists += 1;
        msj += ", el codigo es " + code + ", NO LO PIERDAS.";
        return msj;
    }

    public String addPlaylistWithPlaylist(Playlist playlist){
        String msj = "Playlist añadida exitosamente";
        if(ammountOfPlaylists >= MAX_PLAYLISTS){
            return msj = "Capacidad maxima de playlists alcanzada.";
        }
        playlist.generateCodeAgain();
        String code = playlist.getCode();
        playlists.add(playlist);
        this.ammountOfPlaylists += 1;
        msj += ", se añadio a tu lista de playlists con el codigo " + code + ", NO LO PIERDAS.";
        return msj;
    }

}