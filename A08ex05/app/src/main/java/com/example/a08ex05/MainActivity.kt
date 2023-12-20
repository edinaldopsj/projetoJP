package com.example.a08ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.a08ex05.api.Endpoint
import com.example.a08ex05.models.User
import com.example.a08ex05.models.UserResponse
import com.example.a08ex05.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meuBotao: Button = findViewById(R.id.btnFind)
        val textField: EditText = findViewById(R.id.editText)

        //Usuario digita no campo, quantos usuarios random ele quer criar
        meuBotao.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Lógica a ser executada quando o botão for clicado
                getUsers(textField.text.toString().toInt())
            }
        })

    }

    fun getUsers(numero: Int){
        //Separei em um package e uma classe pra poder melhorar a organizacao do codigo
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://randomuser.me/")

        //Pra poder lançar a requisição com o nome de usuario digitado pela pessoa, ultilizo o
        //@GET com parametro query, que pode ser visto na classe Endpoint
        //foi usado o @query porque a url termina com /api/?api=10

        val endpoint = retrofitClient.create(Endpoint::class.java)
        endpoint.getUsers(numero).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("TAG_", "Houve um erro!")
                t.printStackTrace()
            }
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                // cria uma lista de usuários
                val users: List<User> = response.body()!!.results

                // busca os emails de todos os usuários
                val adapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    users.map { it.name.first + " " + it.name.last })

                // Conectando o adapter à ListView
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = adapter
            }
        })

    }
}