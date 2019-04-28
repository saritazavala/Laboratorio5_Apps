package com.example.lab5

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

/**
 *
 * @author Sara Zavala 18893
 * Laboratorio 5
 *
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var r = Registro.globalInventario
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.Principal)
        NavigationUI.setupActionBarWithNavController(this, navController)
        var cajas = producto("Caja de cerveza", "C")
        var primero = ListaDeProductos(cajas, 0)
        r.add(primero)
        var papalinas = producto("Papalinas", "P")
        var segundo = ListaDeProductos(papalinas, 25)
        r.add(segundo)
        var gaseosas = producto("Gaseosa", "G")
        var tercero = ListaDeProductos(gaseosas, 5)
        r.add(tercero)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController((R.id.Principal))
        return navController.navigateUp()
    }
}
