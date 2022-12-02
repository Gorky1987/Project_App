package de.syntaxinstitut.project_app.data.datamodels

data class GymSearch(

    val title :String = "",
    val rating : Double = 0.0,
    val address :String = "",
    val operating_hours : OperatingHours = OperatingHours(),
    val thumbnail :String = "",
    val phone :String = ""

)
