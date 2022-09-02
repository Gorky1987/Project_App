package de.syntaxinstitut.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import de.syntaxinstitut.myapplication.databinding.ActivityMainBinding

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode, wird aufgerufen wenn Activity erstellt wird
     *
     * @param savedInstanceState      Save state vom view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Das Binding zur XML-Datei
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        navController = findNavController(R.id.main_fragment)
//
//        setupActionBarWithNavController(navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

    fun setToolBar(title: String) {
        binding.toolbarText.text = title
    }
}
