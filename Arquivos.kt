package leitores

import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class Arquivos {
    private val mensagem = "Não foi possível acessar o caminho."

    var escolhaArquivo: JFileChooser? = null
    var resposta: Int = 0

    fun retornaCaminho(escolha: String): String {
        escolhaArquivo = JFileChooser()

        val filtro: FileNameExtensionFilter = if (escolha == "i") FileNameExtensionFilter("Arquivo JPG ou Diretório", "jpg", "jpeg") else FileNameExtensionFilter("Arquivo TXT ou Diretório", "txt")

        escolhaArquivo!!.fileFilter = filtro
        resposta = escolhaArquivo!!.showOpenDialog(escolhaArquivo)

        return if (resposta.equals(JFileChooser.APPROVE_OPTION))
            escolhaArquivo!!.getSelectedFile().toString()
        else
            mensagem
    }

    fun salvarCaminho(): String {
        escolhaArquivo = JFileChooser()
        escolhaArquivo!!.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY

        resposta = escolhaArquivo!!.showOpenDialog(escolhaArquivo)

        return if (resposta.equals(JFileChooser.APPROVE_OPTION))
            escolhaArquivo!!.selectedFile.toString()
        else
            mensagem
    }
}