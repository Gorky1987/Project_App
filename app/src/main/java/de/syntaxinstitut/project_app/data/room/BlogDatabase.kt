package de.syntaxinstitut.project_app.data.room

import android.content.Context
import androidx.room.*
import de.syntaxinstitut.project_app.data.datamodels.Blog

@Database(entities = [Blog::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {

    abstract val blogDatabaseDao: BlogDatabaseDao
}


// Singelton Pattern
private lateinit var INSTANCE: BlogDatabase

// falls es keine gibt wird sie gebaut
fun getDatabase(context: Context): BlogDatabase {
    synchronized(BlogDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BlogDatabase::class.java,
                "blog_database"
            )
                .build()
        }
    }
    return INSTANCE

}