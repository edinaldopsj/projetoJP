package com.example.a07ex03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Context
import android.widget.Toast

/**
 * Este trecho de código pertence à função onCreate() de uma atividade Android.
 *
 * Passo a passo:
 * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
 * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
 * 3. Encontra os botões de FloatingActionButton no layout usando seus IDs.
 * 4. Configura listeners de clique para cada botão.
 *    - Ao clicar no botão do navegador, exibe um Toast e inicia uma Intent para abrir o Google.
 *    - Ao clicar no botão de chamada, exibe um Toast e inicia uma Intent para discar um número.
 *    - Ao clicar no botão de SMS, exibe um Toast e inicia uma Intent para enviar um SMS.
 *    - Ao clicar no botão de e-mail, exibe um Toast e inicia uma Intent para enviar um e-mail.
 *
 * Proposta do código:
 * Este código, faz com que botões de FloatingActionButton,
 * cada um associado a uma ação específica. A proposta é fornecer funcionalidades de
 * navegação, chamada telefônica, envio de SMS e envio de e-mail, respondendo aos cliques
 * nos botões e exibindo mensagens informativas usando Toasts.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val browserButton = findViewById<FloatingActionButton>(R.id.browserButton)
        val callButton = findViewById<FloatingActionButton>(R.id.callButton)
        val smsButton = findViewById<FloatingActionButton>(R.id.smsButton)
        val emailButton = findViewById<FloatingActionButton>(R.id.emailButton)

        browserButton.setOnClickListener {
            showToast(this, "Acessar Google!") //exibir um toast com mensagem
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
        }
        callButton.setOnClickListener {
            showToast(this, "Fazer uma ligacao!") //exibir um toast com mensagem
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789")))
        }
        smsButton.setOnClickListener {
            showToast(this, "Mandar um SMS!") //exibir um toast com mensagem
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("sms:123456789")))
        }
        emailButton.setOnClickListener {
            showToast(this, "Enviar um e-mail!") //exibir um toast com mensagem
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:example@example.com")))
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

