//Code by Eduardo Pereira Ribeiro
//JIR - Java Image Reader
//Parte I - Leitor e Escritor para a imagem no arquivo de texto

//Pacote principal
package leitorImagem;

//Importando todos os pacotes nessecários
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;

//Classe LeitorImagem
public class LeitorImagem {

	//Método principal
	public static void main(String[] args) throws IOException{
		String nomeImagem = JOptionPane.showInputDialog("Digite o nome da imagem (sem extensão)");
		
		//Criando objeto "File" onde armazanará o caminho da imagem
		File arquivo = new File("C:\\Users\\eduar\\Desktop\\" + nomeImagem + ".jpg");
		
		//Imagem armazenada em buffer para ser trabalhada
		BufferedImage imagem = ImageIO.read(arquivo);
		
		//Pede o nome de arquivo de texto
		String nomeTexto = JOptionPane.showInputDialog("Digite o nome do arquivo");
		
		//Cria o arquivo de texto para a transliteração
		PrintWriter texto = new PrintWriter("C:\\Users\\eduar\\Desktop\\" + nomeTexto + ".txt", "UTF-8");
		
		//Comando que "caminha" pela imagem e vai imprimindo os valores de RGB de cada pixel no texto
		for (int i = 0; i < imagem.getTileHeight(); i++) {
			for (int j = 0; j < imagem.getTileWidth(); j++) {
				
				//Captura o RGB de cada pixel
				int cor = imagem.getRGB(j, i);
				
				//Converte o comando de um comando que eu não conheço para hexadecimal
				int  red   = (cor & 0x00ff0000) >> 16;
				int  green = (cor & 0x0000ff00) >> 8;
				int  blue  =  cor & 0x000000ff;
				
				//Imprime o valor no texto 
				texto.println(i + " " + j + " " + red + " " + green + " " + blue);
			}
		}
		
		//Fecha o buffer do arquivo de texto
		texto.close();
	}

}
