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
		System.out.println("1. Registrar un usuario\n" +
                           "2. Mejorar usuario a premium\n" +
                           "0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

    public void executeOption(int option){
		switch(option){
			case 1: 
                registerUser();
				break; 

			case 2: 
                upgradeUser();
				break; 

			case 3: 
				break; 

			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
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

    public void registerUser(){
        String nickName;
        String id;
        System.out.println("Ingrese su NickName");
        nickName = reader.nextLine();
        System.out.println("Ingrese su cedula");
        id = reader.nextLine();
        System.out.println(controller.registerUser(nickName, id));
    }

    public void upgradeUser(){
        String nickName;
        System.out.println("Ingrese su nickName");
        nickName = reader.nextLine();
        System.out.println(controller.upgradeUser(nickName));
    }

}