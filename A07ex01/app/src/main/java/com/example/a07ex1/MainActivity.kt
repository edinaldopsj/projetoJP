package com.example.a07ex1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Método chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define o layout da atividade com base em 'activity_main.xml'
        setContentView(R.layout.activity_main)

        // Encontra o elemento TextView no layout com o ID 'textView'
        val textView = findViewById<TextView>(R.id.textView)

        // Chama a snackbar e o toast ao clicar no textview
        textView.setOnClickListener {
            // Cria um Snackbar que exibe o texto do TextView
            val snackBar = Snackbar.make(it, textView.text, Snackbar.LENGTH_LONG)
            snackBar.show()

            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "Clicou!!!!")
        }
    }
}

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}