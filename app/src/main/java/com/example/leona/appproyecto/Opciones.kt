package com.example.leona.appproyecto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_opciones.*

class Opciones : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        val Obintent: Intent=intent
        val nomemple=Obintent.getStringExtra("Nombre")

            lblnombremp.text=nomemple

    }


        fun listavisitas(v:View)
        {
            val int=Intent(this@Opciones,MainActivityLista::class.java)
            startActivity(int)
        }


        fun listaEncuesta(v:View)
        {
            val intent=Intent(this@Opciones,Main2ActivityListaEncu::class.java)
            startActivity(intent)
        }
}
