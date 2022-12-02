package de.syntaxinstitut.project_app.ui.DetailFragments

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import coil.load
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.databinding.FragmentProfilBinding


class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var navController: NavController


    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.uploadImage(uri)
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).showUI()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.member.observe(
            viewLifecycleOwner, Observer {
                if (it != null) {
                    binding.tvName.text = it.name

                    binding.userImage.load(it.image) {
                        error(resources.getDrawable(R.drawable.profil_image_default))
                    }
                }
            }
        )

        viewModel.currentUser.observe(
            viewLifecycleOwner, Observer {
                binding.tvEmailInput.text = it?.email

            }
        )
     //   viewModel.db.collection("user").document(currentUser.value!!.uid).get(member)
     //       viewLifecycleOwner, Observer {
     //           binding.tvBirthdayInput.text = it.birthday
       //     }
       // )

         /* binding.tvBirthdayInput.setOnClickListener{

              val et_new = EditText(this)
              et_new.setText("Trage dein Geburtsdatum ein")

              val meldung = AlertDialog.Builder(this)
              meldung.setMessage("Trage dein Geburtsdatum ein")
              meldung.setPositiveButton("Speichern") { _, _ ->

                  binding.tvBirthdayInput.text = et_new.text


                  viewModel.setBirthday()*/







         binding.uploadImage.setOnClickListener {
             getContent.launch("image/*")
         }

    }


}
