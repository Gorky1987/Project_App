package de.syntaxinstitut.project_app.ui.DetailFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import de.syntaxinstitut.project_app.MainActivity


class FactFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).showUI()
    }


}