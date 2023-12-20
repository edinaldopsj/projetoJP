package com.example.a08ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.a08ex01.api.Endpoint
import com.example.a08ex01.models.User
import com.example.a08ex01.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    /**
    * Este trecho de código pertence à MainActivity em uma aplicação Android com integração ao Retrofit para
    * consumir a API do GitHub e exibir informações de um usuário.
    * 
    * Passo a passo:
    * 1. No método onCreate, é chamado ao iniciar a atividade.
    *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
    *    - Inicializa botão e campo de texto da interface.
    *    - Configura um listener de clique para o botão, que chama a função getUsers ao ser clicado.
    * 2. A função getUsers recebe um nome de usuário como parâmetro.
    *    - Inicializa o cliente Retrofit para realizar chamadas à API do GitHub.
    *    - Cria uma instância do serviço Endpoint para definição das operações da API.
    *    - Chama a função getUsers do serviço passando o nome de usuário e configura callbacks para resposta e falha.
    * 3. No callback onResponse, é chamado quando a requisição à API é bem-sucedida.
    *    - Obtém os dados do usuário da resposta.
    *    - Verifica se o nome do usuário é nulo; se for, exibe "Usuário não encontrado!" no TextView da interface.
    *    - Se o nome não for nulo, cria uma string com informações do usuário e exibe no TextView da interface.
    * 4. No callback onFailure, é chamado quando há uma falha na requisição à API.
    *    - Exibe uma mensagem de erro no log e imprime o stack trace da exceção.
    * 
    * Propósito do código:
    * Este código permite que o usuário insira um nome de usuário do GitHub em um campo de texto,
    * clique em um botão, e receba informações sobre o usuário (nome, empresa, localização) da API do GitHub,
    * utilizando Retrofit para realizar chamadas à API e exibindo os resultados em um TextView na interface.
    */

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

   fun getUsers(usuario: String){

       val retrofitClient = NetworkUtils
           .getRetrofitInstance("https://api.github.com/")

       val endpoint = retrofitClient.create(Endpoint::class.java)
       val users = endpoint.getUsers(usuario).enqueue(object : Callback<User>{
           override fun onFailure(call: Call<User>, t: Throwable) {
               Log.d("TAG_", "Houve um erro!")
               t.printStackTrace()
           }

           //Segui o mesmo esquema mostrado no slide, apenas mudando para mostrar as informações do usuario

           override fun onResponse(call: Call<User>, response: Response<User>) {
               val dados = response.body()
               if(dados?.name == null){
                   findViewById<TextView>(R.id.txt).text = "Usuario nao encontrado!"
               }else {
                   var usuario = "${response.body()?.name}, ${response.body()?.company}, ${response.body()?.location}"
                   findViewById<TextView>(R.id.txt).text = usuario
               }
           }
       })
   }
}