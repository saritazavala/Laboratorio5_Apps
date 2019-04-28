package com.example.lab5

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class ContadorDeProductos(var listaProductos1: ArrayList<ListaDeProductos>):RecyclerView.Adapter<ContadorDeProductos.ViewHolder>(){
    override fun onCreateViewHolder(x: ViewGroup, p1: Int): ViewHolder {
        var layout1 = LayoutInflater.from(x.context).inflate(R.layout.item,x,false)
        return ViewHolder(layout1)
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        fun bindItem(data:ListaDeProductos){
            val cantidad: TextView = itemView.findViewById(R.id.cantidad)
            val nombre: TextView = itemView.findViewById(R.id.nombre)
            cantidad.text = data.cantidad.toString()
            nombre.text = data.producto.nombre
            val Agregarmenos: Button = itemView.findViewById(R.id.menos)
            Agregarmenos.setOnClickListener(){
                if(data.cantidad!=0){
                    val num = data.cantidad-1
                    data.cantidad = num
                    cantidad.text = num.toString() } }

            val Agregarmas: Button = itemView.findViewById(R.id.mas)
            Agregarmas.setOnClickListener(){
                val contador = data.cantidad + 1
                data.cantidad = contador
                cantidad.text = contador.toString()
            }
        }
    }
    override fun getItemCount(): Int {
        return listaProductos1.size
    }
    override fun onBindViewHolder(x: ContadorDeProductos.ViewHolder, i: Int) {
        x.bindItem(listaProductos1[i])
    }
}