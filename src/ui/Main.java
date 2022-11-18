package ui;

import java.util.Scanner;
import model.NeoTunes;
import java.util.Date; 

public class Main{

    private Scanner reader;
    private NeoTunes controller;

	public Main() {
		reader = new Scanner(System.in);
        controller = new NeoTunes();
    }

    public static void main(String[] args) {
		Main main = new Main(); 
		int option = 0; 
		do{
			option = main.getOptionShowMenu(); 
			main.executeOption(option);
		}while(option != 0);
		main.getReader().close();
	}

    /**
     * @return
     */
    public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to NeoTunes >>>>>");
		System.out.println("1. Registrar un usuario consumidor\n" +
                           "2. Registrar un usuario productor\n" +
                           "3. Mejorar usuario a premium\n" +
						   "4. Añadir cancion o podcast\n" +
						   "5. Crear playlist\n" +
						   "6. Editar playlist\n" +
						   "7. Crear playlist con una playlist compartida\n" +
						   "8. Reproducir un audio\n" +
						   "9. Comprar una cancion\n" +
						   "10. Generar informes\n" +
                           "0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

    /**
     * @param option
     */
    public void executeOption(int option){
		switch (option) {
			case 1 -> registerConsumerUser();
			case 2 -> registerProducerUser();
			case 3 -> upgradeUser();
			case 4 -> askKindOfAudio();
			case 5 -> createPlaylist();
			case 6 -> editPlaylist();
			case 7 -> createPlaylistWithAnotherPlaylist();
			case 8 -> playAudio();
			case 9 -> buySong();
			case 10 -> reportSelect();
			case 0 -> System.out.println("Exit program.");
			default -> System.out.println("Invalid Option");
		}
	}

	public void reportSelect(){
		int option = 0; 
		System.out.println("1. Informar el acumulado total de reproducciones en toda la plataforma.\n" +
                           "2. Informar el genero de cancion mas escuchado para un usuario\n" +
                           "3. Informar el genero de cancion mas escuchado para la plataforma\n" +
						   "4. Informar la categoria de podcast mas escuchada para un usuario\n" +
						   "5. Informar la categoria de podcast mas escuchada para la plataforma\n" +
						   "6. Top 5 de artistas y top 5 de creadores de contenido\n" +
						   "7. Top 10 de canciones y top 10 de podcasts\n" + 
						   "8. Numero de canciones vendidas y el valor total de ventas de cada genero\n" + 
						   "9. Numero total de ventas y el valor total de venta de la cancion mas vendida");
		option =  validateIntegerInput();
		switch (option) {
			case 1 -> registerConsumerUser();
			case 2 -> registerProducerUser();
			case 3 -> upgradeUser();
			case 4 -> askKindOfAudio();
			case 5 -> createPlaylist();
			case 6 -> editPlaylist();
			case 7 -> createPlaylistWithAnotherPlaylist();
			case 8 -> playAudio();
			case 9 -> buySong();
			default -> System.out.println("Invalid Option");
		}
	}

    /**
     * @return
     */
    public int validateIntegerInput(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
			reader.nextLine();
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

    /**
     * @return
     */
    public double validateDoubleInput(){
		double option = 0; 

		if(reader.hasNextDouble()){
			option = reader.nextDouble(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

    /**
     * @return
     */
    public Scanner getReader(){
		return reader; 
	}

    public void registerConsumerUser(){
        String nickName;
        String id;
        System.out.println("Ingrese su NickName");
        nickName = reader.nextLine();
        System.out.println("Ingrese su cedula");
        id = reader.nextLine();
        System.out.println(controller.registerConsumerUser(nickName, id));
    }

    public void registerProducerUser(){
        String name;
        String imageUrl;
        int producerType;
        System.out.println("Ingrese el nombre");
        name = reader.nextLine();
        System.out.println("Ingrese un url de imagen");
        imageUrl = reader.nextLine();
        System.out.println("Ingrese el tipo de usuario productor\n" +
                           "1. Artista\n" + 
                           "2. Creador de contenido");
        producerType = validateIntegerInput();
        System.out.println(controller.registerProducerUser(name, imageUrl, producerType));
    }

    public void upgradeUser(){
        String nickName;
        System.out.println("Ingrese su nickName");
        nickName = reader.nextLine();
        System.out.println(controller.upgradeUser(nickName));
    }

	public void askKindOfAudio(){
		int audioType;
		System.out.println("Que desea añadir?\n" + 
						   "1. Cancion\n" +
						   "2. Podcast");
		audioType = validateIntegerInput();
		switch(audioType){
			case 1 -> addSong();
			case 2 -> addPodcast();
			default -> System.out.println("Opcion incorrecta");
		}
	}

	public void addSong(){
		String name;
		String artistName;
		String album;
		int genre;
		String url;
		String duration;
		double cost;
		System.out.println("Ingrese el nombre");
		name = reader.nextLine();
		System.out.println("Ingrese el nombre del artista");
		artistName = reader.nextLine();
		System.out.println("Ingrese el album");
		album = reader.nextLine();
		System.out.println("Ingrese el genero\n" +
						   "1. Rock\n" + 
						   "2. Pop\n" + 
						   "3. Trap\n" + 
						   "4. House\n");
		genre = validateIntegerInput();
		if(genre < 1 || genre > 4){
			System.out.println("Genero incorrecto");
			return;
		}
		System.out.println("Ingrese el url");
		url = reader.nextLine();
		System.out.println("Ingrese la duracion (xx:xx:xx)");
		duration = reader.nextLine();
		System.out.println("Ingrese el costo");
		cost = validateDoubleInput();
		if(cost < 0){
			System.out.println("Costo incorrecto");
			return;
		}
		System.out.println(controller.addSong(name, artistName, album, genre, url, duration, cost));
	}

	public void addPodcast(){
		String name;
		String creatorName;
		String description;
		int category;
		String url;
		String duration;
		System.out.println("Ingrese el nombre");
		name = reader.nextLine();
		System.out.println("Ingrese el nombre del creador de contenido.");
		creatorName = reader.nextLine();
		System.out.println("Ingrese la descripcion");
		description = reader.nextLine();
		System.out.println("Ingrese la categoria\n" + 
						   "1. Politica\n" + 
						   "2. Entretenimiento\n" +
						   "3. Videojuegos\n" +
						   "4. Moda\n");
		category = validateIntegerInput();
		if(category < 1 || category > 4){
			System.out.println("Categoria incorrecta");
			return;
		}
		System.out.println("Ingrese el url");
		url = reader.nextLine();
		System.out.println("Ingrese la duracion (xx:xx:xx)");
		duration = reader.nextLine();
		System.out.println(controller.addPodcast(name, creatorName, description, category, url, duration));
	}

	public void createPlaylist(){
		String nickName;
		String playListCode;
		int playlistType;
		System.out.println("Ingrese el NickName del usuario");
		nickName = reader.nextLine();
		System.out.println("Ingrese el nombre de la playlist a crear");
		playListCode = reader.nextLine();
		System.out.println("Ingrese el tipo de playlist\n" + 
						   "1. Solo canciones\n" + 
						   "2. Solo podcasts\n" + 
						   "3. Canciones y podcasts");
		playlistType = validateIntegerInput();
		if(playlistType < 1 || playlistType > 3){
			System.out.println("Tipo de playlist incorrecto");
			return;
		}
		System.out.println(controller.createPlaylist(nickName, playListCode, playlistType));
	}

	public void editPlaylist(){
		int option;
		System.out.println("Que deseas hacer?\n" + 
						   "1. Añadir audio a playlist\n" +
						   "2. Eliminar audio de playlist");
		option = validateIntegerInput();
		switch(option){
			case 1 -> addAudioToPlaylist();
			case 2 -> removeAudioOfPlaylist();
			default -> System.out.println("Opcion incorrecta");
		}
	}

	public void addAudioToPlaylist(){
		String nickName;
		String playlistCode;
		String audioName;
		System.out.println("Ingrese el NickName del usuario");
		nickName = reader.nextLine();
		System.out.println("Ingrese el codigo de la playlist");
		playlistCode = reader.nextLine();
		System.out.println("Ingrese el nombre del audio a añadir");
		audioName = reader.nextLine();
		System.out.println(controller.addAudioToPlaylist(nickName, playlistCode, audioName));
	}

	public void removeAudioOfPlaylist(){
		String nickName;
		String playlistCode;
		String audioName;
		System.out.println("Ingrese el nickName");
		nickName = reader.nextLine();
		System.out.println("Ingrese el codigo de la playlist");
		playlistCode = reader.nextLine();
		System.out.println("Ingrese el nombre de el audio");
		audioName = reader.nextLine();
		System.out.println(controller.removeAudioOfPlaylist(nickName, playlistCode, audioName));
	}

	public void createPlaylistWithAnotherPlaylist(){
		String nickNameOwner;
		String nickName;
		String playlistCode;
		System.out.println("Ingresa tu nickname");
		nickName = reader.nextLine();
		System.out.println("Ingresa el nickname de la persona que te compartio el codigo de su playlist");
		nickNameOwner = reader.nextLine();
		System.out.println("Ingresa el codigo de la playlist");
		playlistCode = reader.nextLine();
		System.out.println(controller.createPlaylistWithAnotherPlaylist(nickNameOwner, nickName, playlistCode));
	}

	public void playAudio(){
		String nickname;
		String audioName;
		System.out.println("Ingresa tu nickname");
		nickname = reader.nextLine();
		System.out.println("Ingresa el nombre del audio a reproducir");
		audioName = reader.nextLine();
		System.out.println(controller.playAudio(nickname, audioName));
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   
	}

	public void buySong(){
		String songName;
		String nickname;
		System.out.println("Ingrese el nombre de la cancion a comprar.");
		songName = reader.nextLine();
		System.out.println("Ingrese su nickname");
		nickname = reader.nextLine();
		System.out.println(controller.buySong(songName, nickname));
	}

	
}