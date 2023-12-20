package com.example.a07ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // cria um objeto representando o layout da tela
        var meuLayout = findViewById<ConstraintLayout>(R.id.meuLayout)

        // define um evento ao clicar no layout (qualquer parte da tela)
        meuLayout.setOnClickListener { view ->
            // cria um objeto que representa o TextView da tela
            var rotulo = findViewById<TextView>(R.id.textView)

            // mostra um Snackbar curto
            Snackbar.make(view, "Mensagem", Snackbar.LENGTH_SHORT).show()

            // mostra um Snackbar longo
            Snackbar.make(view, "Mensagem", Snackbar.LENGTH_LONG).show()

            // mostra um Snackbar de fechar
            var s = Snackbar.make(view, rotulo.text, Snackbar.LENGTH_INDEFINITE)
            s.setAction("Fechar") { s.dismiss() }
            s.show()
            exibirToast(this, "Parabéns, CLicou na tela") //exibe um toast com mensagem
        }
    }
}

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}