package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiprc.mycareer.Adapter.CourseDescAdapter
import com.aiprc.mycareer.R
import com.aiprc.mycareer.Singleton_Class.MySharedPref
import com.aiprc.mycareer.ViewModelFactory.CourseDecViewModelfactory
import com.aiprc.mycareer.ViewmodelClass.CourseDescViewModel


class CourseDescription : Fragment() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view: View =  inflater.inflate(R.layout.fragment_course_description, container, false)
       val recyclerView = view.findViewById<RecyclerView>(R.id.course_description_recyclerView)
        val ViewModel = ViewModelProviders.of(this, context?.let { CourseDecViewModelfactory(it) }).get(CourseDescViewModel::class.java)
          ViewModel.get_courseData().observe(viewLifecycleOwner, Observer {
            val  adapter = context?.let { it1 -> CourseDescAdapter(context = it1,it) }!!
              recyclerView.layoutManager = LinearLayoutManager(context)
              recyclerView.adapter = adapter
              adapter?.setInterface(object : CourseDescAdapter.AdapterInterface{
                  override fun getItemId(id: String) {
                      MySharedPref(context!!).setPreferences(MySharedPref.id,id)
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


}