package model;
import java.util.ArrayList;
import java.util.Random;

public class NeoTunes {

    private ArrayList<ConsumerUser> consumersUsers;
    private ArrayList<ProducerUser> producersUsers;
    private ArrayList<Audio> audios;
    private String[] ads;
    private Random random;

    public NeoTunes(){
        consumersUsers = new ArrayList<ConsumerUser>();
        producersUsers = new ArrayList<ProducerUser>();
        random = new Random();
        audios = new ArrayList<Audio>();
        ads = new String[3];
        ads[0] = "Nike - Just Do It";
        ads[1] = "Coca-Cola - Open Happiness";
        ads[2] = "M&Ms - Melts in Your Mouth, Not in Your Hands";
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
    public String addSong(String name, String artistName, String album, int genre, String url, String duration, double cost){
        String msj = "Cancion añadida exitosamente";
        Audio song = new Song(name, artistName, url, duration, album, genre, cost);
        int artistPos = searchNamePos(artistName);
        if(artistPos == -1){
            return msj = "Ese artista no existe";
        }
        if(!(producersUsers.get(artistPos) instanceof ArtistUser)){
            return msj = "Las canciones solo pueden ser creadas por usuarios artistas.";
        }
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
    public String addPodcast(String name, String creatorName ,String description, int category, String url, String duration){
        String msj = "Podcast añadido exitosamente";
        Audio podcast = new Podcast(name, creatorName, url, duration, description, category);
        int creatorPos = searchNamePos(creatorName);
        if(creatorPos == -1){
            return msj = "Ese artista no existe";
        }
        if(!(producersUsers.get(creatorPos) instanceof ContentCreatorUser)){
            return msj = "Los podcasts solo pueden ser creados por usuarios creadores de contenido.";
        }
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

    /**
     * @param nickNameOwner
     * @param nickName
     * @param code
     * @return
     */
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
     * @param nickname
     * @param audioName
     * @return
     */
    public String playAudio(String nickname, String audioName){
        String msj = "";
        int userPos = searchNickNamePos(nickname);
        int audioPos = searchAudioPos(audioName);
        if(userPos == -1){
            return msj = "Ese usuario no existe";
        }
        if(audioPos == -1){
            return msj = "Ese audio no existe";
        }
        if(consumersUsers.get(userPos) instanceof StandardUser){
            if(audios.get(audioPos) instanceof Podcast){
                msj = ads[random.nextInt(3)] + "\n";
                msj += ((StandardUser) consumersUsers.get(userPos)).playAudio(audios.get(audioPos));
            } else if(((StandardUser) consumersUsers.get(userPos)).getCurrentReproduction() >= 2){
                msj = ads[random.nextInt(3)] + "\n";
                msj += ((StandardUser) consumersUsers.get(userPos)).playAudio(audios.get(audioPos));
            } else {
                msj = ((StandardUser) consumersUsers.get(userPos)).playAudio(audios.get(audioPos));
            }
        } else {
            msj = ((PremiumUser) consumersUsers.get(userPos)).playAudio(audios.get(audioPos));
        }
        audios.get(audioPos).addAmmountOfPlays();
        Audio audio = audios.get(audioPos);
        String creator = audio.getCreator();
        producersUsers.get(searchNamePos(creator)).addAmmountOfPlays();
        if(audios.get(audioPos) instanceof Song){
            consumersUsers.get(userPos).addGenrePlay(((Song) audio).getGenre());
        } else {
            consumersUsers.get(userPos).addCategoryPlay(((Podcast) audio).getCategory());
        }
        return msj;
    }

    /**
     * @param songName
     * @param nickname
     * @return
     */
    public String buySong(String songName, String nickname){
        String msj = "";
        int userPos = searchNickNamePos(nickname);
        int songPos = searchAudioPos(songName);
        if(userPos == -1){
            return msj = "Ese usuario no existe";
        }
        if(songPos == -1){
            return msj = "Esa cancion no existe";
        }
        if(audios.get(songPos) instanceof Podcast){
            return msj = "Solo se pueden comprar canciones, no podcasts";
        }
        msj = consumersUsers.get(userPos).buySong();
        if(consumersUsers.get(userPos) instanceof StandardUser){
            if(((StandardUser)consumersUsers.get(userPos)).getAmmountOfBuys() >= 100){
                return msj;
            }
        }
        ((Song)audios.get(songPos)).addAmmountOfSells();
        int creatorPos = searchNamePos(audios.get(songPos).getCreator());
        ((ArtistUser)producersUsers.get(creatorPos)).addAmmountOfSells(((Song)audios.get(songPos)).getCost());
        return msj;
    }

    /**
     * @return
     */
    public String totalPlays(){
        String msj = "En total se tienen ";
        int totalPlays = 0;
        for(int i = 0; i < audios.size(); i++){
            totalPlays += audios.get(i).getAmmountOfPlays();
        }
        msj += totalPlays + " reproducciones.";
        return msj;
    }

    /**
     * @param nickname
     * @return
     */
    public String mostPlayedGenreForAnUser(String nickname){
        String msj = "";
        int userPos = searchNickNamePos(nickname);
        if(userPos == -1){
            return msj = "Ese usuario no existe";
        }
        msj = consumersUsers.get(userPos).getMostPlayedGenre();
        return msj;
    }

    /**
     * @param nickname
     * @return
     */
    public String mostPlayedCategoryForAnUser(String nickname){
        String msj = "";
        int userPos = searchNickNamePos(nickname);
        if(userPos == -1){
            return msj = "Ese usuario no existe";
        }
        msj = consumersUsers.get(userPos).getMostPlayedCategory();
        return msj;
    }

    /**
     * @return
     */
    public String mostPlayedGenre(){
        String msj = "";
        int[] plays = new int[4]; 
        for(int i = 0; i < audios.size(); i++){
            if(audios.get(i) instanceof Song){
                switch(((Song) audios.get(i)).getGenre()){
                    case ROCK -> plays[0]++;
                    case POP -> plays[1]++;
                    case TRAP -> plays[2]++;
                    case HOUSE -> plays[3]++;
                }
            }
        }
        int mostPlays = 0;
        int mostPlayedGenre = -1;
        for(int i = 0; i < 4; i++){
            if(plays[i] > mostPlays){
                mostPlayedGenre = i;
                mostPlays = plays[i];
            }
        }
        if(mostPlays == 0){
            return msj = "No hay genero mas escuchado.";
        }
        String genre = "";
        switch(mostPlayedGenre){
            case 0 -> genre = "rock";
            case 1 -> genre = "pop";
            case 2 -> genre = "trap";
            case 3 -> genre = "house";
        }
        msj = "El genero mas escuchado es " + genre + ", con un total de " + mostPlays +" reproducciones.";
        return msj;
    }

    /**
     * @return
     */
    public String mostPlayedCategory(){
        String msj = "";
        int[] plays = new int[4];
        for(int i = 0; i < audios.size(); i++){
            if(audios.get(i) instanceof Podcast){
                switch(((Podcast)audios.get(i)).getCategory()){
                    case POLITICS -> plays[0]++;
                    case ENTERTAINMENT -> plays[1]++;
                    case VIDEOGAMES -> plays[2]++;
                    case FASHION -> plays[3]++;
                }
            }
        }
        int mostPlays = 0;
        int mostPlayedCategory = -1;
        for(int i = 0; i < 4; i++){
            if(plays[i] > mostPlays){
                mostPlayedCategory = i;
                mostPlays = plays[i];
            }
        }
        if(mostPlays == 0){
            return msj = "No hay categoria mas escuchada.";
        }
        String category = "";
        switch(mostPlayedCategory){
            case 0 -> category = "politica";
            case 1 -> category = "entretenimiento";
            case 2 -> category = "videojuegos";
            case 3 -> category = "moda";
        }
        msj = "La categoria mas escuchada es " + category + ", con un total de " + mostPlays +" reproducciones.";
        return msj;
    }

    /**
     * @return
     */
    public String top5ArtistAndCreators(){
        String msj = "Top 5 artistas:\n";
        msj += top5Artist() + "\n" + "Top 5 creadores de contenido:\n";
        msj += top5Creators();
        return msj;
    }

    /**
     * @return
     */
    public String top5Artist(){
        String msj = "Top | Name | Plays\n";
		String[] names = new String[5];
		double[] plays = new double[5];
		int producerPlays;
		String name;
		int position;
		for(int i = 0; i < 5; i++){
			names[i] = "-----";
		}
		for(int i = 0; i < producersUsers.size(); i++){
            if(producersUsers.get(i) instanceof ArtistUser){
                producerPlays = producersUsers.get(i).getAmmountOfPlays();
				name = producersUsers.get(i).getName();
				position = -1;
				for(int j = 0; j < 5; j++){
					if(producerPlays > plays[j]){
						position = j;
					}
				}
				for(int j = 1; j <= position; j++){
					plays[j-1] = plays[j];
					names[j-1] = names[j];
				}
				if(position != -1){
					plays[position] = producerPlays;
					names[position] = name;
				}
            }
		}
		int top = 1;
		for(int i = 4; i>=0; i--){
			msj += top + " | " + names[i]+" | " + plays[i] + "\n";
			top++;
		}
        return msj;
    }

    /**
     * @return
     */
    public String top5Creators(){
        String msj = "Top | Name | Plays\n";
		String[] names = new String[5];
		double[] plays = new double[5];
		int producerPlays;
		String name;
		int position;
		for(int i = 0; i < 5; i++){
			names[i] = "-----";
		}
		for(int i = 0; i < producersUsers.size(); i++){
            if(producersUsers.get(i) instanceof ContentCreatorUser){
                producerPlays = producersUsers.get(i).getAmmountOfPlays();
				name = producersUsers.get(i).getName();
				position = -1;
				for(int j = 0; j < 5; j++){
					if(producerPlays > plays[j]){
						position = j;
					}
				}
				for(int j = 1; j <= position; j++){
					plays[j-1] = plays[j];
					names[j-1] = names[j];
				}
				if(position != -1){
					plays[position] = producerPlays;
					names[position] = name;
				}
            }
		}
		int top = 1;
		for(int i = 4; i>=0; i--){
			msj += top + " | " + names[i]+" | " + plays[i] + "\n";
			top++;
		}
        return msj;
    }

    /**
     * @return
     */
    public String top10SongsAndPodcasts(){
        String msj = "Top 10 canciones:\n";
        msj += top5Artist() + "\n" + "Top 10 podcasts:\n";
        msj += top5Creators();
        return msj;
    }

    /**
     * @return
     */
    public String top10Songs(){
        String msj = "Top | Name | Plays\n";
		String[] names = new String[10];
		double[] plays = new double[10];
		int audioPlays;
		String name;
		int position;
		for(int i = 0; i < 5; i++){
			names[i] = "-----";
		}
		for(int i = 0; i < audios.size(); i++){
            if(audios.get(i) instanceof Song){
                audioPlays = audios.get(i).getAmmountOfPlays();
				name = audios.get(i).getName();
				position = -1;
				for(int j = 0; j < 5; j++){
					if(audioPlays > plays[j]){
						position = j;
					}
				}
				for(int j = 1; j <= position; j++){
					plays[j-1] = plays[j];
					names[j-1] = names[j];
				}
				if(position != -1){
					plays[position] = audioPlays;
					names[position] = name;
				}
            }
		}
		int top = 1;
		for(int i = 4; i>=0; i--){
			msj += top + " | " + names[i]+" | " + plays[i] + "\n";
			top++;
		}
        return msj;
    }

    /**
     * @return
     */
    public String top10Podcasts(){
        String msj = "Top | Name | Plays\n";
		String[] names = new String[10];
		double[] plays = new double[10];
		int audioPlays;
		String name;
		int position;
		for(int i = 0; i < 5; i++){
			names[i] = "-----";
		}
		for(int i = 0; i < audios.size(); i++){
            if(audios.get(i) instanceof Podcast){
                audioPlays = audios.get(i).getAmmountOfPlays();
				name = audios.get(i).getName();
				position = -1;
				for(int j = 0; j < 5; j++){
					if(audioPlays > plays[j]){
						position = j;
					}
				}
				for(int j = 1; j <= position; j++){
					plays[j-1] = plays[j];
					names[j-1] = names[j];
				}
				if(position != -1){
					plays[position] = audioPlays;
					names[position] = name;
				}
            }
		}
		int top = 1;
		for(int i = 4; i>=0; i--){
			msj += top + " | " + names[i]+" | " + plays[i] + "\n";
			top++;
		}
        return msj;
    }

    /**
     * @return
     */
    public String sellsOfEachGenre(){
        String msj = "";
        int[] sells = new int[4];
        int[] valueOfSells = new int[4];
        for(int i = 0; i < audios.size(); i++) {
            if(audios.get(i) instanceof Song){
                Song song = ((Song)audios.get(i));
                switch(song.getGenre()){
                    case ROCK:
                        sells[0] += song.getAmmountOfSells();
                        valueOfSells[0] += song.getValueOfTotalSells();
                    break;

                    case POP:
                        sells[1] += song.getAmmountOfSells();
                        valueOfSells[1] += song.getValueOfTotalSells();
                    break;

                    case TRAP:
                        sells[2] += song.getAmmountOfSells();
                        valueOfSells[2] += song.getValueOfTotalSells(); 
                    break;

                    case HOUSE:
                        sells[3] += song.getAmmountOfSells();
                        valueOfSells[3] += song.getValueOfTotalSells();
                    break;
                }
            }
        }
        msj += "Numero de canciones vendidas de rock: " + sells[0] + ", valor total de ventas: " + valueOfSells[0] + "\n";
        msj += "Numero de canciones vendidas de pop: " + sells[1] + ", valor total de ventas: " + valueOfSells[1] + "\n";
        msj += "Numero de canciones vendidas de trap: " + sells[2] + ", valor total de ventas: " + valueOfSells[2] + "\n";
        msj += "Numero de canciones vendidas de house: " + sells[3] + ", valor total de ventas: " + valueOfSells[3] + "\n";
        return msj;
    }
   
    /**
     * @return
     */
    public String mostSoldSong(){
        String msj = "";
        int mostSoldAmmount = 0;
        int mostSoldValue = 0;
        int mostSoldSongPos = -1;
        for(int i = 0; i < audios.size(); i++){
            if(audios.get(i) instanceof Song){
                Song song = ((Song)audios.get(i));
                if(song.getAmmountOfSells() > mostSoldAmmount){
                    mostSoldAmmount = song.getAmmountOfSells();
                    mostSoldValue = song.getValueOfTotalSells();
                    mostSoldSongPos = i;
                }
            }
        }
        if(mostSoldSongPos == -1){
            return msj = "No hay cancion con mayor cantidad de ventas";
        }
        msj = "La cancion con mas ventas es: " + audios.get(mostSoldSongPos).getName() + ", con un total de " + mostSoldAmmount + " ventas y un valor todal de " + mostSoldValue;
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