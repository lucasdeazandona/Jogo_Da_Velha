package Jogo_Velha;

import java.io.IOException;
import java.util.Scanner;

public class Main_Velha {
	
	static boolean jogando;
	static boolean fimJogo = false;
	static String jogador1;
	static String jogador2;
	static Scanner scan = new Scanner(System.in);
	static String[][] velhaMatriz = new String[3][3];
	static int numJogadas;

	public static void main(String[] args) throws IOException{
		
		jogando = true;
		
		do {
			numJogadas=0;
			fimJogo = false;
			System.out.printf("Informe o nome do primeiro Jogador: ");
			jogador1= scan.next();
			System.out.printf("Informe o nome do segundo Jogador: ");
			jogador2= scan.next();
			
			for(int l=0; l<3; l++) {
				for(int c=0; c<3; c++) {
					velhaMatriz[l][c]="-";
				}
			}
			
			jogada();
			imprimeTabu();
			jogando();
			
		}while(jogando);
	}
	static void imprimeTabu() {
			System.out.println("\n   1   2   3");
			System.out.println("1  " + velhaMatriz[0][0] + " | " + velhaMatriz[0][1] + " | " + velhaMatriz[0][2]);
			System.out.println("  -----------");
			System.out.println("2  " + velhaMatriz[1][0] + " | " + velhaMatriz[1][1] + " | " + velhaMatriz[1][2]);
			System.out.println("  -----------");
			System.out.println("3  " + velhaMatriz[2][0] + " | " + velhaMatriz[2][1] + " | " + velhaMatriz[2][2]);
		
	}
	
	static void jogada() {
			int jogador = 1;
			boolean correto = true;
			boolean vazio = true;
			
			do {
				if(jogador == 1) {
					int l, c;
					do {
						
						System.out.println("Jogador da vez: " + jogador1);
						imprimeTabu();
						System.out.println("Informe a Linha e depois a Coluna");
						do {
							l = scan.nextInt() - 1;
							if(l < 0 ||l>2) {
								correto = false;
								System.out.println("Informe um valor Para linha de 1 a 3");
							}else {
								correto = true;
							}
						}while(!correto);
						do {
							c = scan.nextInt() - 1;
							if(c < 0 ||c>2) {
								correto = false;
								System.out.println("Informe um valor Para Coluna de 1 a 3");
							}else {
								correto = true;
							}
						}while(!correto);
						vazio = testevazio(l, c);
						if(!vazio) {
							System.out.println("Casa em uso");
					}
					
				}while(!vazio);
				velhaMatriz[l][c] ="X";
				numJogadas++;
				validandoLinhaseColunas();
				validandoDiagonais();
				empate();
				jogador = 2;
			}
			if(jogador == 2 && !fimJogo) {
				int l, c;
				do {
					System.out.println("Jogador da vez: " + jogador2);
					imprimeTabu();
					System.out.println("Informe a Linha e depois a Coluna");
					
					do {
						l = scan.nextInt() - 1;
						if(l < 0 || l > 2) {
							correto = false;
							System.out.println("Informe um valor Para linha de 1 a 3");
						}else {
							correto = true;
						}
					}while(!correto);
					do {
						c = scan.nextInt() - 1;
						if(c < 0 ||c>2) {
							correto = false;
							System.out.println("Informe um valor Para Coluna de 1 a 3");
						}else {
							correto = true;
						}
					}while(!correto);
					vazio = testevazio(l, c);
					if(!vazio) {
						System.out.println("Casa em uso");
					}
				}while(!vazio);
				velhaMatriz[l][c] ="O";
				numJogadas++;
				validandoLinhaseColunas();
				validandoDiagonais();
				empate();
				jogador = 1;
			}
		}while(!fimJogo);
	}
	
	static boolean testevazio(int l, int c) {
		if(velhaMatriz[l][c] != "-") {
			return false;
		}else {
			return true;
		}
	}
	
	static void validandoDiagonais() {
		if(velhaMatriz[0][0] == "X" && velhaMatriz[1][1] == "X" && velhaMatriz[2][2] == "X") {
			fimJogo = true;
			System.out.println("O vencedor é: "+jogador1);
		}
		if(velhaMatriz[0][0] == "O" && velhaMatriz[1][1] == "O" && velhaMatriz[2][2] == "O") {
			fimJogo = true;
			System.out.println("O vencedor é: "+jogador2);
		}
		if(velhaMatriz[0][2] == "X" && velhaMatriz[1][1] == "X" && velhaMatriz[2][0] == "X") {
			fimJogo = true;
			System.out.println("O vencedor é: "+jogador1);
		}
		if(velhaMatriz[0][2] == "O" && velhaMatriz[1][1] == "O" && velhaMatriz[2][0] == "O") {
			fimJogo = true;
			System.out.println("O vencedor é: "+jogador2);
		}
	}
	
	static void validandoLinhaseColunas() {
		for(int l=0; l<3; l++) {
			if(velhaMatriz[l][0] == "X" && velhaMatriz[l][1] == "X" && velhaMatriz[l][2] == "X"){
				fimJogo = true;
				System.out.println("O vencedor é: "+jogador1);
			}
		}
		for(int l=0; l<3; l++) {
			if(velhaMatriz[l][0] == "O" && velhaMatriz[l][1] == "O" && velhaMatriz[l][2] == "O"){
				fimJogo = true;
				System.out.println("O vencedor é: "+jogador2);
			}
		}
		for(int c=0; c<3; c++) {
			if(velhaMatriz[0][c] == "X" && velhaMatriz[1][c] == "X" && velhaMatriz[2][c] == "X"){
				fimJogo = true;
				System.out.println("O vencedor é: "+jogador1);
			}
		}
		for(int c=0; c<3; c++) {
			if(velhaMatriz[0][c] == "O" && velhaMatriz[1][c] == "O" && velhaMatriz[2][c] == "O"){
				fimJogo = true;
				System.out.println("O vencedor é: "+jogador2);
			}
		}
	}
	
	static void empate() {
		if(numJogadas == 9 && !fimJogo) {
			fimJogo = true;
			System.out.println("O resultado do jogo deu empate");
		}
	}
	
	static void jogando() {
		int resp;
		do {
			System.out.println("Quer iniciar um novo jogo?");
			System.out.println("1: Sim");
			System.out.println("2: Não");
			resp = scan.nextInt();
			if(resp == 1) {
				jogando = true;
			}else {
				jogando = false;
			}
		}while(resp != 1 && resp != 2);
		
	}
}

