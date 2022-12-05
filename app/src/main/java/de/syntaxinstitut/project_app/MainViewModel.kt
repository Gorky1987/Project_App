package de.syntaxinstitut.project_app

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import de.syntaxinstitut.project_app.data.GymSearchApi
import de.syntaxinstitut.project_app.data.Member
import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.data.repository.Repository
import de.syntaxinstitut.project_app.data.room.getDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


const val TAG = "MainViewModel"

/**
 * Das MainViewModel
 */

// hier wird statt ViewModel AndroidViewModel verwendet um den applicationcontext zu bekommen
class MainViewModel(application: Application) : AndroidViewModel(application) {


    val database = getDatabase(application)

    val repository = Repository(database)

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
            repository.fillTableIfEmpty()
        }
    }


    // Kommunikationspunkt mit der Firestore Datenbank
     val db = FirebaseFirestore.getInstance()

    // Kommunikationspunkt mit der FirebaseAuth
     val firebaseAuth = FirebaseAuth.getInstance()

    // Kommunikationspunkt mit Firebase Storage

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference


    // currentuser ist null wenn niemand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser



    // Member enthält alle relevanten Daten aus dem Firestore
    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member>
        get() = _member


    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast


    val blogList = repository.blogList

    private val _item = MutableLiveData<Repository>()
    val item: LiveData<Repository>
        get() = _item


    // hier wird versucht einen User zu erstellen um diesen anschließend auch gleich
    // einzuloggen

    fun signUp(email: String, password: String, member: Member) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _currentUser.value = firebaseAuth.currentUser
                        setName(member)
                        _toast.value = "welcome"
                        _toast.value = null
                    } else {
                        Log.e(TAG, "Login failed: ${it.exception}")
                        _toast.value = "login failed\n${it.exception?.localizedMessage}"
                        _toast.value = null
                    }
                }
            } else {
                Log.e(TAG, "SignUp failed: ${it.exception}")
                _toast.value = "signup failed\n${it.exception?.localizedMessage}"

            }
        }
    }

    // hier wird nickname in die Firestore Datenbank gespeichert
    private fun setName(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating birthday\n${it.localizedMessage}"
            }
    }

     fun setBirthday(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating Birthday\n${it.localizedMessage}"
                _toast.value = null
            }
    }
    fun setHometown(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating Birthday\n${it.localizedMessage}"
                _toast.value = null
            }
    }

    fun setBIO(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating Birthday\n${it.localizedMessage}"
            }
    }







    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _currentUser.value = firebaseAuth.currentUser

            } else {
                Log.e(TAG, "Login failed: ${it.exception}")
                _toast.value = "login failed\n${it.exception?.localizedMessage}"
                _toast.value = null
            }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    // hier werden Userdaten mittles userid aus dem Firestore geladen

    fun getMember() {
        db.collection("user").document(currentUser.value!!.uid)
            .get().addOnSuccessListener {
            _member.value = it.toObject(Member::class.java)
        }
            .addOnFailureListener {
                Log.e(TAG, "Error reading document: $it")
            }
    }




    fun getAllBlogs(): List<Blog> {

        return repository.initialBlog()

    }


    fun insertBlog(blog: Blog) {

        viewModelScope.launch {

            repository.insert(blog)

        }
    }


    var gymSearch = repository.gymSearch

    fun loadGymSearch(plz: String) {
        viewModelScope.launch {
            repository.getGymSearch(plz)
        }
    }

    fun uploadImage(uri: Uri) {
        val imageRef = storageRef.child("images/${currentUser.value?.uid}/profilePic")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnFailureListener {
            Log.e("MainViewModel", "upload failed: $it")
        }

        uploadTask.addOnSuccessListener {
            Log.e("MainViewModel", "upload worked")
        }

        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    setImage(it.result)
                }
            }
        }

    }
    private fun setImage(uri: Uri) {
        db.collection("user").document(currentUser.value!!.uid)
            .update("image", uri.toString())
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating player\n${it.localizedMessage}"
                _toast.value = null
            }
            .addOnCompleteListener {
                getMember()
            }
    }
}


