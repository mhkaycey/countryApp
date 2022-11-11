package com.kelechi.countries

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
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

    //lateinit var dayFilter: ImageView
    lateinit var nightFilter: ImageView
    lateinit var switch: SwitchCompat
    lateinit var logoDay: ImageView
    lateinit var logoNight: ImageView
    lateinit var ivFilter: ImageView
    lateinit var rvCountry: RecyclerView
    //new Add

    var adapter: myAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Objects.requireNonNull(supportActionBar)?.hide()

        nightFilter = findViewById(R.id.ivFilterNight)
        switch = findViewById(R.id.themeSwitch)
        logoDay = findViewById(R.id.ivLogoDay)
        logoNight = findViewById(R.id.ivLogoNight)
        val searchView: SearchView= findViewById(R.id.search)
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
                adapter = myAdapter(response.body()!! as ArrayList<ModelData>)

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


    switch.setOnCheckedChangeListener{_, isChecked ->
        if (switch.isChecked){
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            logoDay.visibility = View.GONE
            logoNight.visibility = View.VISIBLE
            ivFilter.visibility = View.GONE
            nightFilter.visibility = View.VISIBLE
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            logoDay.visibility = View.VISIBLE
            logoNight.visibility = View.GONE
            ivFilter.visibility = View.VISIBLE
            nightFilter.visibility = View.GONE

        }
    }




       onClick()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText);
                return true
            }

        })



    }




    fun onClick() {



        ivFilter?.setOnClickListener {
            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)


            dialog.setCancelable(true)


            dialog.setContentView(view)

            dialog.show()
            val ivClose: ImageView = view.findViewById(R.id.ivClose)
            ivClose.setOnClickListener {
                dialog.dismiss()
            }
        }

    }




}









