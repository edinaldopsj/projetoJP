package com.example.a07ex10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    /**
     * Este trecho de código pertence à MainActivity em uma aplicação Android com integração ao Google Maps.
     *
     * Passo a passo:
     * 1. Declaração da classe MainActivity que estende AppCompatActivity e implementa OnMapReadyCallback.
     * 2. Declaração de uma propriedade privada lateinit mMap para representar o GoogleMap.
     * 3. No método onCreate, chama o método da classe pai para realizar inicializações necessárias.
     *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     *    - Obtém uma referência ao SupportMapFragment no layout usando o ID 'map'.
     *    - Chama o método getMapAsync() no SupportMapFragment para registrar a MainActivity como
     *      um callback a ser notificado quando o GoogleMap estiver pronto.
     * 4. No método onMapReady, é chamado quando o GoogleMap está pronto para uso.
     *    - Inicializa a propriedade mMap com a instância de GoogleMap fornecida como parâmetro.
     *    - Exibe um Toast informativo "Busque sua localizacao".
     * 5. A função showToast é declarada para exibir um Toast com a mensagem fornecida.
     *
     * Propósito do código:
     * Este código integra o Google Maps em uma aplicação Android.
     * Quando a atividade é criada, ela obtém a referência ao SupportMapFragment
     * e registra a MainActivity como um callback para ser notificado quando o GoogleMap estiver pronto.
     * Quando o GoogleMap está pronto, é exibido um Toast informativo.
     */

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        showToast(this, "Busque sua localizacao")
    }
}

fun showToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

