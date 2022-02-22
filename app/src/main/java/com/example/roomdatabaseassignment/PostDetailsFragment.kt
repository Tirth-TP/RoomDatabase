package com.example.roomdatabaseassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabaseassignment.api.PostList
import com.example.roomdatabaseassignment.databinding.FragmentPostDetailsBinding
import com.example.roomdatabaseassignment.util.POST_KEY


class PostDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentPostDetailsBinding
    private var post: PostList? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        mBinding = FragmentPostDetailsBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        post = arguments?.getParcelable(POST_KEY)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as AppCompatActivity).supportActionBar?.title = post?.title

        mBinding.postBody.text = post?.body


    }


}

