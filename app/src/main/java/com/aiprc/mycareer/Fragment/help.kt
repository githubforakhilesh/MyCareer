package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.aiprc.mycareer.R



class help : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        val feedback = view.findViewById<CardView>(R.id.feedback_card)
        val doubts = view.findViewById<CardView>(R.id.doubts_card)
        val window = activity?.window
        window?.setStatusBarColor(ContextCompat.getColor(requireContext(),R.color.red) )

        feedback.setOnClickListener(View.OnClickListener {
            replace_fragment(feedback())

        })
        doubts.setOnClickListener(View.OnClickListener {
            replace_fragment(Doubts())

        })
        return view
    }
    fun replace_fragment(fragment: Fragment ){
        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }


}