package com.pokemon.data.model.api.webhook

import com.google.gson.annotations.SerializedName

data class WebHookResponse (
    @SerializedName("sv_status")
    val svStatus: Boolean
)
