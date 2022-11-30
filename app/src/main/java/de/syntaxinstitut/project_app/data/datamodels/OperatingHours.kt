package de.syntaxinstitut.project_app.data.datamodels

data class OperatingHours(

    val mittwoch : String  = "",
    val donnerstag : String  = "",
) {
    override fun toString ():String{

       return """
            Öffnungszeiten: 
            
           Mittwoch: $mittwoch
           Donnerstag: $donnerstag
           
           
        """.trimIndent()

    }
}
