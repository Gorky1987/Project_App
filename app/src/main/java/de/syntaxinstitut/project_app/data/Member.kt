package de.syntaxinstitut.project_app.data

import com.google.firebase.firestore.DocumentId

data class Member(
    @DocumentId
    val id: String = "",
    val name: String = "",
)


