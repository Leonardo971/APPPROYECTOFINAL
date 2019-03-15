package com.example.leona.appproyecto

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leona.appproyecto.database.VisitasEntry
import kotlinx.android.synthetic.main.encuesta_list_item.view.*
import kotlinx.android.synthetic.main.visita_list_item.view.*

class    VisitaAdapter (private  var mEmpleadoEntries:List<VisitasEntry>, private val mContext: Context, private val clikckListener:(VisitasEntry)-> Unit)
    : RecyclerView.Adapter<VisitaAdapter.EmpleadoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):EmpleadoViewHolder{
        val layoutInflater= LayoutInflater.from(mContext)
        return EmpleadoViewHolder(layoutInflater.inflate(R.layout.visita_list_item,parent,false))
    }


    override fun onBindViewHolder(holder: EmpleadoViewHolder, position: Int) {
        holder.bind(mEmpleadoEntries[position], mContext, clikckListener)
    }


    override fun getItemCount(): Int = mEmpleadoEntries.size


    fun setEmpleado(contactoEntries: List<VisitasEntry>){
        mEmpleadoEntries = contactoEntries
        notifyDataSetChanged()
    }

    fun getTasks(): List<VisitasEntry> = mEmpleadoEntries


    class EmpleadoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind (contact:VisitasEntry, context: Context, clickListener: (VisitasEntry) -> Unit){
            //Asigna los valores a los elementos delcontacto_list_item
            itemView.tvNombre.text = contact.nombre
            itemView.tvTelefono.text = contact.direccion

            itemView.setOnClickListener{ clickListener(contact)}
        }
    }

}

