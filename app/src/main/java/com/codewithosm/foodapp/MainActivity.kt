package com.codewithosm.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithosm.foodapp.databinding.ActivityMainBinding
import com.codewithosm.foodapp.adapter.ResturantAdapter
import com.codewithosm.foodapp.mdel.ResturantResponseItem
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import java.util.*

class MainActivity : AppCompatActivity(),ResturantAdapter.Action{

   private lateinit var binding: ActivityMainBinding
   private lateinit var resturantAdapter: ResturantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        val datePicker = findViewById<DatePicker>(R.id.datePicker1)
//        val today = Calendar.getInstance()
//        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
//            today.get(Calendar.DAY_OF_MONTH)
//
//        ) { view, year, month, day ->
//            val month = month + 1
//            val msg = "You Selected: $day/$month/$year"
//            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
//        }
        initiRecylerview()
        getResturant()

    }


    private fun initiRecylerview(){
        resturantAdapter=ResturantAdapter(this)
        binding.rvResturant.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=resturantAdapter
            setHasFixedSize(true)
        }
        resturantAdapter.submitList(getResturant())
    }


    private fun getResturant():List<ResturantResponseItem>{

        val inputStream=resources.openRawResource(R.raw.resturant)
        val writer=StringWriter()
        val buffer= CharArray(1024)
        try {

            val reader=BufferedReader(InputStreamReader(inputStream,"UTF-8"))
            var n:Int
            while (reader.read(buffer).also { n=it }!=-1)
                writer.write(buffer,0,n)

        }catch (e:Exception){ }
        val jsonstr=writer.toString()
        val gson = Gson()
        val resturantModel=gson.fromJson<Array<ResturantResponseItem>>(jsonstr,Array<ResturantResponseItem>::class.java)
        return resturantModel.toList()

    }

    override fun onItemClick(responseItem: ResturantResponseItem) {
        val intent=Intent(this,ResturantActivity::class.java)
        intent.putExtra("resturantmodel",responseItem)
        startActivity(intent)
    }

}