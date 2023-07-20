package com.morpion;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		play();
	}
	
	public static int[][] createPlateau(int nbRows, int nbColumns) {
		int[][] tab = new int[nbRows][nbColumns];
		return tab;
	}

	public static void displayPlateau(int[][] plateau) {
		System.out.println();
		System.out.print("  ");
		
		for(int cptColumn=0; cptColumn < 3; cptColumn++) {
			
			System.out.print("  " + cptColumn + "  ");
		}
		
		System.out.println();
		
		for(int cptRow = 0; cptRow < 3; cptRow++) {
			System.out.print(cptRow + "   ");
			
			for(int cptColumn = 0; cptColumn < plateau[cptRow].length; cptColumn++) {
				
				switch(plateau[cptRow][cptColumn]) {
				case 0:
					System.out.print("-    ");
					break;
					
				case 1:
					System.out.print("X    ");
					break;
					
				case 2:
					System.out.print("O    ");
					break;
					
				default:
					break;
					
				
				}
			}
			System.out.println();
		}
	}
	
	public static boolean isPlateauFull(int[][] plateau) {
		for(int cptRow = 0; cptRow < 3; cptRow++) {
			System.out.print(cptRow + "   ");
			
			for(int cptColumn = 0; cptColumn < plateau[cptRow].length; cptColumn++) {
				
				if(plateau[cptRow][cptColumn] == 0) {
					return false;
				} 
			}
		} return true;
	}
	
	public static boolean checkPlateau(int[][] plateau, int numRow, int numColumn) {
		boolean check = false;
		
		if(numRow >= 0 && numRow < plateau.length && numColumn >= 0 && numColumn < plateau.length) {
			if(plateau[numRow][numColumn] == 0) {
				check = true;
			}
		}
		return check;
	}
	
	public static void fillCase(int[][] plateau, int numRow, int numColumn, int numPlayer) {
		plateau[numRow][numColumn] = numPlayer;
	}
	
	public static boolean equals(int entier1, int entier2, int entier3, int entier4) {
		boolean compare = false;
		
		if(entier1 == entier2 && entier2 == entier3 && entier3 == entier4) {
			compare = true;
		}
		return compare;
	}
	
	public static boolean win(int[][] plateau, int numPlayer) {
		//Row
		for(int cptRow = 0; cptRow < plateau.length; cptRow++) {
			if(equals(plateau[cptRow][0], plateau[cptRow][1], plateau[cptRow][2], numPlayer)) {
				return true;
			}
		}
		
		//Column
		for(int cptColumn = 0; cptColumn < plateau.length; cptColumn++) {
			if(equals(plateau[0][cptColumn], plateau[1][cptColumn], plateau[2][cptColumn], numPlayer)) {
				return true;
			}
		}
		
		//diagonal
		if(equals(plateau[0][0], plateau[1][1], plateau[2][2], numPlayer) || equals(plateau[2][0], plateau[1][1], plateau[0][2], numPlayer)) {
			return true;
		}
		
		return false;
		
	}
	
	public static void play() {
		
		
		Scanner scan = new Scanner(System.in);
		
		int[][] plateau = createPlateau(3, 3);
		
		int currentPlayer = 1;
		
		boolean playing = true;
		
		while (playing) {
			
			System.out.println("\n\n****************   Joueur " + currentPlayer + " c'est a toi de jouer    *********************\n ");
			
			int numLigne = -1;
			int numColonne = -1;
			boolean caseValide = false;
			
			while(!caseValide) {
				displayPlateau(plateau);
				
				System.out.println();
				System.out.println(" le Joueur " + currentPlayer + " choisit une ligne: ");
				numLigne = scan.nextInt();
				
				System.out.println();
				System.out.println(" le Joueur " + currentPlayer + " choisit une colonne: ");
				numColonne = scan.nextInt();
				
				caseValide = checkPlateau(plateau, numLigne, numColonne);
				
				if(!caseValide) {
					System.out.println("Case Invalide");
					System.out.println();
				}
			}
			
			fillCase(plateau, numLigne, numColonne, currentPlayer);
			
			if(win(plateau, currentPlayer)) {
				
				displayPlateau(plateau);
				System.out.println("Le joueur " + currentPlayer + " a gagne , bravo tu es un grand maitre !!");
				playing = false;
				
			} else if(isPlateauFull(plateau)) {
				
				displayPlateau(plateau);
				System.out.println("Match nul!!! tant pis");
				playing = false;
					
			} else {
				if(currentPlayer == 1) {
					currentPlayer = 2;
				} else {
					currentPlayer = 1;
				}
			}
		}
	}
}
