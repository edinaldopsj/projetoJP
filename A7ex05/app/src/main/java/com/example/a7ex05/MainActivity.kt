package com.example.a7ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Context
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageView = findViewById<ImageView>(R.id.imgFoto)

        Glide.with(this) // Inicializa o carregamento de imagem
            .load("https://static.itdg.com.br/images/360-240/d1307a2e17cda187df76b78cfd3ac464/shutterstock-2322251819-1-.jpg")
            // Carrega a imagem da URL
            .into(imageView) // Exibe a imagem no ImageView

        // Chama a função 'exibirToast' para exibir um Toast
        exibirToast(this, "Puuuudim")

    }
}

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    // Cria um Toast com a mensagem fornecida e exibe-o na tela
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}
