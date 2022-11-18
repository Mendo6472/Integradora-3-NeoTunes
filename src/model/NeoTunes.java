package model;
import java.util.ArrayList;

public class NeoTunes {

    private ArrayList<ConsumerUser> consumersUsers;
    private ArrayList<ProducerUser> producersUsers;
    private ArrayList<Audio> audios;

    public NeoTunes(){
        consumersUsers = new ArrayList<ConsumerUser>();
        producersUsers = new ArrayList<ProducerUser>();
        audios = new ArrayList<Audio>();
    }

    /**
     * @param nickName
     * @param id
     * @return
     */
    public String registerConsumerUser(String nickName, String id){
        String msj = "El usuario se a añadido con exito";
        if(searchNickNamePos(nickName) != -1){
            return msj = "El NickName ya existe.";
        }
        ConsumerUser user = new StandardUser(nickName, id);
        consumersUsers.add(user);
        return msj;
    }

    /**
     * @param name
     * @param imageUrl
     * @param producerType
     * @return
     */
    public String registerProducerUser(String name, String imageUrl, int producerType){
        String msj = "El usuario se a añadido con exito.";
        if(producerType != 1 && producerType != 2){
            return msj = "Tipo de productor incorrecto.";
        }
        if(searchNamePos(name) != -1){
            return msj = "Un usuario con ese nombre ya existe.";
        }
        ProducerUser user;
        if(producerType == 1){
            user = new ArtistUser(name, imageUrl);
        } else {
            user = new ContentCreatorUser(name, imageUrl);
        }
        producersUsers.add(user);
        return msj;
    }

    /**
     * @param nickName
     * @return
     */
    public String upgradeUser(String nickName){
        String msj = "";
        int userPos = searchNickNamePos(nickName);
        if(userPos == -1){
            return msj = "No existe usuario con ese NickName";
        }
        if(consumersUsers.get(userPos) instanceof PremiumUser){
            return msj = "El usuario ya es premium";
        }
        String id = consumersUsers.get(userPos).getId();
        ArrayList<Playlist> playlists = consumersUsers.get(userPos).getPlaylists();
        ConsumerUser user = new PremiumUser(nickName, id);
        consumersUsers.set(userPos, user);
        consumersUsers.get(userPos).setPlaylists(playlists);
        msj = "El usuario se a cambiado a premium exitosamente";
        return msj;
    }

    /**
     * @param name
     * @param album
     * @param genre
     * @param url
     * @param duration
     * @param cost
     * @return
     */
    public String addSong(String name, String album, int genre, String url, String duration, double cost){
        String msj = "Cancion añadida exitosamente";
        int durationInSeconds = 0; //TODO - calculate duration in seconds
        Audio song = new Song(name, url, duration, durationInSeconds, album, genre, cost);
        audios.add(song);
        return msj;
    }

    /**
     * @param name
     * @param description
     * @param category
     * @param url
     * @param duration
     * @return
     */
    public String addPodcast(String name, String description, int category, String url, String duration){
        String msj = "Podcast añadido exitosamente";
        int durationInSeconds = 0; //TODO - calculate duration in seconds
        Audio podcast = new Podcast(name, url, duration, durationInSeconds, description, category);
        audios.add(podcast);
        return msj;
    }

    /**
     * @param nickName
     * @param playlistCode
     * @param playlistType
     * @return
     */
    public String createPlaylist(String nickName, String playListCode, int playlistType){
        String msj = " ";
        int userPos = searchNickNamePos(nickName);
        if(userPos == -1){
            return msj = "El nickname no existe";
        }
        msj = consumersUsers.get(userPos).addPlaylist(playListCode, playlistType);
        return msj;
    }

    /**
     * @param nickName
     * @param playListCode
     * @param audioName
     * @return
     */
    public String addAudioToPlaylist(String nickName, String playListCode, String audioName){
        String msj = "Cancion añadida exitosamente";
        int userPos = searchNickNamePos(nickName);
        if(userPos == -1){
            return msj = "Un usuario con ese NickName no existe";
        }
        int playlistPos = consumersUsers.get(userPos).searchPlaylistPos(playListCode);
        if(playlistPos == -1){
            return msj = "Una playlist con ese nombre no existe";
        }
        int audioPos = searchAudioPos(audioName);
        if(audioPos == -1){
            return msj = "Un audio con ese nombre no existe";
        }
        msj = consumersUsers.get(userPos).addAudioToPlaylist(audios.get(audioPos), playlistPos);
        return msj;
    }

    /**
     * @param nickName
     * @param playListCode
     * @param audioName
     * @return
     */
    public String removeAudioOfPlaylist(String nickName, String playListCode, String audioName){
        String msj = "Audio removido con exito";
        int userPos = searchNickNamePos(nickName);
        if(userPos == -1){
            return msj = "Un usuario con ese NickName no existe";
        }
        int playlistPos = consumersUsers.get(userPos).searchPlaylistPos(playListCode);
        if(playlistPos == -1){
            return msj = "Una playlist con ese nombre no existe";
        }
        int audioPos = consumersUsers.get(userPos).searchAudioPos(audioName, playlistPos);
        if(audioPos == -1){
            return msj = "Un audio con ese nombre no existe";
        }
        consumersUsers.get(userPos).removeAudioFromPlaylist(audioPos, playlistPos);
        return msj;
    }

    public String createPlaylistWithAnotherPlaylist(String nickNameOwner, String nickName, String code){
        String msj = "";
        int ownerPos = searchNickNamePos(nickNameOwner);
        int userPos = searchNickNamePos(nickName);
        if(ownerPos == -1 | userPos == -1){
            return msj = "Alguno de los nicknames no existe.";
        }
        int playlistPos = consumersUsers.get(ownerPos).searchPlaylistPos(code);
        if(playlistPos == -1){
            return msj = "Una playlist con ese codigo no existe.";
        }
        Playlist playlist = consumersUsers.get(ownerPos).getPlaylist(playlistPos);
        msj = consumersUsers.get(userPos).addPlaylistWithPlaylist(playlist);
        return msj;
    }

    /**
     * @param nickName
     * @return
     */
    public int searchNickNamePos(String nickName){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < consumersUsers.size() && !found; i++){
            if(consumersUsers.get(i).getNickName().equals(nickName)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    /**
     * @param name
     * @return
     */
    public int searchNamePos(String name){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < producersUsers.size() && !found; i++){
            if(producersUsers.get(i).getName().equals(name)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    /**
     * @param name
     * @return
     */
    public int searchAudioPos(String name){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < audios.size() && !found; i++){
            if(audios.get(i).getName().equals(name)){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

}