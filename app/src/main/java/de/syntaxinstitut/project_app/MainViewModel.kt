package de.syntaxinstitut.project_app

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
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
    private val db = FirebaseFirestore.getInstance()

    // Kommunikationspunkt mit der FirebaseAuth
    private val firebaseAuth = FirebaseAuth.getInstance()


    // currentuser ist null wenn niemand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    // Player enthält alle relevanten Daten aus dem Firestore
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

    // hier wird userid, nickname und level in die Firestore Datenbank gespeichert
    private fun setName(member: Member) {
        db.collection("user").document(currentUser.value!!.uid)
            .set(member)
            .addOnFailureListener {
                Log.w(TAG, "Error writing document: $it")
                _toast.value = "error creating player\n${it.localizedMessage}"

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

    // hier werden Spielerdaten mittles userid aus dem Firestore geladen
    fun getPlayerData() {
        db.collection("user").document(currentUser.value!!.uid)
            .get().addOnSuccessListener {
                _member.value = it.toObject(Member::class.java)
            }
            .addOnFailureListener {
                Log.e(TAG, "Error reading document: $it")
            }
    }


    fun getMember(){
        db.collection("user").document(currentUser.value!!.uid).get().addOnSuccessListener {
            _member.value = it.toObject(Member::class.java)
        }
    }


    fun getAllBlogs():List<Blog>{

           return repository.initialBlog()

        }


    fun insertBlog(blog: Blog) {

        viewModelScope.launch {

            repository.insert(blog)

        }
    }






}


