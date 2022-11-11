package com.kelechi.countries

import com.google.gson.annotations.SerializedName


data class Maps (

  @SerializedName("googleMaps"     ) var googleMaps     : String? = null,
  @SerializedName("openStreetMaps" ) var openStreetMaps : String? = null

)