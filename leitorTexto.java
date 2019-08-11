//Importações necessárias
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

//Classe LeitorTexto
public class LeitorTexto {

	// Método principal
	public static void main(String[] args) throws IOException {

		// Filtro para o caminho da imagem
		final JFileChooser escolhaArquivo = new JFileChooser();

		// Escolhe o nome do arquivo para conversão
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo TXT ou Diretório", "txt");

		// Setta o filtro para o arquivo
		escolhaArquivo.setFileFilter(filtro);

		// Variável para escolha do arquivo
		int resposta = escolhaArquivo.showOpenDialog(escolhaArquivo);

		// Se for aprovada a verificação do arquivo, complete o procedimento
		if (resposta == JFileChooser.APPROVE_OPTION) {

			// Armazena o arquivo para esolha
			String nomeCompleto = escolhaArquivo.getSelectedFile().toString();

			// Abre o formulário para escolha de diretório
			escolhaArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			// Resposta para o aprovamento do diretório
			int respostaDiretorio = escolhaArquivo.showOpenDialog(escolhaArquivo);

			// Se for aprovada a verificação do diretorio, complete o procedimento
			if (respostaDiretorio == JFileChooser.APPROVE_OPTION) {

				// Armazena o arquivo para escolha
				String caminhoDiretorio = escolhaArquivo.getSelectedFile().toString();

				// Cria um leitor para o arquivo texto
				FileReader arquivo = new FileReader(nomeCompleto);

				// Cria um buffer para a entrada de valores do arquivo texto para o programa
				BufferedReader texto = new BufferedReader(arquivo);

				// Abre um arquivo para a imagem a ser criada
				File arquivoImagem = new File(caminhoDiretorio + "/saidaImagem.jpg");

				// Linha para a formação da imagem atraves da leitura da mesma
				String linha = texto.readLine();

				//Teste fazer tais operações
				try {

					// Faz a divisão do texto para saber o tamanho da imagem
					String[] textoSplit = linha.split("%");

					// Pega o comprimento da imagem
					int comprimento = Integer.parseInt(textoSplit[0]);
					// Pega a largura da imagem
					int largura = Integer.parseInt(textoSplit[1]);

					// Imagem em buffer até a composição final
					BufferedImage img = new BufferedImage(largura, comprimento, BufferedImage.TYPE_INT_RGB);

					// Enquanto a linha do texto for diferente de nulo
					while ((linha = texto.readLine()) != null) {
						
						//Faz a divisão da linha para uma pegar os pixels
						textoSplit = linha.split(" ");

						// Pega, no primeiro espaço da linha a posição X
						int posicaoX = Integer.parseInt(textoSplit[0]);
						// Pega, no segundo espaço da linha a posição Y
						int posicaoY = Integer.parseInt(textoSplit[1]);

						// Pega, nas seguintes posições as cores vermelha, verde e azul
						int red = Integer.parseInt(textoSplit[2]);
						int green = Integer.parseInt(textoSplit[3]);
						int blue = Integer.parseInt(textoSplit[4]);

						// Cria um pixel para a formação da imagem
						int pixel = (red << 16) | (green << 8) | blue;

						// Coloca, na imagem criada, um pixel de determinada cor na posição determinada
						img.setRGB(posicaoY, posicaoX, pixel);
					}

					// Escreve, na imagem a ser criada, o pixel, o tipo da imagem, e o arquivo para
					// impressão
					ImageIO.write(img, "jpg", arquivoImagem);

					// Fecha o arquivo
					texto.close();

					// Mostra uma mensagem final
					JOptionPane.showMessageDialog(null, "Foi terminada a conversão...");
				} catch (FileNotFoundException ex) {

					// Caso o arquivo não exista
					System.out.println("Não foi possível achar o arquivo...");
				} catch (IOException ex) {

					// Caso ocorra um erro inesperado
					System.out.println("Ocorreu um erro inesperado...");
				}
			}
		}
	}
}
