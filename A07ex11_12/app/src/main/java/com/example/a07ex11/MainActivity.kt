package com.example.a07ex11

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random
import android.content.Context
import android.widget.Toast


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    /**
     * Este trecho de código pertence à MainActivity em uma aplicação Android com integração ao Google Maps.
     *
     * Passo a passo:
     * 1. Declara as propriedades lateinit mapView e lateinit googleMap para representar o MapView e GoogleMap.
     * 2. No método onCreate, chama o método da classe pai para realizar inicializações necessárias.
     *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     *    - Inicializa mapView com a referência ao MapView no layout.
     *    - Chama o método onCreate() do mapView e registra um callback para notificar quando o GoogleMap estiver pronto.
     * 3. No método onMapReady, é chamado quando o GoogleMap está pronto para uso.
     *    - Inicializa a propriedade googleMap com a instância de GoogleMap fornecida como parâmetro.
     *    - Chama setupButtonClickListeners para configurar listeners de clique para os botões na interface.
     * 4. A função setupButtonClickListeners configura listeners de clique para os botões "Home", "School" e "Work".
     *    - Cada botão exibe um Toast informativo e coloca um marcador aleatório no mapa com um título específico.
     * 5. A função placeRandomMarker coloca um marcador no mapa com uma posição aleatória próxima a Barretos.
     *    - Gera uma posição aleatória em um raio de 3000 metros em relação ao centro de Barretos.
     *    - Adiciona um marcador no mapa com a posição gerada e ajusta a câmera para focar nesse marcador.
     * 6. Os métodos de ciclo de vida do MapView são implementados para garantir o gerenciamento correto do ciclo de vida.
     *    - onStart, onResume, onPause, onStop, onDestroy, onSaveInstanceState e onLowMemory.
     * 7. A função showMessage exibe um Toast com a mensagem fornecida.
     *
     * Propósito do código:
     * Este código integra o Google Maps em uma aplicação Android.
     * A atividade contém um MapView que, quando criado, inicializa o GoogleMap e configura botões
     * para adicionar marcadores aleatórios no mapa, cada um representando um local específico (casa, escola, trabalho).
     */

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        findViewById<Button>(R.id.buttonHome).setOnClickListener {
            showMessage(this, "Bora ir para casa!!!!")
            placeRandomMarker("Casa")
        }

        findViewById<Button>(R.id.buttonSchool).setOnClickListener {
            showMessage(this, "É hora de estudar!!!!")
            placeRandomMarker("Escola")
        }

        findViewById<Button>(R.id.buttonWork).setOnClickListener {
            showMessage(this, "Uhuuu, hora do trabalho!!!!")
            placeRandomMarker("Trabalho")
        }
    }

    private fun placeRandomMarker(title: String) {
        val randomLatLng = getRandomLocationNearBarretos()

        googleMap.addMarker(MarkerOptions().position(randomLatLng).title(title))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(randomLatLng, 15f))
    }

    private fun getRandomLocationNearBarretos(): LatLng {
        val center = LatLng(-20.5587, -48.5679)

        val radius = 3000
        val random = Random

        val x0 = center.longitude
        val y0 = center.latitude

        val radiusInDegrees = radius / 111320f

        val u = random.nextDouble()
        val v = random.nextDouble()
        val w = radiusInDegrees * sqrt(u)
        val t = 2 * Math.PI * v
        val x = w * cos(t)
        val newLongitude = x / cos(Math.toRadians(y0))

        val y = w * sin(t)
        val newLatitude = y

        val newLat = newLatitude + y0
        val newLon = newLongitude + x0

        return LatLng(newLat, newLon)
    }
    
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


