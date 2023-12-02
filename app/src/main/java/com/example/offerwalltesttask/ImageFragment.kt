package com.example.offerwalltesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val img: ImageView = view.findViewById(R.id.img)

        val imgUrl = (viewModel.content.value as ContentImage).url
        Picasso.with(context).load(imgUrl).into(img)

        return view
    }
}