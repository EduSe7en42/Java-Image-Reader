package leitores

import java.awt.image.BufferedImage
import java.io.File
import java.io.PrintWriter
import javax.imageio.ImageIO

class LeitorImagem {
    private val arq: Arquivos = Arquivos()

    var arquivo: File? = null
    var imagem: BufferedImage? = null
    var texto: PrintWriter? = null

    fun converterImagem(nome: String): String {
        val comprimento: Int
        val largura: Int
        var cor: Int
        var r: Int
        var g: Int
        var b: Int

        try {
            arquivo = File(arq.retornaCaminho("i"))
            imagem = ImageIO.read(arquivo)

            texto = PrintWriter(arq.salvarCaminho() + '/' + nome + ".txt")

            comprimento = imagem!!.tileHeight
            largura = imagem!!.tileWidth

            texto!!.println("$comprimento%$largura")

            for (i in 0 until imagem!!.tileHeight) {
                for (j in 0 until imagem!!.tileWidth) {
                    cor = imagem!!.getRGB(j, i)

                    r = cor and 0x00ff0000 shr 16
                    g = cor and 0x0000ff00 shr 8
                    b = cor and 0x000000ff

                    texto!!.println("n $i $j $r $g $b")
                }
            }

            texto!!.close()
            return "A conversão acabou..."
        } catch (e: Exception) {
            return "Não foi possível realizar a ação. Erro: ${e.stackTraceToString()}"
        }
    }
}