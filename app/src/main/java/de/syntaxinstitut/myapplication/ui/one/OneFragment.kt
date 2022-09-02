package de.syntaxinstitut.myapplication.ui.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.myapplication.MainActivity
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.databinding.FragmentOneBinding

/**
 * Fragment 1
 */
class OneFragment : Fragment(R.layout.fragment_one) {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentOneBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: OneViewModel by viewModels()

    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setToolBar("home")
    }

    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     *
     * @param view                    Das angezeigte View
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* -------------------- UI-Interaktionen -------------------- */

        binding.btnFragmentOne.setOnClickListener {
            viewModel.navigateToFragmentTwo()
        }

        /* -------------------- Observer -------------------- */

        // Navigation zum zweiten Fragment
        viewModel.navigateToFragmentTwo.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(
                    OneFragmentDirections.actionOneFragmentToTwoFragment()
                )

                viewModel.resetAllValues()
            }
        }
    }
}
