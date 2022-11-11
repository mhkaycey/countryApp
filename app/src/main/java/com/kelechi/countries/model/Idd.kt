package com.kelechi.countries

import com.google.gson.annotations.SerializedName


data class Idd (

  @SerializedName("root"     ) var root     : String?           = null,
  @SerializedName("suffixes" ) var suffixes : ArrayList<String> = arrayListOf()

)