package com.aiprc.mycareer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.aiprc.mycareer.Data_Classes.ExploreCourse
import com.aiprc.mycareer.R

class ExploreCourseAdapter(val context: Context,val arrayList: ExploreCourse) : RecyclerView.Adapter<ExploreCourseAdapter.ViewHolder>() {

    private var adapterInterface : ExploreCourseAdapter.AdapterInterface? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExploreCourseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.explore_course_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreCourseAdapter.ViewHolder, position: Int) {
        holder.course_name.setText(arrayList.get(position).name)
        holder.course_desc.setText(arrayList.get(position).description)
        holder.course_price.setText(arrayList.get(position).price)
        holder.sem_fee.setText(arrayList.get(position).sem_fee)
        Glide.with(context)
            .load(arrayList.get(position).course_image)
            .into(holder.course_image)
        holder.itemView.setOnClickListener(View.OnClickListener {
            arrayList.get(position).id?.let { it1 -> adapterInterface?.getItemId(it1) }
        })
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       val course_name = itemView.findViewById<AppCompatTextView>(R.id.explore_course_name)
        val course_desc = itemView.findViewById<AppCompatTextView>(R.id.explore_course_description)
        val course_price = itemView.findViewById<AppCompatTextView>(R.id.explore_course_price)
        val sem_fee = itemView.findViewById<AppCompatTextView>(R.id.explore_course_semFee)
       val course_image = itemView.findViewById<AppCompatImageView>(R.id.explore_course_image)
    }
    fun setInterface(adapterInterface: AdapterInterface){
        this.adapterInterface = adapterInterface
    }

    interface AdapterInterface{
        fun getItemId(id: String)
    }
}