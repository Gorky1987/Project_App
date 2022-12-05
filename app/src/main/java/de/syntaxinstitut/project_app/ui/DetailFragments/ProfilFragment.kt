package de.syntaxinstitut.project_app.ui.DetailFragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import de.syntaxinstitut.project_app.data.Member
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.member.observe(
            viewLifecycleOwner, Observer {
                if (it != null) {
                    binding.tvName.text = it.name
                    binding.tvBirthdayInput.text = it.birthday
                    binding.tvWohnortInput.text = it.hometown
                    binding.tvBioInput.text = it.bio

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

        binding.btnEditBirthday.setOnClickListener {
            val etNew = EditText(context)
            val setBirthday = etNew.setText("").toString()

            val meldung = AlertDialog.Builder(requireContext())
            meldung.setMessage("Trage dein Geburtsdatum ein")
            meldung.setPositiveButton("Speichern") { _, _ ->

                binding.tvBirthdayInput.text = setBirthday
                val member = viewModel.member.value!!
                member.birthday = etNew.text.toString()
                viewModel.setBirthday(member)
                viewModel.getMember()
            }
            meldung.setNegativeButton("Abbrechen") { _, _ ->
            }

            meldung.setView(etNew)
            meldung.show()
        }

        binding.btnEditHometown.setOnClickListener {
            val etNew = EditText(context)
            val setHometown = etNew.setText("").toString()

            val meldung = AlertDialog.Builder(requireContext())
            meldung.setMessage("Trage deinen Wohnort ein")
            meldung.setPositiveButton("Speichern") { _, _ ->

                binding.tvWohnortInput.text = setHometown
                val member = viewModel.member.value!!
                member.hometown = etNew.text.toString()
                viewModel.setHometown(member)
                viewModel.getMember()
            }
            meldung.setNegativeButton("Abbrechen") { _, _ ->
            }
            meldung.setView(etNew)
            meldung.show()
        }

        binding.btnEditBio.setOnClickListener {
            val etNew = EditText(context)
            val setbio = etNew.setText("").toString()

            val meldung = AlertDialog.Builder(requireContext())
            meldung.setMessage("Schreibe etwas über dich: ")
            meldung.setPositiveButton("Speichern") { _, _ ->

                binding.tvBioInput.text = setbio
                val member = viewModel.member.value!!
                member.bio = etNew.text.toString()
                viewModel.setBIO(member)
                viewModel.getMember()
            }
            meldung.setNegativeButton("Abbrechen") { _, _ ->
            }
            meldung.setView(etNew)
            meldung.show()
        }






        binding.uploadImage.setOnClickListener {
            getContent.launch("image/*")
        }

    }


}
