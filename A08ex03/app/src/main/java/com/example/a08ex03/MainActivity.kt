package com.example.a08ex03

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.a08ex03.api.Endpoint
import com.example.a08ex03.models.User
import com.example.a08ex03.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val meuBotao: Button = findViewById(R.id.btnFind)
        val textField: EditText = findViewById(R.id.editText)

        meuBotao.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Lógica a ser executada quando o botão for clicado
                getUsers(textField.text.toString())
            }
        })
    }


    //Fiz algumas modificacoes nos slides
    //A modificacao do projeto é, a pessoa escreve o nome de um usuario do github e clica no botao para pesquisar
    fun getUsers(usuario: String){

        //Separei em um package e uma classe pra poder melhorar a organizacao do codigo
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.github.com/")

        //Pra poder lançar a requisição com o nome de usuario digitado pela pessoa, ultilizo o
        //@GET com parametro path, que pode ser visto na classe Endpoint
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val users = endpoint.getUsers(usuario).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG_", "Houve um erro!")
                t.printStackTrace()
            }

            //Segui o mesmo esquema mostrado no slide, apenas mudando para mostrar as informações do usuario
            //ou "usuario nao econtrado"
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val dados = response.body()
                if(dados?.name == null){
                    findViewById<TextView>(R.id.txt).text = "Usuario nao encontrado!"
                }else {
                    var usuario = "${response.body()?.name}, ${response.body()?.login}"
                    val imgFoto = findViewById<ImageView>(R.id.imageView)
                    findViewById<TextView>(R.id.txt).text = usuario

                    //Uzei a base do codigo do A08ex01 que fiz apenas adaptando para a imagem
                    //Usei a mesma biblioteca, apenas colocando um tamanho fixo na imagem
                    Glide.with(applicationContext)
                        .load(response.body()?.avatar_url)
                        .apply(
                            RequestOptions()
                                .override(
                                    400,
                                    400
                                ) // Defina a largura e altura desejadas
                        ).into(imgFoto)
                }
            }
        })
    }
}