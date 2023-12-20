package com.example.a08ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.a08ex02.api.Endpoint
import com.example.a08ex02.models.User
import com.example.a08ex02.models.UserResponse
import com.example.a08ex02.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
      /**
    * Este trecho de código pertence à MainActivity em uma aplicação Android com integração ao Retrofit para
    * consumir a API Random User Generator e exibir informações de usuários.
    * 
    * Passo a passo:
    * 1. No método onCreate, é chamado ao iniciar a atividade.
    *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
    *    - Inicializa botão e campo de texto da interface.
    *    - Configura um listener de clique para o botão, que chama a função getUsers ao ser clicado.
    * 2. A função getUsers recebe um número como parâmetro.
    *    - Inicializa o cliente Retrofit para realizar chamadas à API Random User Generator.
    *    - Cria uma instância do serviço Endpoint para definição das operações da API.
    *    - Chama a função getUsers do serviço passando o número de usuários desejados e configura callbacks para resposta e falha.
    * 3. No callback onResponse, é chamado quando a requisição à API é bem-sucedida.
    *    - Obtém a lista de usuários da resposta.
    *    - Itera sobre a lista para obter informações (primeiro nome, último nome, e-mail) de cada usuário.
    *    - Concatena as informações em uma string e exibe no TextView da interface.
    * 4. No callback onFailure, é chamado quando há uma falha na requisição à API.
    *    - Exibe uma mensagem de erro no log e imprime o stack trace da exceção.
    * 
    * Propósito do código:
    * Este código permite que o usuário insira um número no campo de texto, clique em um botão,
    * e receba informações de usuários gerados aleatoriamente da API Random User Generator,
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
                getUsers(textField.text.toString().toInt())
            }
        })
    }

    fun getUsers(numero: Int){
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://randomuser.me/")

        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getUsers(numero).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("TAG_", "Houve um erro!")
                t.printStackTrace()
            }
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val users: List<User> = response.body()!!.results

                var usuarios = "Usuários:\n"
                for (u in users)
                    usuarios += "${u.name.first} ${u.name.last} (${u.email})\n"

                findViewById<TextView>(R.id.txt).text = usuarios
            }
        })

    }
}
