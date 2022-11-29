package de.syntaxinstitut.project_app.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Blog (
    @PrimaryKey

    var id : Int,
    var blog_number: String,
    var titel : String,
    var subTitel : String,
    var content_titel : String,
    var content : String,
    var titel_Image : Int,

        )
{



}