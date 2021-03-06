package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.Jogada;
import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.XadrezException;

public class Application {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Partida partida = new Partida();
		List<PecaXadrez> capturadas = new ArrayList<>();

		while (!partida.getXequeMate()) {
			try {
				UI.limpaTela();
				UI.imprimePartida(partida, capturadas);
				System.out.println();
				System.out.print("Peça: ");
				Jogada origem = UI.lerJogada(input);

				boolean [][] movPossiveis = partida.movPossiveis(origem);
				UI.limpaTela();
				UI.imprimeTab(partida.getPecas(), movPossiveis);

				System.out.println();
				System.out.print("Jogada: ");
				Jogada jogada = UI.lerJogada(input);
				PecaXadrez capturada = partida.moverPeca(origem, jogada);
				if(capturada != null) {
					capturadas.add(capturada);
				}
				if (partida.getPromovido() != null) {
					System.out.println("Pra qual peça vc quer promover (b,c,t,r)?");
					String tipo = input.nextLine().toLowerCase();
					while (!tipo.equals("b") && !tipo.equals("c") && !tipo.equals("t") && !tipo.equals("r")) {
						System.out.println("Valor inválido!! Pra qual peça vc quer promover (b,c,t,r)?");
						tipo = input.nextLine().toLowerCase();
					}
					partida.trocarPromovida(tipo);
				}
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			}
		}
		UI.limpaTela();
		UI.imprimePartida(partida, capturadas);
	}
}