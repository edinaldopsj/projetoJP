package com.example.a07ex08

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Context
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    // Declarando uma variável lateinit para armazenar um ImageView
    private lateinit var imageViewCaptured: ImageView

    private val captureActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Verifica se o resultado foi OK
        if (result.resultCode == RESULT_OK) {
            // Imagem capturada como um Bitmap
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            // Define o Bitmap capturado no ImageView
            imageViewCaptured.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa a variável imageViewCaptured com o ImageView do layout
        imageViewCaptured = findViewById(R.id.imageView_captured)

        // Obtém uma referência ao botão de captura do layout
        val buttonCapture: Button = findViewById(R.id.button_capture)

        // Configura um ouvinte de clique para o botão de captura
        buttonCapture.setOnClickListener {
            // Verifica se a permissão da câmera não foi concedida
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Solicita permissão para a câmera
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
            } else {
                // Chama a função 'exibirToast' para exibir um Toast
                exibirToast(this, "Fotografou")

                // Inicia a captura de imagem
                captureImage()
            }
        }
    }

    // Função para iniciar a captura de imagem
    private fun captureImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Inicia a atividade de captura de imagem e aguarda o resultado
        captureActivityResult.launch(takePictureIntent)
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 100
    }

    // Manipula a resposta da solicitação de permissão
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Se a permissão for concedida, inicia a captura de imagem
                    captureImage()
                } else {
                    // Se a permissão for negada, você pode informar ao usuário aqui
                }
                return
            }
            else -> {
                // Ignora todas as outras solicitações de permissão
            }
        }
    }
}


// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

