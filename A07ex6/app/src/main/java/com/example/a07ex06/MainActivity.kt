package com.example.a07ex06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    /**
     * Este trecho de código pertence à função onCreate() de uma atividade Android.
     *
     * Passo a passo:
     * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
     * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     * 3. Cria um array de strings contendo 100 elementos no formato "Item X", onde X é o índice.
     * 4. Inicializa um ArrayAdapter com o contexto atual, um layout de item padrão e o array de strings.
     * 5. Encontra a ListView no layout usando o seu ID.
     * 6. Encontra o botão de retorno ao topo no layout usando o seu ID.
     * 7. Configura um listener de clique para o botão.
     *    - Quando o botão é clicado, faz um scroll suave para a posição inicial da ListView.
     * 8. Define o adapter da ListView como o ArrayAdapter criado.
     *
     * Propósito do código:
     * Este código cria uma lista de 100 itens e os exibe em uma ListView. Além disso, implementa
     * um botão que, quando clicado, realiza um scroll suave para o topo da lista. O objetivo é
     * demonstrar o uso de uma ListView e a funcionalidade de retorno ao topo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val strings = Array(100) { i -> "Item ${i + 1}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
        val listView = findViewById<ListView>(R.id.listView)
        val backButton = findViewById<Button>(R.id.backToTopButton)

        backButton.setOnClickListener {
            listView.smoothScrollToPosition(0,0)
        }

        listView.adapter = adapter
    }
}
