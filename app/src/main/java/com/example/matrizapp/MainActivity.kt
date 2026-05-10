package com.example.matrizapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTamano: EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTamano = findViewById(R.id.etTamano)
        tvResultado = findViewById(R.id.tvResultado)

        val btnTriangularSuperior: Button = findViewById(R.id.btnTriangularSuperior)
        val btnMatrizNula: Button = findViewById(R.id.btnMatrizNula)
        val btnLimpiar: Button = findViewById(R.id.btnLimpiar)

        btnTriangularSuperior.setOnClickListener {
            val tamano = obtenerTamanoValido() ?: return@setOnClickListener
            val matrizTriangular = generarMatrizTriangularSuperior(tamano)
            tvResultado.text = convertirMatrizATexto(matrizTriangular)
        }

        btnMatrizNula.setOnClickListener {
            val tamano = obtenerTamanoValido() ?: return@setOnClickListener
            val matrizNula = generarMatrizNula(tamano)
            tvResultado.text = convertirMatrizATexto(matrizNula)
        }

        btnLimpiar.setOnClickListener {
            etTamano.text.clear()
            tvResultado.text = "Resultado"
        }
    }

    // Valida que el tamaño sea un número mayor que cero.
    private fun obtenerTamanoValido(): Int? {
        val textoTamano = etTamano.text.toString().trim()

        if (textoTamano.isEmpty()) {
            Toast.makeText(this, "Ingrese el tamaño de la matriz", Toast.LENGTH_SHORT).show()
            return null
        }

        val tamano = textoTamano.toIntOrNull()
        if (tamano == null || tamano <= 0) {
            Toast.makeText(this, "Ingrese un número válido mayor que cero", Toast.LENGTH_SHORT).show()
            return null
        }

        return tamano
    }

    // Crea una matriz triangular superior usando dos ciclos anidados.
    private fun generarMatrizTriangularSuperior(tamano: Int): Array<IntArray> {
        val matriz = Array(tamano) { IntArray(tamano) }

        for (i in 0 until tamano) {
            for (j in 0 until tamano) {
                matriz[i][j] = if (j >= i) {
                    i * tamano + j + 1
                } else {
                    0
                }
            }
        }

        return matriz
    }

    // Crea una matriz nula: todos los elementos son cero.
    private fun generarMatrizNula(tamano: Int): Array<IntArray> {
        return Array(tamano) { IntArray(tamano) }
    }

    // Convierte la matriz en texto alineado para mostrarla en pantalla.
    private fun convertirMatrizATexto(matriz: Array<IntArray>): String {
        val resultado = StringBuilder()

        for (fila in matriz) {
            for (valor in fila) {
                resultado.append(String.format("%4d", valor))
            }
            resultado.append("\n")
        }

        return resultado.toString()
    }
}
