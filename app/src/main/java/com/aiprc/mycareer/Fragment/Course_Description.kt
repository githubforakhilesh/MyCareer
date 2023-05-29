package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.aiprc.mycareer.R
import com.aiprc.mycareer.Singleton_Class.MySharedPref
import com.aiprc.mycareer.ViewModelFactory.Course_desc_Factory
import com.aiprc.mycareer.ViewmodelClass.Course_Decription


class Course_Description : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course__description, container, false)
        val id = MySharedPref(requireContext()).getPreferences(MySharedPref.id)
        Toast.makeText(context,""+id,Toast.LENGTH_SHORT).show()
        val name = view.findViewById<AppCompatTextView>(R.id.name)
        val category = view.findViewById<AppCompatTextView>(R.id.category)
        val course_short_name = view.findViewById<AppCompatTextView>(R.id.course_short_name)
        val duration = view.findViewById<AppCompatTextView>(R.id.duration)
        val eligibility = view.findViewById<AppCompatTextView>(R.id.eligibility)
        val admission_process = view.findViewById<AppCompatTextView>(R.id.admission_process)
        val top_recruit = view.findViewById<AppCompatTextView>(R.id.top_recruit)
        val short = view.findViewById<AppCompatTextView>(R.id.course_short)
        val description = view.findViewById<AppCompatTextView>(R.id.description)
        val price = view.findViewById<AppCompatTextView>(R.id.price)
        val sem_fee = view.findViewById<AppCompatTextView>(R.id.sem_fee)
        val image = view.findViewById<AppCompatImageView>(R.id.image)

        val viewModel = ViewModelProviders.of(this, context?.let { Course_desc_Factory(it) }).get(Course_Decription::class.java)
        if (id != null) {
            viewModel.get_Courses(id).observe(viewLifecycleOwner, Observer {
               name.setText(it.get(0).name)
                category.setText(it.get(0).category)
                course_short_name.setText(it.get(0).course_short_name)
                duration.setText(it.get(0).duration)
                eligibility.setText(it.get(0).eligibility)
                admission_process.setText(it.get(0).admission_process)
                top_recruit.setText(it.get(0).top_recruit)
                short.setText(it.get(0).short)
                description.setText(it.get(0).description)
                price.setText(it.get(0).price)
                sem_fee.setText(it.get(0).sem_fee)
                Glide.with(this)
                    .load(it.get(0).course_image)
                    .into(image)
            })
        }
        return  view;
    }


}