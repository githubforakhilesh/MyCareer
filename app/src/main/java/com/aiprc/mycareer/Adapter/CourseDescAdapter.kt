package com.aiprc.mycareer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.aiprc.mycareer.Data_Classes.CourseDescDataClass
import com.aiprc.mycareer.R

class CourseDescAdapter(val context: Context,val arrayList: ArrayList<CourseDescDataClass>) : RecyclerView.Adapter<CourseDescAdapter.ViewHolder>() {

  private var adapterInterface : AdapterInterface? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDescAdapter.ViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(R.layout.category_card_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseDescAdapter.ViewHolder, position: Int) {
     holder.name.setText(arrayList.get(position).name)
        holder.description.setText(arrayList.get(position).description)
        holder.price.setText(arrayList.get(position).price)
        holder.discount.setText(arrayList.get(position).discount)
        Glide.with(context)
            .load(arrayList.get(position).course_image)
            .into(holder.image)

        holder.itemView.setOnClickListener(View.OnClickListener {
            arrayList.get(position).id?.let { it1 -> adapterInterface?.getItemId(it1) }
        })
    }



    override fun getItemCount(): Int {
        return  arrayList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<AppCompatTextView>(R.id.course_name)
        val description = itemView.findViewById<AppCompatTextView>(R.id.course_description)
        val price = itemView.findViewById<AppCompatTextView>(R.id.course_price)
        val discount = itemView.findViewById<AppCompatTextView>(R.id.course_discount)
        val image = itemView.findViewById<AppCompatImageView>(R.id.course_image)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)

    }
    fun setInterface(adapterInterface: AdapterInterface){
        this.adapterInterface = adapterInterface
    }

    interface AdapterInterface{
       fun getItemId(id: String)
    }
}