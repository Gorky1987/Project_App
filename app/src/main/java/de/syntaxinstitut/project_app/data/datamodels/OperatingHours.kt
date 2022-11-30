package de.syntaxinstitut.project_app.data.datamodels

data class OperatingHours(

    val mittwoch: String = "",
    val donnerstag: String = "",
    val freitag: String = "",
    val samstag: String = "",
    val sonntag: String = "",
    val montag: String = "",
    val dienstag: String = "",
) {
    override fun toString(): String {

        return """
            Öffnungszeiten: 
            
           Montag: $montag
           Dienstag: $dienstag
           Mittwoch: $mittwoch
           Donnerstag: $donnerstag
           Freitag: $freitag 
           Samstag: $samstag
           Sonntag: $sonntag


        """.trimIndent()

    }
}
