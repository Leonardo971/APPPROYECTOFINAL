package com.example.leona.appproyecto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.leona.appproyecto.database.AppDatabase
import com.example.leona.appproyecto.database.EncuestaEntry
import com.example.leona.appproyecto.database.VisitasEntry
import com.example.leona.appproyecto.helper.doAsync
import kotlinx.android.synthetic.main.activity_main2_lista_encu.*
import kotlinx.android.synthetic.main.activity_main_lista.*

class Main2ActivityListaEncu : AppCompatActivity() {


    private lateinit var viewAdapter:EncuestaAdapter
    private lateinit var viewManager:RecyclerView.LayoutManager
    val encuestaList:List<EncuestaEntry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_lista_encu)

        viewManager = LinearLayoutManager(this)
        viewAdapter = EncuestaAdapter(encuestaList,this,{ encuesta: EncuestaEntry ->  onItemClickListener(encuesta)})


        recyclerViewEncu.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@Main2ActivityListaEncu, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        retrieveEncuestados()
    }


    private fun retrieveEncuestados(){
        doAsync{
            val encuestados = AppDatabase.getInstance(this@Main2ActivityListaEncu)?.encuestaDao()?.loadAllEncuestas()
            runOnUiThread{
                viewAdapter.setEncuesta(encuestados!!)
            }
        }.execute()
    }


    private fun onItemClickListener(encuestados: EncuestaEntry){
        // Launch AddTaskActivity adding the itemId as an extra in the intent
        //val intent = Intent(this,Main2ActivityEncuesta::class.java)
        //intent.putExtra(MainActivityDetalle.EXTRA_CONTACTO_ID, contacto.id)
        //val nom=empleado.nombre
        //val cla=empleado.clave.toString()
        //val apelli=empleado.apellido
        //intent.putExtra("Nombre",nom)
        //intent.putExtra("Clave",cla)
        //intent.putExtra("Apellido",apelli)
        //startActivity(intent)
        //Toast.makeText(this, "Clicked item" + task.description, Toast.LENGTH_LONG).show()
    }
}
