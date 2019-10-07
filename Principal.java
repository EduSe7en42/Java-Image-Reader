package leitores;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner esc = new Scanner(System.in);
		LeitorImagem imagem;
		LeitorTexto texto;
		
		System.out.println("Você deseja converter uma [i]magem ou [t]exto? :");
		String escolha = esc.next();
		
		switch(escolha) {
		case "i":
			imagem = new LeitorImagem();
			System.out.print("Escolha o nome do arquivo para salvar: ");						
			System.out.print(imagem.converteImagem(esc.next()));
			break;
		case "t":
			texto = new LeitorTexto();
			System.out.print(texto.converteTexto());
			break;
		}
	}
}
