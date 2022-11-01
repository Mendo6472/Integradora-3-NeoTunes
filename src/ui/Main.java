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
		System.out.println("0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

    public void executeOption(int option){
		switch(option){
			case 1: 
				break; 

			case 2: 
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

}