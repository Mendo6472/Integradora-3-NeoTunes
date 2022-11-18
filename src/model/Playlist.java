package model;
import java.util.ArrayList;
import java.util.Random;

public class Playlist {

    private ArrayList<Audio> playlist;
    private String name;
    private PlaylistType playlistType;
    private String code;
    private Random random;

    /**
     * @param name
     * @param playlistType
     */
    public Playlist(String name, int playlistType){
        playlist = new ArrayList<Audio>();
        this.name = name;
        this.random = new Random();
        switch(playlistType){
            case 1 -> this.playlistType = PlaylistType.SONGS;
            case 2 -> this.playlistType = PlaylistType.PODCASTS;
            case 3 -> this.playlistType = PlaylistType.SONGS_AND_PODCASTS;
        }
        this.code = generateCode(initializeMatrix());
    }

    private String[][] initializeMatrix(){
        String[][] matrix = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                matrix[i][j] = String.valueOf(random.nextInt(9));
            }
        }
        return matrix;
    }

    private String generateCode(String[][] matrix){
        String code = "";
        switch(this.playlistType){
            case SONGS:
                for(int i = 5; i >= 0; i--){
                    code += matrix[0][i];
                }
                for(int i = 1;i<5;i++){
                    code += matrix[i][i];
                }
                for(int i = 5; i >= 0; i--){
                    code += matrix[5][i];
                }
            break;

            case PODCASTS:
                for(int i = 0; i < 3; i++){
                    code += matrix[i][0];
                }
                for(int i = 1; i < 6; i++){
                    code += matrix[2][i];
                }
                for(int i = 5; i > 0; i--){
                    code += matrix[3][i];
                }
                for(int i = 3; i < 6; i++){
                    code += matrix[i][0];
                }
                System.out.println(code);
            break;

            case SONGS_AND_PODCASTS:
                for(int i = 5; i >=0; i--){
                    for(int j = 5; j >= 0; j--){
                        if(((i+j)%2) != 0){
                            if(i+j > 1){
                                code += matrix[i][j];
                            }
                        }
                    }
                }
            break;
        }
        return code;
    }

    public void generateCodeAgain(){
        String[][] matrix = initializeMatrix();
        this.code = generateCode(matrix);
    }
    /**
     * @return
     */
    public ArrayList<Audio> getPlaylist() {
        return playlist;
    }

    /**
     * @return
     */
    public String getName() {
        
        return name;
    }

    public String getCode() {
        return code;
    }

    /**
     * @param audio
     * @return
     */
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

    /**
     * @param audio
     * @return
     */
    public String addSongToPlaylist(Audio audio){
        String msj = "Audio añadido con exito";
        if(!(audio instanceof Song)){
            return msj = "La playlist solo acepta canciones.";
        }
        playlist.add(audio);
        return msj;
    }

    /**
     * @param audio
     * @return
     */
    public String addPodcastToPlaylist(Audio audio){
        String msj = "Audio añadido con exito";
        if(!(audio instanceof Podcast)){
            return msj = "La playlist solo acepta podcasts.";
        }
        playlist.add(audio);
        return msj;
    }

    /**
     * @param name
     * @return
     */
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

    /**
     * @param audioPos
     */
    public void removeAudioFromPlaylist(int audioPos){
        playlist.remove(audioPos);
    }

}
