package leitores;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LeitorTexto  {
	private FileReader arquivo;
	private BufferedReader texto;
	private File arquivoImagem;
	private String linha;
	private BufferedImage imagem;
	
	public String converteTexto() {
		try {
			int comprimento, largura, posicaoX, posicaoY, R, B, G, pixel;
			
			String caminhoDiretorio = Arquivos.retornaCaminho('t');
			String salvarArquivo = Arquivos.salvarCaminho();
			
			arquivo = new FileReader(caminhoDiretorio);
			texto = new BufferedReader(arquivo);
			arquivoImagem =  new File(salvarArquivo + "/saidaImagem.jpg");
			
			linha = texto.readLine();
			
			String[] textoSplit = linha.split("%");
			comprimento = Integer.parseInt(textoSplit[0]);
			largura = Integer.parseInt(textoSplit[1]);
			
			imagem = new BufferedImage(comprimento, largura, BufferedImage.TYPE_INT_RGB);
			
			while ((linha = texto.readLine()) != null) {
				textoSplit = linha.split(" ");
				
				posicaoX = Integer.parseInt(textoSplit[0]);
				posicaoY = Integer.parseInt(textoSplit[1]);
				R = Integer.parseInt(textoSplit[2]);
				G = Integer.parseInt(textoSplit[3]);
				B = Integer.parseInt(textoSplit[4]);
				
				pixel = (R << 16) | (G << 8) | B;
				
				imagem.setRGB(posicaoY, posicaoX, pixel);
			}
			
			ImageIO.write(imagem, "jpg", arquivoImagem);
			
			texto.close();
			
			return "A conversão foi realizada com sucesso!";
		} catch(Exception e) {
			return "Não foi possível realizar a operação...";
		}
	}
}
