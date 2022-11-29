package de.syntaxinstitut.project_app.ui.BlogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import de.syntaxinstitut.project_app.MainActivity
import de.syntaxinstitut.project_app.MainViewModel
import de.syntaxinstitut.project_app.databinding.FragmentBlogBinding
import de.syntaxinstitut.project_app.util.BlogAdapter


class BlogFragment : Fragment() {

    private lateinit var binding: FragmentBlogBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlogBinding.inflate(
            inflater,  container, false
        )
        // Inflate the layout for this fragment
        return binding.root


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as MainActivity).showUI()
val blogAdapter = BlogAdapter(listOf())

    viewModel.blogList.observe(viewLifecycleOwner){
        blogAdapter.submitList(it)
    }

        binding.blogRecycler.adapter = blogAdapter

        binding.blogRecycler.setHasFixedSize(true)
    }
}