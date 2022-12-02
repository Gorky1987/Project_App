package de.syntaxinstitut.project_app.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.syntaxinstitut.project_app.data.datamodels.Blog


@Dao
interface BlogDatabaseDao  {


    @Insert(onConflict = OnConflictStrategy.REPLACE) // in SQL INSERT
    suspend fun insert(blog: Blog)

    @Update // in SQL UPDATE
    suspend fun update(blog: Blog)

    @Query("SELECT * FROM Blog")
    fun getAll(): LiveData<List<Blog>>

    @Query("SELECT * FROM Blog WHERE id = :id")
    fun getById(id: Int): Blog

    @Query("DELETE FROM Blog WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE from Blog")
    suspend fun deleteAll()

   @Query("SELECT CASE WHEN EXISTS(SELECT 1 FROM Blog) THEN 0 ELSE 1 END")
   suspend fun isEmpty (): Boolean
}