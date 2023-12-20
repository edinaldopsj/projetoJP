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

    lateinit var btnCapturar: Button
    lateinit var imgFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCapturar = findViewById(R.id.btnCapturar)
        imgFoto = findViewById(R.id.imgFoto)
    }

    // objeto de registro do evento de tirar foto
    val register = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { image: Bitmap? ->  // 'image' pode ser nulo, por isso 'Bitmap?' é usado.
        image?.let { bitmap ->
            imgFoto.setImageBitmap(bitmap)  // 'bitmap' dentro do 'let' não é nulo.

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
                                    // Trate o caso onde url é nulo.
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

    // evento (colocar no 'onClick' do botão)
    fun capturarFoto(view: View) {
        // Chama a função 'exibirToast' para exibir um Toast
        exibirToast(this, "Clicou!!!!")
        register.launch(null) // disparar o evento registrado
    }
}

// Função para exibir um Toast na tela
fun exibirToast(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}