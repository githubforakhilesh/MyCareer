package com.aiprc.mycareer.Fragment

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.aiprc.mycareer.R

class Know_healthcare : Fragment() {
    var mediaControls: MediaController? = null
    var video_view: VideoView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_know_healthcare, container, false)
         video_view = view.findViewById<VideoView>(R.id.video_view)

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(requireContext())

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.video_view)
        }

        // set the media controller for video view
        video_view!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        video_view!!.setVideoURI(Uri.parse("android.resource://"
                + (context?.packageName ?: null) + "/" + R.raw.health_care))

        video_view!!.requestFocus()

        // starting the video
        video_view!!.start()

        // display a toast message
        // after the video is completed
        video_view!!.setOnCompletionListener {
            Toast.makeText(context, "Video completed",
                Toast.LENGTH_LONG).show()
            true
        }

        // display a toast message if any
        // error occurs while playing the video


         return view
    }


}