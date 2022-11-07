package model;

public class PremiumUser extends ConsumerUser{

    /**
     * @param nickName
     * @param id
     */
    public PremiumUser(String nickName, String id){
        super(nickName, id);
    }

    public String downloadSong(){
        String msj = "";
        return msj;
    }

    public String addPlaylist(String name, int playlistType){
        String msj = "Playlist creada exitosamente";
        Playlist playlist = new Playlist(name, playlistType);
        playlists.add(playlist);
        return msj;
    }
    
}
