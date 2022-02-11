package leitores

import java.util.Scanner

fun main() {
    val esc = Scanner(System.`in`)
    val imagem: LeitorImagem
    val texto: LeitorTexto

    println("Você deseja converter uma [i]magem ou [t]exto?: ")

    when(esc.next()) {
        "i" -> {
            imagem = LeitorImagem()
            println("Escolha um nome do arquivo para salvar: ")
            println(imagem.converterImagem(esc.next()))
        }

        "t" -> {
            texto = LeitorTexto()
            println(texto.converteTexto())
        }
    }
}