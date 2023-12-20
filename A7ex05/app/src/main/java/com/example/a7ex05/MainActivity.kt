package com.example.a7ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Context
import android.widget.Toast

/**
 * Este trecho de código pertence à função onCreate() de uma atividade Android.
 *
 * Passo a passo:
 * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
 * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
 * 3. Encontra a ImageView no layout usando o seu ID.
 * 4. Utiliza a biblioteca Glide para carregar uma imagem remota na ImageView.
 *    - A URL da imagem é especificada no método .load().
 * 5. Chama a função showText para exibir um Toast com a mensagem "Puuuudim".
 *
 * Propósito do código:
 * Este código carrega uma imagem remota em uma ImageView usando a biblioteca Glide,
 * exibindo simultaneamente um Toast com a mensagem "Puuuudim". O objetivo é demonstrar
 * o carregamento de imagens assíncrono e a exibição de mensagens breves na interface.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageView = findViewById<ImageView>(R.id.imgFoto)

        Glide.with(this)
            .load("https://static.itdg.com.br/images/360-240/d1307a2e17cda187df76b78cfd3ac464/shutterstock-2322251819-1-.jpg")
            .into(imageView)

        showText(this, "Puuuudim")
    }
}

fun showText(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
