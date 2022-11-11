package com.kelechi.countries

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kelechi.countries.adapter.myAdapter
import com.kelechi.countries.model.ModelData
import com.kelechi.countries.retrofit.ApiInterface
import com.kelechi.countries.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

lateinit var putIn: List<ModelData>

    var ivFilter: ImageView? = null
    lateinit var rvCountry: RecyclerView
    //new Add

    var adapter: myAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Objects.requireNonNull(supportActionBar)?.hide()

        val EditText: EditText = findViewById(R.id.search)
        ivFilter = findViewById(R.id.ivFilter)
        rvCountry = findViewById(R.id.rvCountries)
        rvCountry.layoutManager = LinearLayoutManager(this)




        val retrofitClient = RetrofitClient.getInstance()!!.create(ApiInterface::class.java)

        retrofitClient.getData().enqueue(object : Callback<List<ModelData>>{
            override fun onResponse(
                call: Call<List<ModelData>>,
                response: Response<List<ModelData>>
            ) {


                val modelData = response.body()!!
                rvCountry.layoutManager = LinearLayoutManager(this@MainActivity)
             //   var    adapter = myAdapter(response.body()!!.sortedBy { it.name?.official.toString() })
           //  val adapter = myAdapter(response.body()!!)
                adapter = myAdapter(response.body()!!)

                rvCountry.adapter = adapter

              //  rvCountry.adapter= myAdapter(response.body()!!)

                adapter!!.setOnitemClickListener(object : myAdapter.onItemClickLister{
                    override fun onItemClick(position: Int) {

                     // Toast.makeText(this@MainActivity, "You clicked on, $position", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                        intent.putExtra("name", modelData[position].name?.common)
                        intent.putExtra("population", modelData[position].population)
                        intent.putExtra("region", modelData[position].region)
                        intent.putExtra("capital", modelData[position].capital)
                      //  intent.putExtra("Motto", modelData[position].m)
                        intent.putExtra("language", modelData[position].languages!!.eng)
                        intent.putExtra("subregion", modelData[position].subregion)
                        //intent.putExtra("Government", modelData[position].)
                        intent.putExtra("independent", modelData[position].independent)
                        intent.putExtra("area", modelData[position].area)
                        intent.putExtra("currency", modelData[position].currencies?.BBD?.symbol)
                      //  intent.putExtra("GDP", modelData[position].)
                        intent.putExtra("timeZone", modelData[position].timezones)
                        //intent.putExtra("Date", modelData[position].)
                        intent.putExtra("dialing", modelData[position].idd?.suffixes)
                        intent.putExtra("driving", modelData[position].car?.side)
                        intent.putExtra("flag", modelData[position].flags?.png)
                        intent.putExtra("coat", modelData[position].coatOfArms?.png)
                        startActivity(intent)
                    }

                })

            }

            override fun onFailure(call: Call<List<ModelData>>, t: Throwable) {
                    t.printStackTrace()
                Log.e("error", t.toString())
            }

        })



       onClick()




    }




    fun onClick() {


        ivFilter?.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)


            dialog.setCancelable(false)


            dialog.setContentView(view)

            dialog.show()
        }

    }



}









