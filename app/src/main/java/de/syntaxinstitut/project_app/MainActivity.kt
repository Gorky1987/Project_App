package de.syntaxinstitut.project_app


import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import de.syntaxinstitut.project_app.databinding.ActivityMainBinding
import de.syntaxinstitut.project_app.util.hView_Adapter


/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {


    val navigateTo: (Int) -> Unit = { itemId ->
        var layoutId = when (itemId) {

            1 -> {
                binding.headerImg.setImageResource(R.drawable.homescreen1)
                (R.id.homeFragment)
            }
            2 -> {
                binding.headerImg.setImageResource(R.drawable.search)
                (R.id.searchFragment)
            }
            3 -> {
                binding.headerImg.setImageResource(R.drawable.profil)
                (R.id.profilFragment)
            }
            4 -> {
                binding.headerImg.setImageResource(R.drawable.gymdifference)
                (R.id.gymDifferenceFragment)
            }
            5 -> {
                binding.headerImg.setImageResource(R.drawable.fact)
                (R.id.fatcFragment)
            }
            else -> {
                binding.headerImg.setImageResource(R.drawable.blog)
                (R.id.blogFragment)
            }
        }
        binding.navHostFragment.findNavController().navigate(layoutId)

    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        viewModel.currentUser.observe(
            this,
            Observer {
                if (it == null) {
                    navController.navigate(R.id.loginFragment)
                } else {
                    viewModel.getMember()
                }
            }
        )
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }

        viewModel.member.observe(
            this,
            {
                binding.hiText.text = "Hi ${it.name}!"
            }

        )


        // Inflate the layout for this fragment


        val horizontal_Item = viewModel.repository.loadItem()

        binding.recyclerHorizontal.adapter = hView_Adapter(horizontal_Item, navigateTo)

        binding.recyclerHorizontal.setHasFixedSize(true)


    }


    //Binding XML-Datei

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun hideUI() {

        binding.textLayout.visibility = View.GONE
        binding.headerImg.visibility = View.GONE
        binding.hiText.visibility = View.GONE
        binding.logoutButton.visibility = View.GONE
        binding.orangeLine.visibility = View.GONE

    }


    fun showUI() {

        binding.textLayout.visibility = View.VISIBLE
        binding.headerImg.visibility = View.VISIBLE
        binding.hiText.visibility = View.VISIBLE
        binding.logoutButton.visibility = View.VISIBLE
        binding.orangeLine.visibility = View.VISIBLE

    }


}


