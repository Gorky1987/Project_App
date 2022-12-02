package de.syntaxinstitut.project_app.ui.DetailFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.databinding.FragmentGymDifferenceBinding


class GymDifferenceFragment : Fragment() {

    private lateinit var binding: FragmentGymDifferenceBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).showUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentGymDifferenceBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.IV1.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToFamilyGymFragment())
        }

        binding.IV2.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToLuxuryGymFragment())
        }
        binding.IV03.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToDiscountFragment())
        }

        binding.IV04.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToWomansGymFragment())
        }
        binding.IV05.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToBodybuilderGymFragment())
        }
        binding.IV06.setOnClickListener() {
            findNavController().navigate(GymDifferenceFragmentDirections.actionGymDifferenceFragmentToSportClubFragment())
        }

    }
}