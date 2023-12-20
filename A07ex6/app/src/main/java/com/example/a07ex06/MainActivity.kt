package com.example.a07ex06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chama a função 'exibirToast' para exibir um Toast
        exibirToast(this, "Deu Certo")

        // Criando um array de strings de tamanho 100
        val strings = Array(100) { i -> "Item ${i + 1}" }

        // Configurando o ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)

        // Conectando o adapter à ListView
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
    }
}
// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    // Cria um Toast com a mensagem fornecida e exibe-o na tela
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

