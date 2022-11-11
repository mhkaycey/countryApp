package com.kelechi.countries.model

import com.google.gson.annotations.SerializedName


data class Flags (

  @SerializedName("png" ) var png : String? = null,
  @SerializedName("svg" ) var svg : String? = null

)