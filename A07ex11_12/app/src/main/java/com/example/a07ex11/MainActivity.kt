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

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização do MapView
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Callback chamado quando o mapa está pronto para uso
        this.googleMap = googleMap

        // Configura os listners de clique dos botões
        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        // Configura os ouvintes de clique para os botões do aplicativo
        findViewById<Button>(R.id.buttonHome).setOnClickListener {
            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "Bora ir para casa!!!!")
            // Coloca um marcador aleatório com o título "Casa" no mapa
            placeRandomMarker("Casa")
        }

        findViewById<Button>(R.id.buttonSchool).setOnClickListener {
            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "É hora de estudar!!!!")
            // Coloca um marcador aleatório com o título "Escola" no mapa
            placeRandomMarker("Escola")
        }

        findViewById<Button>(R.id.buttonWork).setOnClickListener {
            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "Uhuuu, hora do trabalho!!!!")
            // Coloca um marcador aleatório com o título "Trabalho" no mapa
            placeRandomMarker("Trabalho")
        }
    }

    private fun placeRandomMarker(title: String) {
        // Gera uma localização aleatória próxima a Barretos-SP
        val randomLatLng = getRandomLocationNearBarretos()

        // Adiciona um marcador no mapa com a posição e título especificados
        googleMap.addMarker(MarkerOptions().position(randomLatLng).title(title))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(randomLatLng, 15f))
    }

    // Função para gerar uma localização aleatória próxima a Barretos-SP
    private fun getRandomLocationNearBarretos(): LatLng {
        // Centro aproximado de Barretos-SP
        val center = LatLng(-20.5587, -48.5679)

        // Raio em metros para geração de pontos aleatórios
        val radius = 3000
        val random = Random

        val x0 = center.longitude
        val y0 = center.latitude

        // Converte o raio de metros para graus
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

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}


