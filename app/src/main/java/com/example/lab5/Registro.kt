package com.example.lab5

import android.app.Application

class Registro : Application(){
    companion object{var globalInventario: ArrayList<ListaDeProductos> = arrayListOf() }
}