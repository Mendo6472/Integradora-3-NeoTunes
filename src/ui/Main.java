package ui;

import java.util.Scanner;

import model.NeoTunes;

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

    public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to NeoTunes >>>>>");
		System.out.println("1. Registrar un usuario consumidor\n" +
                           "2. Registrar un usuario productor\n" +
                           "3. Mejorar usuario a premium\n" +
						   "4. Añadir cancion o podcast\n" +
                           "0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

    public void executeOption(int option){
		switch (option) {
			case 1 -> registerConsumerUser();
			case 2 -> registerProducerUser();
			case 3 -> upgradeUser();
			case 4 -> askKindOfAudio();
			case 0 -> System.out.println("Exit program.");
			default -> System.out.println("Invalid Option");
		}
	}

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
		String album;
		int genre;
		String url;
		String duration;
		double cost;
		System.out.println("Ingrese el nombre");
		name = reader.nextLine();
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
		System.out.println(controller.addSong(name, album, genre, url, duration, cost));
	}

	public void addPodcast(){
		String name;
		String description;
		int category;
		String url;
		String duration;
		System.out.println("Ingrese el nombre");
		name = reader.nextLine();
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
		System.out.println(controller.addPodcast(name, description, category, url, duration));
	}

}