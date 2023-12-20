package com.example.a07ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.widget.Toast

/**
 * Este trecho de código pertence à função onCreate() de uma atividade Android.
 *
 * Passo a passo:
 * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
 * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
 * 3. Encontra o ConstraintLayout no layout usando o seu ID.
 * 4. Configura um listener de clique para o ConstraintLayout.
 *    - Quando o ConstraintLayout é clicado, executa uma série de ações.
 *      - Exibe um Snackbar curto com a mensagem "Mensagem".
 *      - Exibe um Snackbar longo com a mensagem "Mensagem".
 *      - Cria um Snackbar com a mensagem do TextView e ação "Fechar".
 *        - A ação "Fechar" fecha o Snackbar quando clicada.
 *      - Exibe um Toast com a mensagem "Exibindo toast na tela".
 *
 * Proposta do código:
 * Este código tem como objetivo responder ao
 * clique no ConstraintLayout. Quando clicado, ele mostra Snackbars com diferentes
 * mensagens e durações, e também exibe um Toast parabenizando o usuário pelo clique.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var meuLayout = findViewById<ConstraintLayout>(R.id.meuLayout)

        meuLayout.setOnClickListener { view ->
            var rotulo = findViewById<TextView>(R.id.textView)

            Snackbar.make(view, "Mensagem Curta", Snackbar.LENGTH_SHORT).show()

            Snackbar.make(view, "Mensagem Longa", Snackbar.LENGTH_LONG).show()

            var snack = Snackbar.make(view, rotulo.text, Snackbar.LENGTH_INDEFINITE)

            snack.setAction("Fechar") { snack.dismiss() }
            snack.show()

            showToast(this, "Exibindo toast na tela")
        }
    }
}

// Função para exibir um Toast na tela
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}