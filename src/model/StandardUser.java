package model;

public class StandardUser extends ConsumerUser implements StandardPlayback{

    private final int MAX_PLAYLISTS = 20;
    private final int MAX_DOWNLOADS = 100;
    private int ammountOfPlaylists = 0;
    private int ammountOfBuys = 0;
    private int currentReproduction = 0;

    public StandardUser(String nickName, String id){
        super(nickName, id);
    }

    public String buySong(){
        String msj = "Cancion comprada con exito";
        if(ammountOfBuys >= 100){
            return msj = "Capacidad maxima para comprar canciones alcanzada";
        }
        ammountOfBuys++;
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

    public String playAudio(Audio audio){
        String msj = "";
        if(!(audio instanceof Podcast)){
            if(currentReproduction >= 2){
                currentReproduction = 0;   
            } else {
                currentReproduction++;
            }
        }

        msj = "Reproduciendo " + audio.getName() + "...";
        return msj;
    }

    public int getCurrentReproduction() {
        return currentReproduction;
    }

    public int getAmmountOfBuys() {
        return ammountOfBuys;
    }
}