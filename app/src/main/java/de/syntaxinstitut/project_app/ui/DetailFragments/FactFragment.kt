package de.syntaxinstitut.project_app.ui.DetailFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.databinding.FragmentFactBinding
import de.syntaxinstitut.project_app.databinding.FragmentSportClubBinding


class FactFragment : Fragment() {

    private lateinit var binding: FragmentFactBinding

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
        binding = FragmentFactBinding.inflate(inflater, container, false)

        return binding.root

    }
}

