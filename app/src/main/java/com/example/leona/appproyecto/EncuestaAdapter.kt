package com.example.leona.appproyecto

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leona.appproyecto.database.EncuestaEntry
import com.example.leona.appproyecto.database.VisitasEntry
import kotlinx.android.synthetic.main.encuesta_list_item.view.*
import kotlinx.android.synthetic.main.visita_list_item.view.*

class EncuestaAdapter (private  var mEncuestaEntrys:List<EncuestaEntry>, private val mContext: Context, private val clikckListener:(EncuestaEntry)-> Unit)
    : RecyclerView.Adapter<EncuestaAdapter.EncuestaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):EncuestaViewHolder{
        val layoutInflater= LayoutInflater.from(mContext)
        return EncuestaViewHolder(layoutInflater.inflate(R.layout.activity_main2_lista_encu,parent,false))
    }


    override fun onBindViewHolder(holder: EncuestaViewHolder, position: Int) {
        holder.bind(mEncuestaEntrys[position], mContext, clikckListener)
    }


    override fun getItemCount(): Int = mEncuestaEntrys.size


    fun setEncuesta(encuestaEntries: List<EncuestaEntry>){
        mEncuestaEntrys = encuestaEntries
        notifyDataSetChanged()
    }

    fun getTasks(): List<EncuestaEntry> = mEncuestaEntrys


    class EncuestaViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind (campo:EncuestaEntry, context: Context, clickListener: (EncuestaEntry) -> Unit){
            //Asigna los valores a los elementos delcontacto_list_item
            itemView.tvEncuesta.text = campo.nombre

            itemView.tvClave.text = campo.claveadult.toString()

            itemView.setOnClickListener{ clickListener(campo)}
        }
    }

}
