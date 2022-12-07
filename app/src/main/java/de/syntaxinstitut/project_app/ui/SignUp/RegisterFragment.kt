package de.syntaxinstitut.project_app.ui.SignUp

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.data.Member
import de.syntaxinstitut.project_app.databinding.DialogeBinding
import de.syntaxinstitut.project_app.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

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
                    //                 Toast.makeText(requireContext(), it, Toast.LENGTH_SHORTY
                    //                 .show()
                    val view: View =
                        LayoutInflater.from(requireContext()).inflate(R.layout.dialoge, null)
                    val alertText = view.findViewById<TextView>(R.id.dialoge_text)
                    var alertHeader = view.findViewById<TextView>(R.id.dialoge_header)
                    alertHeader.text = "Gratulation & Wilkommen"
                    alertText.text = "du hast dich erfolgreich bei Gym Search registriert"
                    val dialog = AlertDialog.Builder(requireContext())
                        .setView(view)
                        .create()
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                        dialog.show()

                }
            }
        )

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                }
            }
        )

        binding.alreadyHaveAccount.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.registerUsername.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val confirmPassword = binding.ConfirmPassword.text.toString()

            if (!username.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty() && !confirmPassword.isNullOrEmpty() && password == confirmPassword) {
                viewModel.signUp(email, password, Member(name = username))
            }
        }
    }
}