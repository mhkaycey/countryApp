package com.kelechi.countries

import com.google.gson.annotations.SerializedName


data class Tur (

  @SerializedName("official" ) var official : String? = null,
  @SerializedName("common"   ) var common   : String? = null

)