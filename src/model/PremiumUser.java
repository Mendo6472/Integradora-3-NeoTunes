package model;

public class PremiumUser extends ConsumerUser implements PremiumPlayback{

    /**
     * @param nickName
     * @param id
     */
    public PremiumUser(String nickName, String id){
        super(nickName, id);
    }

    public String buySong(){
        String msj = "Se a comprado la cancion con exito";
        return msj;
    }

    public String addPlaylist(String name, int playlistType){
        String msj = "Playlist añadida exitosamente";
        Playlist playlist = new Playlist(name, playlistType);
        String code = playlist.getCode();
        playlists.add(playlist);
        msj += ", el codigo es " + code + ", NO LO PIERDAS.";
        return msj;
    }

    public String addPlaylistWithPlaylist(Playlist playlist){
        String msj = "Playlist añadida exitosamente";
        playlist.generateCodeAgain();
        String code = playlist.getCode();
        playlists.add(playlist);
        msj += ", se añadio a tu lista de playlists con el codigo " + code + ", NO LO PIERDAS.";
        return msj;
    }

    public String playAudio(Audio audio){
        String msj;
        msj = "Reproduciendo " + audio.getName() + "...";
        return msj;
    }
}
