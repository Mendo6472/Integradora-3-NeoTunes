package model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class ConsumerUser {
    
    protected String nickName;
    protected String id;
    protected Date registerDate;
    protected ArrayList<Playlist> playlists;
    protected int[] genresPlays;
    protected int[] categoriesPlays;

    /**
     * @param nickName
     * @param id
     */
    public ConsumerUser(String nickName, String id){
        this.nickName = nickName;
        this.id = id;
        this.registerDate = new Date(System.currentTimeMillis());
        this.playlists = new ArrayList<Playlist>();
        this.genresPlays = new int[4];
        this.categoriesPlays = new int[4];
    }

    public void addGenrePlay(Genre genre){
        switch(genre){
            case ROCK -> genresPlays[0]++;
            case POP -> genresPlays[1]++;
            case TRAP -> genresPlays[2]++;
            case HOUSE -> genresPlays[3]++;
        }
    }

    public void addCategoryPlay(Category category){
        switch (category){
            case POLITICS -> categoriesPlays[0]++;
            case ENTERTAINMENT -> categoriesPlays[1]++;
            case VIDEOGAMES -> categoriesPlays[2]++;
            case FASHION -> categoriesPlays[3]++;
        }
    }

    public String getMostPlayedGenre(){
        String msj = "";
        int mostPlays = 0;
        int mostPlayedGenre = -1;
        for(int i = 0; i < 4; i++){
            if(genresPlays[i] > mostPlays){
                mostPlayedGenre = i;
                mostPlays = genresPlays[i];
            }
        }
        if(mostPlays == 0){
            return msj = "No hay genero mas escuchado para este usuario.";
        }
        String genre = "";
        switch(mostPlayedGenre){
            case 0 -> genre = "rock";
            case 1 -> genre = "pop";
            case 2 -> genre = "trap";
            case 3 -> genre = "house";
        }
        msj = "El genero mas escuchado de este usuario es " + genre + ", con un total de " + mostPlays +" reproducciones.";
        return msj;
    }

    public String getMostPlayedCategory(){
        String msj = "";
        int mostPlays = 0;
        int mostPlayedCategory = -1;
        for(int i = 0; i < 4; i++){
            if(categoriesPlays[i] > mostPlays){
                mostPlayedCategory = i;
                mostPlays = categoriesPlays[i];
            }
        }
        if(mostPlays == 0){
            return msj = "No hay categoria mas escuchada para este usuario.";
        }
        String category = "";
        switch(mostPlayedCategory){
            case 0 -> category = "politica";
            case 1 -> category = "entretenimiento";
            case 2 -> category = "videojuegos";
            case 3 -> category = "moda";
        }
        msj = "La categoria mas escuchada de este usuario es " + category + ", con un total de " + mostPlays +" reproducciones.";
        return msj;
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
    public abstract String buySong();

    /**
     * @param name
     * @param playlistType
     * @return
     */
    public abstract String addPlaylist(String name, int playlistType);

    public abstract String addPlaylistWithPlaylist(Playlist playlist);
    
}
