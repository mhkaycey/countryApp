package com.kelechi.countries

import com.google.gson.annotations.SerializedName


data class Pol (

  @SerializedName("official" ) var official : String? = null,
  @SerializedName("common"   ) var common   : String? = null

)