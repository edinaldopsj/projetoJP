package com.example.A08ex02

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        txtResultado = findViewById(R.id.txtResultado)





        service.getUsers(5).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {


                if (response.isSuccessful) {
                    val dadosRecebidos = response.body()?.results
                    if (!dadosRecebidos.isNullOrEmpty()) {
                        // Filtra apenas os usuários que têm nomes
                        val usuariosComNomes = dadosRecebidos.filter { it.name != null }

                        if (usuariosComNomes.isNotEmpty()) {
                            // Leva apenas os primeiros 5 usuários com nomes
                            val usuarios = usuariosComNomes.take(30).map { "Pessoa: <b>${it.name?.fullName}</b>"}
                            txtResultado.text =
                                "Dados:\n${usuarios.joinToString("\n")}"

                            // Exibe o Snackbar com a mensagem "Dados carregados com sucesso"
                        } else {
                            txtResultado.text = "Nenhum usuário com nome encontrado"
                        }
                    } else {
                        txtResultado.text = "Erro na chamada da API: ${response.message()}"
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

                txtResultado.text = "Erro na chamada da API"
            }
        })
    }

}
