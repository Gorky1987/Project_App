package de.syntaxinstitut.project_app.ui.DetailFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.databinding.FragmentDiscountBinding
import de.syntaxinstitut.project_app.databinding.FragmentLuxuryGymBinding


class DiscountFragment : Fragment() {

    private lateinit var binding: FragmentDiscountBinding

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
        binding = FragmentDiscountBinding.inflate(inflater, container, false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonBack3.setOnClickListener(){
            findNavController().navigate(DiscountFragmentDirections.actionDiscountFragmentToGymDifferenceFragment())
        }
    }
}