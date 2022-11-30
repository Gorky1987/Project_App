package de.syntaxinstitut.project_app.data.datamodels


import com.squareup.moshi.Json

data class GymSearchList(
    @Json(name = "local_results")
    val searchList : List<GymSearch>

)
