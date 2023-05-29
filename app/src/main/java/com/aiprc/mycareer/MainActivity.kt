package com.aiprc.mycareer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.aiprc.mycareer.Fragment.Home
import com.aiprc.mycareer.Fragment.Study
import com.aiprc.mycareer.Fragment.help
import com.aiprc.mycareer.Fragment.profile

class MainActivity : AppCompatActivity() {
    private lateinit var home_image: AppCompatImageView;
    lateinit var book_image: AppCompatImageView;
    lateinit var help_image: AppCompatImageView
    lateinit var profile_image: AppCompatImageView;
    lateinit var home_text: AppCompatTextView
    lateinit var book_text: AppCompatTextView
    lateinit var help_text: AppCompatTextView
    lateinit var profile_text: AppCompatTextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        home_image = findViewById(R.id.home)
        book_image = findViewById(R.id.book)
        help_image = findViewById(R.id.help)
        profile_image = findViewById(R.id.profile)
        home_text = findViewById(R.id.home_text)
        book_text = findViewById(R.id.book_text)
        help_text = findViewById(R.id.help_text)
        profile_text = findViewById(R.id.profile_text)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Home_fragment_call()
        home_image.setOnClickListener(View.OnClickListener {
              Home_fragment_call()
        })
        book_image.setOnClickListener(View.OnClickListener {
            home_image.setImageDrawable(getDrawable(R.drawable.white_home))
            book_image.setImageDrawable(getDrawable(R.drawable.red_book))
            help_image.setImageDrawable(getDrawable(R.drawable.white_help))
            profile_image.setImageDrawable(getDrawable(R.drawable.white_profile))
            home_text.setTextColor(getColor(R.color.brown))
            book_text.setTextColor(getColor(R.color.red))
            help_text.setTextColor(getColor(R.color.brown))
            profile_text.setTextColor(getColor(R.color.brown))
            replace_fragment(Study())
        })
        help_image.setOnClickListener(View.OnClickListener {
            home_image.setImageDrawable(getDrawable(R.drawable.white_home))
            book_image.setImageDrawable(getDrawable(R.drawable.white_book))
            help_image.setImageDrawable(getDrawable(R.drawable.red_help))
            profile_image.setImageDrawable(getDrawable(R.drawable.white_profile))
            home_text.setTextColor(getColor(R.color.brown))
            book_text.setTextColor(getColor(R.color.brown))
            help_text.setTextColor(getColor(R.color.red))
            profile_text.setTextColor(getColor(R.color.brown))
            replace_fragment(help())
        })
        profile_image.setOnClickListener(View.OnClickListener {
            home_image.setImageDrawable(getDrawable(R.drawable.white_home))
            book_image.setImageDrawable(getDrawable(R.drawable.white_book))
            help_image.setImageDrawable(getDrawable(R.drawable.white_help))
            profile_image.setImageDrawable(getDrawable(R.drawable.red_profile))
            home_text.setTextColor(getColor(R.color.brown))
            book_text.setTextColor(getColor(R.color.brown))
            help_text.setTextColor(getColor(R.color.brown))
            profile_text.setTextColor(getColor(R.color.red))
            replace_fragment(profile())
        })
    }
    fun replace_fragment(fragment: Fragment ){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()

    }
    fun Home_fragment_call(){
        home_image.setImageDrawable(getDrawable(R.drawable.red_home))
        book_image.setImageDrawable(getDrawable(R.drawable.white_book))
        help_image.setImageDrawable(getDrawable(R.drawable.white_help))
        profile_image.setImageDrawable(getDrawable(R.drawable.white_profile))
        home_text.setTextColor(getColor(R.color.red))
        book_text.setTextColor(getColor(R.color.brown))
        help_text.setTextColor(getColor(R.color.brown))
        profile_text.setTextColor(getColor(R.color.brown))
        replace_fragment(Home())

    }
}