package com.example.request_http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity(), CompletadoListener {

    override fun descargaCompleta(resultado:String){
        Log.d("descargaCompleta", resultado)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bValidarRed = findViewById<Button>(R.id.bValidarRed)
        val bSolicitud = findViewById<Button>(R.id.bSolicitud)
        val bVolley = findViewById<Button>(R.id.bVolley)
        val bOk = findViewById<Button>(R.id.bOk)

        bValidarRed.setOnClickListener{
            // Código para validar red
            if(Network.hayRed(this)){
                Toast.makeText(this, "Hay red", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No hay red", Toast.LENGTH_LONG).show()
            }
        }

        bSolicitud.setOnClickListener{
            if(Network.hayRed(this)){
                DescargaURL(this).execute("http://www.google.com")
            }else{
                Toast.makeText(this, "No hay red", Toast.LENGTH_LONG).show()
            }
        }

        bVolley.setOnClickListener{
            if(Network.hayRed(this)){
                solicitudHTTPVolley("http://www.google.com")
            }else{
                Toast.makeText(this, "No hay red", Toast.LENGTH_LONG).show()
            }
        }

        bVolley.setOnClickListener{
            if(Network.hayRed(this)){
            }else{
            }
        }
    }

    //Método para Volley
    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try{
                Log.d("solicitudHTTPVolley", response)
            }catch(e: Exception){

            }
        }, Response.ErrorListener{ })

        queue.add(solicitud)
    }
}
