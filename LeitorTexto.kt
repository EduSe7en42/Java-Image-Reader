package leitores

import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.imageio.ImageIO

class LeitorTexto {
    private val arq: Arquivos = Arquivos()

    private var arquivo: FileReader? = null
    private var texto: BufferedReader? = null
    private var arquivoImagem: File? = null
    private var linha: String? = null
    private var imagem: BufferedImage? = null

    fun converteTexto(): String {
        var x: Int
        var y: Int
        var r: Int
        var g: Int
        var b: Int
        var pixel: Int

        try {
            val caminhoDiretorio: String = arq.retornaCaminho("t")
            val salvarArquivo: String = arq.salvarCaminho()

            arquivo = FileReader(caminhoDiretorio)
            texto = BufferedReader(arquivo)
            arquivoImagem = File("$salvarArquivo/saidaImagem.jpg")

            linha = texto!!.readLine()

            var textoSplit = linha!!.split("%").toTypedArray()

            val comprimento = textoSplit[0].toInt()
            val largura = textoSplit[1].toInt();

            imagem = BufferedImage(comprimento, largura, BufferedImage.TYPE_INT_RGB)

            while (texto!!.readLine().also { linha = it } != null) {
                textoSplit = linha!!.split(" ").toTypedArray()

                if (textoSplit[0] == "n") {
                    x = textoSplit[1].toInt()
                    y = textoSplit[2].toInt()

                    r = textoSplit[3].toInt()
                    g = textoSplit[4].toInt()
                    b = textoSplit[5].toInt()

                    pixel = r shl 16 or (g shl 8) or b

                    imagem!!.setRGB(y, x, pixel)
                } else {
                    //TODO Adicionar para múltiplas linhas
                }
            }

            ImageIO.write(imagem, "jpg", arquivoImagem)
            texto!!.close()

            return "A conversão foi realizada com sucesso..."
        } catch (e: Exception) {
            return "Não foi possível realizar a operação. Erro: ${e.stackTraceToString()}"
        }
    }
}