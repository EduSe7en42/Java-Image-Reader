//Code by Eduardo Pereira Ribeiro
//JIR - Java Image Reader
//Parte I - Leitor e Escritor para a imagem no arquivo de texto

//Importando todos os pacotes necessários
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;

//Classe LeitorImagem
public class LeitorImagem {

	// Método principal
	public static void main(String[] args) throws IOException {
		// Variáveis do programa
		String nomeImagem;

		// Abrem o arquivo para conversão
		final JFileChooser escolhaArquivo = new JFileChooser();

		// Escolhe o filtro da imagem
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo JPG ou Diretório", "jpg", "jpeg");

		// Adiciona um filtro para a imagem
		escolhaArquivo.setFileFilter(filtro);
		int resposta = escolhaArquivo.showOpenDialog(escolhaArquivo);

		// Se for aprovado o uso do arquivo ele continua com a operação
		if (resposta == JFileChooser.APPROVE_OPTION) {

			// Adiciona numa variável o arquivo
			nomeImagem = escolhaArquivo.getSelectedFile().toString();

			// Criando objeto "File" onde armazanará o caminho da imagem
			File arquivo = new File(nomeImagem);

			// Imagem armazenada em buffer para ser trabalhada
			BufferedImage imagem = ImageIO.read(arquivo);

			// Pede o nome de arquivo de texto
			String nomeTexto = JOptionPane.showInputDialog("Digite o nome do arquivo de texto final (sem extensão): ");

			// Pede o caminho para o usuário salvar
			escolhaArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			// Resposta para saber se o arquivo é válido
			int respostaTexto = escolhaArquivo.showOpenDialog(escolhaArquivo);

			// Se o caminho do arquivo for válido, faz as opções
			if (respostaTexto == JFileChooser.APPROVE_OPTION) {

				// Salva o local do arquivo
				String caminhoTexto = escolhaArquivo.getSelectedFile().toString();

				// Cria um objeto para salvar o texto em um buffer
				PrintWriter texto = new PrintWriter(caminhoTexto + "/" + nomeTexto + ".txt");
				
				// Adiciona metadados de tamanho na imagem
				int comprimento = imagem.getTileHeight();  
				int largura = imagem.getTileWidth();
				
				//Insere o tamanho da imagem no texto
				texto.println(comprimento + "%" + largura);
				
				/* Comando que "caminha" pela imagem e vai imprimindo os valores de RGB de cada
				 pixel no texto*/
				for (int i = 0; i < imagem.getTileHeight(); i++) {
					for (int j = 0; j < imagem.getTileWidth(); j++) {

						// Captura o RGB de cada pixel
						int cor = imagem.getRGB(j, i);

						// Converte o comando de um comando que eu não conheço para hexadecimal
						int red = (cor & 0x00ff0000) >> 16;
						int green = (cor & 0x0000ff00) >> 8;
						int blue = cor & 0x000000ff;

						// Imprime o valor no texto
						texto.println(i + " " + j + " " + red + " " + green + " " + blue);
					}
				}

				// Fecha o buffer do arquivo de texto
				texto.close();

				// Mostra uma mensagem final
				JOptionPane.showMessageDialog(null, "A conversão acabou...");
			} else {

				// Opçõo caso não seja possível abrir o diretório
				JOptionPane.showMessageDialog(null, "Não foi possível encontrar o diretório...");
			}
		} else {

			// Opçõo caso não seja possével abrir o arquivo
			JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo...");
		}
	}

}
