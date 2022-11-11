package com.kelechi.countries.model

import com.google.gson.annotations.SerializedName


data class Car (

  @SerializedName("signs" ) var signs : ArrayList<String> = arrayListOf(),
  @SerializedName("side"  ) var side  : String?           = null

)