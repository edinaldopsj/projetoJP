package com.example.a07ex1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Este trecho de código pertence à função onCreate() de uma atividade Android.
 *
 * Passo a passo:
 * 1. Chama o método onCreate da classe pai, realizando as inicializações necessárias.
 * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
 * 3. Encontra o TextView no layout usando o seu ID.
 * 4. Configura um listener de clique para o TextView.
 *    - Quando o TextView é clicado, a função translate() é chamada.
 *
 * Proposta do código:
 * Este código tem como objetivo responder ao
 * clique no TextView, chamando a função translate(), que realiza uma
 * ação específica, neste caso, uma tradução de texto.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
           translate(textView)
        }
    }
}


fun translate(textView: TextView) {
    textView.text = "Olá mundo!"
}