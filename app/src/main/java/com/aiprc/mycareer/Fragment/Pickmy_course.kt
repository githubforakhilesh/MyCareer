package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aiprc.mycareer.R
import com.aiprc.mycareer.ViewModelFactory.CategoryViewModelFactory
import com.aiprc.mycareer.ViewmodelClass.CategoryViewModel


class Pickmy_course : Fragment() {

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
   val view = inflater.inflate(R.layout.fragment_pickmy_course, container, false)
        val master_course = view.findViewById<CardView>(R.id.master_course)
        val diploma_course = view.findViewById<CardView>(R.id.diploma_course)
        val master_course_textview = view.findViewById<AppCompatTextView>(R.id.master_course_text)
        val diploma_course_textview = view.findViewById<AppCompatTextView>(R.id.diploma_course_text)
        val viewModel = ViewModelProviders.of(this, context?.let { CategoryViewModelFactory(it) }).get(CategoryViewModel::class.java)
          viewModel.get_categoryData().observe(viewLifecycleOwner, Observer {
              master_course_textview.setText(it.get(0).category_name)
              diploma_course_textview.setText(it.get(1).category_name)

          })
        master_course.setOnClickListener(View.OnClickListener {
            next_fragment(CourseDescription())

        })

        return view
    }
    fun next_fragment(fragment: Fragment){
        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}