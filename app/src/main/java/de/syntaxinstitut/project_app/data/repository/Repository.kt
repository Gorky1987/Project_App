package de.syntaxinstitut.project_app.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.project_app.R


import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.data.hView_Item
import de.syntaxinstitut.project_app.data.room.BlogDatabase


const val TAG = "Repository"

class Repository(private val database: BlogDatabase) {

    val blogList: LiveData<List<Blog>> = database.blogDatabaseDao.getAll()

    private val _blog= MutableLiveData<Blog>()
    val blog: LiveData<Blog>
        get() = _blog

    suspend fun insert(blog: Blog) {
        try {
            database.blogDatabaseDao.insert(blog)
        } catch (e: Exception) {
            Log.e(TAG, "Error writing data in database: $e")
        }
    }

    fun getBlog(id: Int) {
        try {
            _blog.postValue(database.blogDatabaseDao.getById(id))
        } catch (e: Exception) {
            Log.e(TAG, "Error finding $id in database: $e")
        }
    }

    suspend fun deleteBlog(id: Int) {
        try {
            database.blogDatabaseDao.deleteById(id)
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting $id from database: $e")
        }
    }

    suspend fun updateGuest(blog: Blog) {
        try {
            database.blogDatabaseDao.update(blog)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating guest in database: $e")
        }
    }




    private val _itemList = MutableLiveData<List<hView_Item>>(loadItem())
    val itemList : LiveData<List<hView_Item>>
        get() = _itemList



    fun loadItem(): List<hView_Item> {
        return listOf(
            hView_Item(1,
                "Home",
                "back to Homescreen",
                R.drawable.gymguide_full_logo,
                R.drawable._6_post
            ),
            hView_Item(2,
                "Gym Finder",
                "search your gym...",
                R.drawable.gymguide_full_logo,
                R.drawable._1_gymfinder
            ),
            hView_Item(3,
                "UserProfil",
                "your personal area...",
                R.drawable.gymguide_full_logo,
                R.drawable._2_blog
            ),
            hView_Item(4,
                "Die Unterschiede",
                "the differences between the gym...",
                R.drawable.gymguide_full_logo,
                R.drawable._3_unterschiede
            ),
            hView_Item(5,
                "Seniorengerecht",
                "these are the right places...",
                R.drawable.gymguide_full_logo,
                R.drawable._4_seniorengrecht
            ),
            hView_Item(6,
                "Blog #01",
                "the personal...",
                R.drawable.gymguide_full_logo,
                R.drawable._5_blog
            ),
            hView_Item(7 ,
                "Die Vorteile",
                "this benefits the muscle...",
                R.drawable.gymguide_full_logo,
                R.drawable._7_item
            ),
        )
    }}