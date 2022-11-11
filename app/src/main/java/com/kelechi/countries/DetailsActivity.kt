package com.kelechi.countries

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.kelechi.countries.model.CoatOfArms
import com.kelechi.countries.model.ModelData
import com.kelechi.countries.retrofit.ApiInterface
import com.kelechi.countries.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.*

class DetailsActivity : AppCompatActivity() {
    lateinit var  tvCom : TextView
    lateinit var Tpopulation : TextView
    lateinit var baseName: String
    lateinit var coatOfArms: String
   // lateinit var subregion: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        Objects.requireNonNull(supportActionBar)?.hide()

        tvCom = findViewById(R.id.tvCommon)
        Tpopulation = findViewById(R.id.tvPopulation)
        val subregion: TextView = findViewById(R.id.tvEthnic)
        val Tregion: TextView = findViewById(R.id.tvRegion)
        val tvCapital: TextView = findViewById(R.id.tvCapital)
        val tvLanguages: TextView = findViewById(R.id.tvLanguage)
        val tvIndependent: TextView = findViewById(R.id.tvIndependent)
        val tvArea: TextView = findViewById(R.id.tvArea)
        val tvTimeZone: TextView = findViewById(R.id.tvTimeZone)
        val tvCurrency: TextView = findViewById(R.id.tvCurrency)
        val tvDialing: TextView = findViewById(R.id.tvDialing)
        val tvDriving: TextView = findViewById(R.id.tvDriving)

        val bundle: Bundle? = intent.extras
        val name = bundle?.getString("name")
        val population = bundle!!.getInt("population").toString()
        val subregion1  = bundle!!.getString("subregion")
        val region = bundle.getString("region")
        val capital = bundle.getStringArrayList("capital").toString()
        val  languages = bundle.getString("language?" )
        val independent = bundle.getBoolean("independent").toString()
        val area = bundle.getDouble("area").toString()
        val currency = bundle.getString("currency")
        val  timezone = bundle.getIntegerArrayList("timeZone").toString()
        val dialing = bundle.getString("dialing")
        val drivingside = bundle.getString("driving")
        val flag1 = bundle.getString("flag").toString()
        val coat1 = bundle.getString("coat").toString()



        baseName = flag1
        coatOfArms = coat1
        tvCom.text = flag1
        subregion.text = subregion1
        Tpopulation.text = population
        Tregion.text = region
        tvCapital.text = capital
        tvLanguages.text = languages
        tvIndependent.text = independent
        tvArea.text = area
        tvCurrency.text = currency
        tvTimeZone.text = timezone
        tvDialing.text = dialing
        tvDriving.text = drivingside

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("${baseName}", "flag"))
        imageList.add(SlideModel("${coatOfArms}"))
        imageList.add(SlideModel("$baseName", "flag"))
//        imageList.add(SlideModel("https://images.immediate.co.uk/production/volatile/sites/3/2019/04/Avengers-Endgame-Banner-2-de7cf60.jpg?quality=90&resize=620,413","Avengers Endgame"))
//        imageList.add(SlideModel("https://img.cinemablend.com/filter:scale/quill/3/7/0/0/8/e/37008e36e98cd75101cf1347396eac8534871a19.jpg?mw=600","Jumanji"))
//        imageList.add(SlideModel("https://www.adgully.com/img/800/201711/spider-man-homecoming-banner.jpg","Spider Man"))
//        imageList.add(SlideModel("https://live.staticflickr.com/1980/29996141587_7886795726_b.jpg","Venom"))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)



//        baseName = tvCom.toString().trim()
//
//       getDetail(baseName)

    }

//    fun getDetail(common: String){
//        val retrofitClient = RetrofitClient.getInstance()?.create(ApiInterface::class.java)
//        retrofitClient?.getDetails(common)?.enqueue(object : Callback<ModelData>{
//            override fun onResponse(call: Call<ModelData>, response: Response<ModelData>) {
//
//                if (response.isSuccessful) {
//
//                    val population: Int? = response.body()?.population
//                    tvPopulation.text = population.toString()
//                }
//            }
//            override fun onFailure(call: Call<ModelData>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//    }

//    private fun getDetails(common: String) {
//        val retrofitClient = RetrofitClient.getInstance()!!.create(ApiInterface::class.java)
//        retrofitClient.getDetails(common).enqueue(object : Callback<ModelData>{
//            override fun onResponse(
//                call: Call<ModelData>,
//                response: Response<ModelData>
//            ) {
//                if (response.isSuccessful){
//
////                    val population: Int? = response.body()?.population
////                    tvPopulation.text = population.toString()
////                    val items = response.body()
////                    if (items != null){
////                        for (i in 0 until items.count()){
////                          val   population = items[i].population ?: "N/A"
////                                tvPopulation.text = population.toString()
////                             Log.e("Popular", population as String)
////                        }
////                    }
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<ModelData>, t: Throwable) {
//                t.printStackTrace()
//                Log.e("Details", t.toString())
//
//                Toast.makeText(this@DetailsActivity, t.toString(), Toast.LENGTH_LONG).show()
//
//            }
//
//        })
//
//    }
}