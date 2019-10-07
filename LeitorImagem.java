package leitores;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LeitorImagem {
	private File arquivo;
	private BufferedImage imagem;
	private PrintWriter texto;
	
	public String converteImagem(String nome){
		int comprimento, largura, i, j, cor, R, G, B;
		
		try {
			arquivo = new File(Arquivos.retornaCaminho('i'));
			imagem = ImageIO.read(arquivo);
		
			texto = new PrintWriter(Arquivos.salvarCaminho() + "/" + nome + ".txt");
		
			comprimento = imagem.getTileHeight();  
			largura = imagem.getTileWidth();
		
			texto.println(comprimento + "%" + largura);
			for (i = 0; i < imagem.getTileHeight(); i++) {
				for (j = 0; j < imagem.getTileWidth(); j++) {
					cor = imagem.getRGB(j, i);
				
					R = (cor & 0x00ff0000) >> 16;
					G = (cor & 0x0000ff00) >> 8;
					B = cor & 0x000000ff;
					texto.println(i + " " + j + " " + R + " " + G + " " + B);
				}
			}
		
			texto.close();		
			return "A conversão acabou";
		} catch (Exception e) {
			return "Não foi possível realizar a ação: " + e;
		}
	}
}
