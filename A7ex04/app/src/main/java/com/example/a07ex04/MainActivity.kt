package com.example.a07ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.core.widget.NestedScrollView
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define o layout da atividade com base em 'activity_main.xml'
        setContentView(R.layout.activity_main)
        // Chama a função 'exibirToast' para exibir um Toast
        exibirToast(this, "Calendários, eeeee")
    }
}

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}
