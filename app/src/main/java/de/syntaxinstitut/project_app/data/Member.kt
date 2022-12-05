package de.syntaxinstitut.project_app.data

import com.google.firebase.firestore.DocumentId

data class Member(
    @DocumentId
    val id: String = "",
    var name: String = "",
    var image: String = "",
    var birthday: String = "",
    var hometown: String = "",
    var bio : String = ""


)



