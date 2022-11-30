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
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.databinding.FragmentDiscountBinding
import de.syntaxinstitut.project_app.databinding.FragmentProfilBinding
import de.syntaxinstitut.project_app.databinding.FragmentSearchBinding
import de.syntaxinstitut.project_app.util.BlogAdapter
import de.syntaxinstitut.project_app.util.SearchAdapter


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }



        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val gymSearchAdapter = SearchAdapter(listOf())

            viewModel.gymSearch.observe(viewLifecycleOwner){
                gymSearchAdapter.submitList(it)
            }

            binding.searchRecyclerView.adapter = gymSearchAdapter

            binding.btnGymSearch.setOnClickListener(){

                val userInput = binding.editTextNumber.text


                if (!userInput.isNullOrEmpty() && userInput.toString().length == 5 )
                    viewModel.loadGymSearch(userInput.toString())

            }

        }
}




