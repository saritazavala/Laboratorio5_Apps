package com.example.lab5


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.lab5.databinding.ListaFragmentBinding
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.lista_fragment.*
import android.support.annotation.NonNull
import android.support.v4.app.FragmentManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.fuente_fragment.*

class fLista : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Se crea el DataBiding para el fragment
        val binding: ListaFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.lista_fragment, container, false)
        var registros = Registro.globalInventario
        binding.listaItems.layoutManager=LinearLayoutManager(context,LinearLayout.VERTICAL,false)
        //Se crea el contador de nuestros productos
        val cont = ContadorDeProductos(registros)
        binding.listaItems.adapter = cont
        setHasOptionsMenu(true)

        //Borrar deslizando
        //Este codigo se tomo de Internet
        //https://www.flipandroid.com/cmo-hacer-deslizar-para-eliminar-cardview-en-android-utilizando-la-biblioteca-de-soporte.html
        //https://codigofacilito.com/videos/43-eliminar-elemento-al-recyclerview
        var paso = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, @NonNull viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(@NonNull target: RecyclerView.ViewHolder, p1:Int){
                val position = target.getAdapterPosition()
                registros.removeAt(position)
                binding.listaItems.adapter?.notifyDataSetChanged()
            }
        })
        paso.attachToRecyclerView(binding.listaItems)

        binding.boton.setOnClickListener(){
            val input = IntentIntegrator(activity)
            input.initiateScan()
        }
        return binding.root
    }
    //Esto es para el QR sacado del git ejemplo que se nos dio
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(context, "No se encuntra", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Escaneado" + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
    //Se crea el mennu de opciones
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflowmenu,menu)
    }

        // Lo que pasa al seleccionar cada opcion del menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId!!.equals(R.id.reiniciar)){
            var lista = Registro.globalInventario
            lista.clear()
            listaItems.adapter?.notifyDataSetChanged()
        }
        if(item.itemId.equals(R.id.actual)){
            Toast.makeText(context, "Cantidad de Items" , Toast.LENGTH_LONG).show()
        }

        if(item.itemId.equals((R.id.ProductoNuevo))){


        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)

    }


}
