package com.example.a07ex07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    /**
     * Este trecho de código pertence à função onCreate() de uma atividade Android.
     *
     * Passo a passo:
     * 1. Chama o método onCreate da classe pai para realizar inicializações necessárias.
     * 2. Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     * 3. Encontra a RecyclerView no layout usando o seu ID.
     * 4. Configura um LinearLayoutManager para a RecyclerView, definindo a orientação como vertical.
     * 5. Cria um ArrayList de objetos da classe Produto.
     * 6. Adiciona dois produtos manualmente ao ArrayList.
     * 7. Utiliza um loop para adicionar mais 97 produtos ao ArrayList, simulando uma lista extensa.
     * 8. Inicializa um CustomAdapter com o ArrayList de produtos.
     * 9. Define o adapter da RecyclerView como o CustomAdapter criado.
     * 10. Cria uma Snackbar com a mensagem "Clique aqui para subir" e a exibe indefinidamente.
     * 11. Adiciona uma ação à Snackbar para realizar um scroll suave para o topo da RecyclerView
     *     quando o botão "Voltar ao topo" na Snackbar é clicado.
     * 12. Exibe a Snackbar.
     *
     * Propósito do código:
     * Este código cria uma lista de produtos utilizando uma RecyclerView e um CustomAdapter.
     * Além disso, exibe uma Snackbar informativa com um botão que, quando clicado, faz um scroll
     * suave para o topo da lista. O objetivo é fornecer uma experiência amigável ao usuário
     * para navegação em listas longas.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val r = findViewById<RecyclerView>(R.id.rv)
        r.layoutManager = LinearLayoutManager(this)

        val arr = ArrayList<Produto>()

        arr.add(Produto(android.R.drawable.ic_menu_camera, "Máquina fotográfica", "R$ 100,00"))
        arr.add(Produto(android.R.drawable.ic_menu_call, "Telefone analógico", "R$ 12,00"))

        for (i in 3 .. 99)
            arr.add(Produto(android.R.drawable.btn_star,"Produto $i", "R$ $i,99"))

        val adapter = CustomAdapter(arr)
        r.adapter = adapter

        var snack = Snackbar.make(r, "Clique aqui para subir", Snackbar.LENGTH_INDEFINITE)

        snack.setAction("Voltar ao topo") { r.smoothScrollToPosition(0) }
        snack.show()
    }
}