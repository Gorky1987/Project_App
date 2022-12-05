package de.syntaxinstitut.project_app.ui.Login

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding



    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).hideUI()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel.toast.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
                        .show()

                    val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialoge, null)
                    val alertText = view.findViewById<TextView>(R.id.dialoge_text)
                    alertText.text = it

                   val alert =  AlertDialog.Builder(requireContext())
                        .setView(view)
                        .create()
                        .show()
                }
            }
        )

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
            }
        )

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.btnlogin.setOnClickListener {

            var email = binding.inputEmail.text.toString()
            var password = binding.inputPassword.text.toString()

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                viewModel.login(email, password)
            }
        }

        binding.btnfacebook.setOnClickListener {
            val url = "https://www.facebook.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        binding.btnGoogle.setOnClickListener {
            val url = "https://accounts.google.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        binding.btnApple.setOnClickListener {
            val url = "https://www.icloud.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        binding.forgotPassword.setOnClickListener{

            Toast(requireContext())
                .setView(view)


            val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialoge, null)
            val alertText = view.findViewById<TextView>(R.id.dialoge_text)
            var alertHeader = view.findViewById<TextView>(R.id.dialoge_header)
            alertText.text = "Du hast dein Passwort vergessen? Jammerschade. Bitte denk noch einmal drüber nach. Ansonsten hast du Pech gehabt. - LG, dein GymGuide"
            alertHeader.text = "Passwort vergessen?"

            val alert =  AlertDialog.Builder(requireContext())
                .setView(view)
                .create()
                .show()

        }
    }
}





