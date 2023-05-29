package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.aiprc.mycareer.R


class profile : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val personal_information = view.findViewById<CardView>(R.id.personal_information_card)
        val term_condition = view.findViewById<CardView>(R.id.term_condition_card)
        val privacy_policy = view.findViewById<CardView>(R.id.privacy_policy_card)

        personal_information.setOnClickListener(View.OnClickListener {
            replace_fragment(Personal_information())
        })
        term_condition.setOnClickListener(View.OnClickListener {
            replace_fragment(TermandCondition())
        })
        privacy_policy.setOnClickListener(View.OnClickListener {
            replace_fragment(privacy_policy())
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