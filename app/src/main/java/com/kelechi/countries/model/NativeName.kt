package com.kelechi.countries

import com.google.gson.annotations.SerializedName


data class NativeName (

  @SerializedName("eng" ) var eng : Eng? = Eng()

)