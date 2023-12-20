package com.example.a07ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView

/**
 * Este trecho de código pertence à função onCreate() de uma atividade Android.
 *
 * Passo a passo:
 * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
 * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
 * 3. Encontra o botão de retorno ao topo no layout usando o seu ID.
 * 4. Encontra a ScrollView no layout usando o seu ID.
 * 5. Configura um listener de clique para o botão.
 *    - Quando o botão é clicado, faz um scroll suave para o topo da ScrollView.
 *
 * Propósito do código:
 * Este código implementa um botão que, quando clicado, realiza um scroll suave para
 * o topo de uma ScrollView. O objetivo é proporcionar uma maneira fácil de retornar ao
 * início do conteúdo visualizado na tela, além de mostrar o ScrollView para telas que ultrapassam
 * limite.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val backButton = findViewById<Button>(R.id.buttonBackToTop)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        backButton.setOnClickListener {
            scrollView.smoothScrollTo(0,0)
        }
    }
}
