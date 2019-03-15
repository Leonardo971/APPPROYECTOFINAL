package com.example.leona.appproyecto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.leona.appproyecto.R.id.*
import com.example.leona.appproyecto.database.AppDatabase
import com.example.leona.appproyecto.database.EncuestaEntry
import com.example.leona.appproyecto.helper.doAsync
import kotlinx.android.synthetic.main.activity_main2_encuesta.*

class Main2ActivityEncuesta : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_encuesta)

        val Obintent: Intent =intent
        var nombre =Obintent.getStringExtra("Nombre")
        var clavle=Obintent.getStringExtra("Clave")
        var apellido=Obintent.getStringExtra("Apellido")
        lblNombreEncu.text="$nombre  $apellido"
        lblClave.text=clavle

    }

    fun btnGuardarEncu(v:View)
    {
        val respuesta1=editTextpregunta1.text.toString()
        val respuesta2=editTextpregunta2.text.toString()
        val respuesta3=editTextpregunta3.text.toString()
        val respuesta4=editTextpregunta4.text.toString()
        val respuesta5=editTextpregunta5.text.toString()
        val respuesta6=editTextpregunta6.text.toString()
        val cla=lblClave.text.toString()
        val adul=cla.toLong()
        val nom=lblNombreEncu.text.toString()
        Log.d("Barcenas",respuesta1)
        Log.d("Barcenas",respuesta2)
        Log.d("Barcenas",respuesta3)
        Log.d("Barcenas",respuesta4)
        Log.d("Barcenas",respuesta5)
        Log.d("Barcenas",respuesta6)
        Log.d("Barcenas",adul.toString())
        Log.d("Barcenas",nom)
        val vis:EncuestaEntry= EncuestaEntry(uno = respuesta1,dos = respuesta2 ,tres = respuesta3 ,cuatro = respuesta4 ,cinco = respuesta5 ,seis = respuesta6,claveadult = adul,nombre = nom)
        doAsync{
            AppDatabase.getInstance(this@Main2ActivityEncuesta)!!.encuestaDao().insertEncuesta(vis)
            runOnUiThread {
                limpia()


            }
        }.execute()


    }


        fun limpia()
        {
            editTextpregunta1.setText("")
            editTextpregunta2.setText("")
            editTextpregunta3.setText("")
            editTextpregunta4.setText("")
            editTextpregunta5.setText("")
            editTextpregunta6.setText("")
        }



    fun btnMostar(v: View)
    {
        val id=lblClave.text.toString().toLong()

        doAsync{
          val res=  AppDatabase.getInstance(this@Main2ActivityEncuesta)!!.encuestaDao().loadEncuestaByClave(id)
            runOnUiThread {
               if (res==null)
               {
                   Toast.makeText(this,"No se le ah realizado la encuesta",Toast.LENGTH_SHORT).show()
               }
                else
                {
                    llenacampos(res!!)
                }


            }
        }.execute()



    }

    fun llenacampos(encuesta:EncuestaEntry)
    {
        if (encuesta==null) return

        editTextpregunta1.setText(encuesta.uno)
        editTextpregunta2.setText(encuesta.dos)
        editTextpregunta3.setText(encuesta.tres)
        editTextpregunta4.setText(encuesta.cuatro)
        editTextpregunta5.setText(encuesta.cinco)
        editTextpregunta6.setText(encuesta.seis)
    }


}
