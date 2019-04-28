package com.example.lab5


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lab5.databinding.FuenteFragmentBinding


class fuente : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Se crea el databinding para nuevos productos
        val binding: FuenteFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fuente_fragment, container, false)
        binding.anadir.setOnClickListener(){
         view:View->
         val nombre = binding.iN.text.toString()
         val codigo = binding.iC.text.toString()
         val guardados = Registro.globalInventario
         val entero = producto(nombre,codigo)
         val productosTotales = ListaDeProductos(entero,0)
         guardados.add(productosTotales)
         Navigation.findNavController(view).navigate(R.id.action_fuente_to_fragmentoLista)
        }
        return binding.root
    }

}
