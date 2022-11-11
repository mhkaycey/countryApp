package com.kelechi.countries.model

import com.google.gson.annotations.SerializedName


data class BBD (

  @SerializedName("name"   ) var name   : String? = null,
  @SerializedName("symbol" ) var symbol : String? = null

)