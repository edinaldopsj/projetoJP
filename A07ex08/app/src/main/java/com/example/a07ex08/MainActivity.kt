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
    /**
     * Este trecho de código pertence à MainActivity em uma aplicação Android.
     *
     * Passo a passo:
     * 1. Declara uma lateinit var imageViewCaptured para armazenar a ImageView que exibirá a imagem capturada.
     * 2. Registra um Activity Result para captura de imagem usando ActivityResultContracts.StartActivityForResult.
     *    - No retorno, verifica se o resultado foi bem-sucedido e exibe a imagem capturada na ImageView.
     * 3. Chama o método onCreate ao iniciar a atividade.
     *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     *    - Inicializa a imageViewCaptured com a referência à ImageView no layout.
     *    - Encontra o botão de captura no layout e configura um listener de clique.
     *    - Verifica se a permissão da câmera foi concedida; se não, solicita a permissão ao usuário.
     *    - Se a permissão for concedida, exibe um Toast e inicia a captura de imagem.
     * 4. O método captureImage inicia uma Intent para captura de imagem usando a câmera.
     * 5. Define uma constante REQUEST_CAMERA_PERMISSION para o código de solicitação da permissão.
     * 6. Sobrescreve o método onRequestPermissionsResult para lidar com o resultado da solicitação de permissão.
     *    - Se a permissão for concedida, inicia a captura de imagem.
     *    - Se a permissão for negada, você pode informar ao usuário.
     *
     * Propósito do código:
     * Este código implementa a captura de imagem usando a câmera do dispositivo.
     * Ao clicar no botão de captura, verifica a permissão da câmera, solicita se necessário,
     * exibe um Toast informativo e inicia a captura de imagem, exibindo-a na ImageView.
     */

    private lateinit var imageViewCaptured: ImageView

    private val captureActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imageViewCaptured.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewCaptured = findViewById(R.id.imageView_captured)

        val buttonCapture: Button = findViewById(R.id.button_capture)

        buttonCapture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
            } else {
                showToast(this, "Olha o X")

                captureImage()
            }
        }
    }

    private fun captureImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        captureActivityResult.launch(takePictureIntent)
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 100
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
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


fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

