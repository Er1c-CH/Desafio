package entities;

import java.util.Scanner;

public class JogoDaVelha {
	
	private String[][] tabuleiro;
	private int turno;
	
	public JogoDaVelha() {
		tabuleiro = new String[3][3];
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				tabuleiro[x][y] = "■";
			}
		}
		turno = 1;
	}

	public String iniciar() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Jogo da Velha");
		imprimirTabuleiro();

		while(turno < 10) {
			int[] par = getCoordenadas(sc);
			tabuleiro[par[0]][par[1]] = (turno % 2 != 0 ? "X" : "O");
			imprimirTabuleiro();
			if(hasVencedor())
				return "Jogador " + (turno % 2 != 0 ? "1" : "2") + " ganhou!";
			turno++;
		}
		return "Empate!";
		
	}
	
	private int[] getCoordenadas(Scanner sc) {
	
		int[] par = new int[2];
		
		do {
			try {
				System.out.println("Turno do jogador " + (turno % 2 != 0 ? "1" : "2"));
				System.out.print("X = ");
				par[0] = Integer.parseInt(sc.nextLine());
				System.out.print("Y = ");
				par[1]  = Integer.parseInt(sc.nextLine());
				System.out.println("");
				if(tabuleiro[par[0]][par[1]] != "■")
					throw new Exception();
			} catch(Exception e) {
				System.out.println("Par ordenado inválido.");
				System.out.println();
				imprimirTabuleiro();
				return getCoordenadas(sc);
			}
		} while(tabuleiro[par[0]][par[1]] != "■");
			
		return par;
	}
	
	private boolean hasVencedor() {
		return hasColunasUmVencedor() || hasDiagonaisUmVencedor() || hasTuplasUmVencedor();
	}
	
	private boolean hasColunasUmVencedor() {
		for(int i = 0; i < 3; i++) {
			if(tabuleiro[0][i].equals(tabuleiro[1][i]) &&
					tabuleiro[1][i].equals(tabuleiro[2][i]) &&
					!(tabuleiro[0][i].equals("■"))) return true;
		}
		return false;
	}
	
	private boolean hasTuplasUmVencedor() {
		for(int i = 0; i < 3; i++) {
			if(tabuleiro[i][0].equals(tabuleiro[i][1]) &&
					tabuleiro[i][1].equals(tabuleiro[i][2]) &&
					!(tabuleiro[i][0].equals("■"))) return true;
		}
		return false;
	}
	
	private boolean hasDiagonaisUmVencedor() {
		boolean primeiraDiagonal = tabuleiro[0][0].equals(tabuleiro[1][1]) &&
				tabuleiro[1][1].equals(tabuleiro[2][2]) &&
				!(tabuleiro[0][0].equals("■"));
		boolean segundaDiagonal = tabuleiro[0][2].equals(tabuleiro[1][1]) &&
				tabuleiro[1][1].equals(tabuleiro[2][0]) &&
				!(tabuleiro[0][2].equals("■"));
		return primeiraDiagonal || segundaDiagonal;
	}
	
	private void imprimirTabuleiro() {
		String t = "";
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				t += tabuleiro[x][y] + " ";
			}
			t += "\n";
		}
		System.out.println(t);
	}

}
