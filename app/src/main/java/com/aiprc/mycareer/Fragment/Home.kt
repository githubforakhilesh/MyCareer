package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiprc.mycareer.Adapter.ExploreCourseAdapter
import com.aiprc.mycareer.R
import com.aiprc.mycareer.Singleton_Class.MySharedPref
import com.aiprc.mycareer.ViewModelFactory.ExploreCourseViewmodelFactory
import com.aiprc.mycareer.ViewmodelClass.ExploreCourseViewmodel


class Home : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val health_care = view.findViewById<CardView>(R.id.about_healthcare_card)
        val pick_course = view.findViewById<androidx.cardview.widget.CardView>(R.id.pick_course)
        val scholarship = view.findViewById<androidx.cardview.widget.CardView>(R.id.scholarship_card)
        val short_program = view.findViewById<androidx.cardview.widget.CardView>(R.id.short_programm_card)
        val counselling = view.findViewById<androidx.cardview.widget.CardView>(R.id.counselling_card)
        val recyclerView = view.findViewById<RecyclerView>(R.id.explore_programm_recyclerView)
         val window = activity?.window
        window?.setStatusBarColor(ContextCompat.getColor(requireContext(),R.color.red) )

        pick_course.setOnClickListener(View.OnClickListener {
            replace_fragment(Pickmy_course())
        })
        scholarship.setOnClickListener(View.OnClickListener {
            replace_fragment(Scholarship())
        })
        short_program.setOnClickListener(View.OnClickListener {
            replace_fragment(Short_programmes())
        })
        counselling.setOnClickListener(View.OnClickListener {
            replace_fragment(Free_Counselling())

        })
        health_care.setOnClickListener(View.OnClickListener {
            replace_fragment(Know_healthcare())

        })
        val viewModel = ViewModelProviders.of(this,
            context?.let { ExploreCourseViewmodelFactory(it) }).get(ExploreCourseViewmodel::class.java)
        viewModel.get_Courses().observe(viewLifecycleOwner, Observer {
           val adapter = context?.let { it1 -> ExploreCourseAdapter(it1,it) }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            adapter?.setInterface(object : ExploreCourseAdapter.AdapterInterface {
                override fun getItemId(id: String) {
                    MySharedPref(context!!).setPreferences(MySharedPref.id, id)
                    val manager = parentFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.frame_layout, Course_Description())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            })
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