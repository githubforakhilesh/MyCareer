package com.aiprc.mycareer.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aiprc.mycareer.R
import com.aiprc.mycareer.ViewModelFactory.CourseDecViewModelfactory
import com.aiprc.mycareer.ViewModelFactory.RegistrationViewModelFactory
import com.aiprc.mycareer.ViewmodelClass.CourseDescViewModel
import com.aiprc.mycareer.ViewmodelClass.RegistrationViewModel
import java.security.cert.Certificate
import java.util.ArrayList as ArrayList1


class Registration_activity : AppCompatActivity() {
    lateinit var first_name: EditText
    lateinit var last_name: EditText
    lateinit var email: EditText
    lateinit var mobile_no: EditText
    lateinit var address: EditText
    lateinit var qualification: Spinner
    lateinit var course_type: Spinner
    lateinit var password:EditText
    lateinit var course_type_str:String
    lateinit var batch: EditText
    lateinit var certificate: EditText
    lateinit var arrayList: MutableList<String>
    val batch_str = arrayOf("Select","Yes","No")
    lateinit var qualification_str: String
    lateinit var certificate_str: String
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val submit_button = findViewById<Button>(R.id.submit_button)
         first_name = findViewById<EditText>(R.id.register_first_name)
         last_name = findViewById<EditText>(R.id.register_last_name)
         email = findViewById<EditText>(R.id.register_email)
         mobile_no = findViewById<EditText>(R.id.register_mobile)
         address = findViewById<EditText>(R.id.register_address)
        qualification = findViewById<Spinner>(R.id.register_qualification)
         course_type = findViewById<Spinner>(R.id.register_course_type)
        password = findViewById(R.id.register_password)
        batch = findViewById(R.id.batch_type)
        certificate = findViewById(R.id.certificate)
          arrayList = mutableListOf()
          arrayList.add("Select Course")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        submit_button.setOnClickListener(View.OnClickListener {
            var first_name:String = first_name.text.toString()
            var last_name:String = last_name.text.toString()
            var email:String = email.text.toString()
            var mobile_no:String = mobile_no.text.toString()
            var address:String = address.text.toString()
            var batch:String = batch.text.toString()
            var password:String = password.text.toString()
                certificate_str = certificate.text.toString()
           if(checkValidation(first_name,last_name,email,mobile_no,address,batch,course_type_str,password,qualification_str,certificate_str)) {
               val viewModel = ViewModelProviders.of(this,RegistrationViewModelFactory(context = applicationContext)).get(RegistrationViewModel::class.java)
               viewModel.register(first_name= first_name,last_name=last_name,email=email,qualification=qualification_str, course = course_type_str, certificate = certificate_str , batch = batch , password = password, mobile = mobile_no, address = address).observe(this,
                   Observer {
                       if(it){
                           Toast.makeText(applicationContext,"Registration Successful",Toast.LENGTH_SHORT).show()
                           val intent = Intent(this, Login_activity::class.java)
                           startActivity(intent)
                           finish()
                       }else{
                           Toast.makeText(applicationContext,"SomeThing Goes Wrong Please try Again...",Toast.LENGTH_SHORT).show()

                       }
                   })
           }
        })

        val ViewModel = ViewModelProviders.of(this, CourseDecViewModelfactory(applicationContext)).get(
            CourseDescViewModel::class.java)
        ViewModel.get_courseData().observe(this, Observer {
            for(i in 1 until it.size) {
                it.get(i).name?.let { it1 -> arrayList.add(it1) }
            }
            var adapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,arrayList)
            adapter.setNotifyOnChange(true)
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            course_type.adapter = adapter
            course_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    course_type_str = it.get(p2).id.toString()
                    adapter.setNotifyOnChange(true)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

        })
        val adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,batch_str)
           adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
           qualification.adapter = adapter
           qualification.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
               override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                   qualification_str = batch_str[p2]
               }

               override fun onNothingSelected(p0: AdapterView<*>?) {
                   TODO("Not yet implemented")
               }

           }


    }






    private fun checkValidation(first_name: String,last_name: String,email: String,mobile_no:String,address: String,batch:String,course_type: String,password: String,qualification_str: String,certificate_str: String): Boolean {

       if(first_name.isEmpty()) {
           Toast.makeText(applicationContext,"please enter your first name",Toast.LENGTH_SHORT).show()
       }else if(last_name.isEmpty()){
           Toast.makeText(applicationContext,"please enter your last name",Toast.LENGTH_SHORT).show()

       }else if(email.isEmpty()){
           Toast.makeText(applicationContext,"please enter your valid email",Toast.LENGTH_SHORT).show()

       }else if(mobile_no.isEmpty() || mobile_no.length < 10){
           Toast.makeText(applicationContext,"please enter your valid mobile number",Toast.LENGTH_SHORT).show()

       }else if(address.isEmpty()){
           Toast.makeText(applicationContext,"please enter your address",Toast.LENGTH_SHORT).show()

       }else if(batch.isEmpty()){
           Toast.makeText(applicationContext,"please enter your batch Timing",Toast.LENGTH_SHORT).show()

       }else if(course_type.isEmpty() || course_type.equals("Select Course")){
           Toast.makeText(applicationContext,"please select your course_type",Toast.LENGTH_SHORT).show()

       }else if(password.isEmpty() || password.length < 6){
           Toast.makeText(applicationContext,"your password should be minimum of 6 character",Toast.LENGTH_SHORT).show()
       }else if(qualification_str.isEmpty() || qualification_str.equals("Select")) {
           Toast.makeText(applicationContext,"Please Select pass out status",Toast.LENGTH_SHORT).show()

       }else if(certificate_str.isEmpty()) {
           Toast.makeText(applicationContext,"Please enter your passing year",Toast.LENGTH_SHORT).show()

       }else{


           return true
       }

      return  false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, Login_activity::class.java)
        startActivity(intent)
        finish()

    }
}