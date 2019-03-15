package com.example.leona.appproyecto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.leona.appproyecto.database.AppDatabase
import com.example.leona.appproyecto.database.VisitasEntry
import com.example.leona.appproyecto.helper.doAsync
import kotlinx.android.synthetic.main.activity_main_lista.*

class MainActivityLista : AppCompatActivity() {

    private lateinit var viewAdapter:VisitaAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    val empleadoList:List<VisitasEntry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lista)

        viewManager = LinearLayoutManager(this)
        viewAdapter = VisitaAdapter(empleadoList,this,{ empleado: VisitasEntry ->  onItemClickListener(empleado)})


        recyclerViewEmp.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivityLista, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        retrieveEmpleados()
    }


    private fun retrieveEmpleados(){
        doAsync{
            val empleados = AppDatabase.getInstance(this@MainActivityLista)?.visitasDao()?.loadAllVisitas()
            runOnUiThread{
                viewAdapter.setEmpleado(empleados!!)
            }
        }.execute()
    }


    private fun onItemClickListener(empleado:VisitasEntry){
        // Launch AddTaskActivity adding the itemId as an extra in the intent
        val intent = Intent(this,Main2ActivityEncuesta::class.java)
        //intent.putExtra(MainActivityDetalle.EXTRA_CONTACTO_ID, contacto.id)
        val nom=empleado.nombre
        val cla=empleado.clave.toString()
        val apelli=empleado.apellido
        intent.putExtra("Nombre",nom)
        intent.putExtra("Clave",cla)
        intent.putExtra("Apellido",apelli)
        startActivity(intent)
        //Toast.makeText(this, "Clicked item" + task.description, Toast.LENGTH_LONG).show()
    }
}
