package com.example.a7ex09

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    /**
     * Este trecho de código pertence à MainActivity em uma aplicação Android.
     *
     * Passo a passo:
     * 1. Declara as variáveis btnCapturar e imgFoto para o botão de captura e a ImageView, respectivamente.
     * 2. No método onCreate, chama o método da classe pai para realizar inicializações necessárias.
     *    - Define o layout da atividade a partir do arquivo XML (activity_main.xml).
     *    - Inicializa btnCapturar e imgFoto com as referências do botão e da ImageView no layout.
     * 3. Registra um Activity Result para a captura de imagem usando ActivityResultContracts.TakePicturePreview.
     *    - No retorno, exibe a imagem capturada na ImageView.
     *    - Converte a imagem para um InputImage para processamento do código de barras.
     *    - Utiliza a API BarcodeScanning para processar o código de barras na imagem.
     *    - Exibe a URL extraída do QR Code no TextView txtResultado, se presente.
     *    - Adiciona casos adicionais para outros tipos de dados de código de barras, se necessário.
     *    - Registra um log de erro em caso de falha no processamento.
     * 4. O método getPhoto é chamado quando o botão de captura é clicado.
     *    - Exibe um Toast informativo "Diga X!!!!".
     *    - Inicia o processo de captura de imagem.
     *
     * Propósito do código:
     * Este código implementa a captura de imagem usando a câmera e processa o código de barras na imagem,
     * extraindo e exibindo a URL do QR Code no layout da atividade.
     */

    lateinit var btnCapturar: Button
    lateinit var imgFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCapturar = findViewById(R.id.btnCapturar)
        imgFoto = findViewById(R.id.imgFoto)
    }

    val register = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { image: Bitmap? ->
        image?.let { bitmap ->
            imgFoto.setImageBitmap(bitmap)

            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val result = BarcodeScanning.getClient().process(inputImage)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val valueType = barcode.valueType
                        when (valueType) {
                            Barcode.TYPE_URL -> {
                                barcode.url?.let { url ->
                                    findViewById<TextView>(R.id.txtResultado).text = url.url
                                } ?: run {
                                    findViewById<TextView>(R.id.txtResultado).text = "URL não encontrado no QR Code."
                                }
                            }
                            // Você pode adicionar mais casos aqui para outros tipos de dados de barcode.
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("=====", e.printStackTrace().toString())
                }
        }
    }

    fun getPhoto(view: View) {
        showToast(this, "Diga X!!!!")
        register.launch(null)
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}