package leitores;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Arquivos {
	public static JFileChooser escolhaArquivo;
	public static int resposta;
	
	public static String retornaCaminho(char escolha) {
		escolhaArquivo = new JFileChooser();
		FileNameExtensionFilter filtro = null;
		
		if (escolha == 'i') {
			filtro = new FileNameExtensionFilter("Arquivo JPG ou Diretório", "jpg", "jpeg");
		} else if (escolha == 't'){
			filtro = new FileNameExtensionFilter("Arquivo TXT ou Diretório", "txt");
		}
		
		escolhaArquivo.setFileFilter(filtro);
		resposta = escolhaArquivo.showOpenDialog(escolhaArquivo);
		
		if (resposta == JFileChooser.APPROVE_OPTION) {
			return escolhaArquivo.getSelectedFile().toString();
		} else {
			return "Não foi possível acessar o caminho.";
		}
	}
	
	public static String salvarCaminho() {
		escolhaArquivo = new JFileChooser();
		escolhaArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		resposta = escolhaArquivo.showOpenDialog(escolhaArquivo);
		
		if (resposta == JFileChooser.APPROVE_OPTION) {
			return escolhaArquivo.getSelectedFile().toString();
		} else {
			return "Não foi possível acessar o caminho.";
		}
	}
}
