package com.example.laboratorio2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),contadorListener {

    var frac1:BlankFragment?=null
    var frac2:BlankFragment2?=null
    var frac3:BlankFragment3?=null

    var btnMain1:Button?=null
    var btnMain2:Button?=null
    var btnMain9:Button?=null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       var itemId=item.itemId
        if (itemId==R.id.itemGuardar){
            Toast.makeText(this@MainActivity,"hizo clic en ajustes",Toast.LENGTH_SHORT).show();
        return  true
        }
        else if (itemId==R.id.itemAjustes){
            Toast.makeText(this@MainActivity,"Hizo click en ajustes",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infla=menuInflater
        infla.inflate(R.menu.menutoolbar,menu)

        val item=menu?.findItem(R.id.buscarmenuitem)


        val sv = item?.actionView as android.widget.SearchView?
        sv?.setOnQueryTextListener( object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                val activity = sv.context as MainActivity
                Toast.makeText(activity,newText,Toast.LENGTH_SHORT).show()

                return true
            }


            override fun onQueryTextSubmit(query: String?): Boolean {
                val activity = sv.context as MainActivity
                Toast.makeText(activity,"Buscar: "+query,Toast.LENGTH_SHORT).show()
                return true
            }

        })

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.includeToolbar)

        setSupportActionBar(toolbar)






        frac1=BlankFragment()
        frac1?.addContadorListener(this)
        frac2= BlankFragment2()
        frac2?.addListener(this)
        frac3= BlankFragment3()
        frac3?.reducirContadorListener(this)
        btnMain1=findViewById(R.id.button3)
        btnMain2=findViewById(R.id.button4)
        btnMain9=findViewById(R.id.button9)


        btnMain1?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Abriendo fragmento 1", Toast.LENGTH_SHORT).show()
            val transaction =getSupportFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainer,frac1!!)
            transaction.commit()
        })

        btnMain2?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Abriendo fragmento 2", Toast.LENGTH_SHORT).show()
            val transaction =getSupportFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainer,frac2!!)
            transaction.commit()
        })

        btnMain9?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Abriendo fragmento 3", Toast.LENGTH_SHORT).show()
            val transaction =getSupportFragmentManager().beginTransaction()
            transaction.replace(R.id.fragmentContainer,frac3!!)
            transaction.commit()
        })

    }
var cont=0
    override fun iincremetar() {

        cont++
    }

    override fun getValorActual(): Int {

        return cont
    }

    override fun resetear() {
       cont=0
    }

    override fun reducir() {
        if (cont>0) cont--
        else cont=0
    }
}

