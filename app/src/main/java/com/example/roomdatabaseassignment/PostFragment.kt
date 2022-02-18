package com.example.roomdatabaseassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseassignment.adapter.PostAdapter
import com.example.roomdatabaseassignment.api.ApiInstance
import com.example.roomdatabaseassignment.api.PostService
import com.example.roomdatabaseassignment.api.PostList
import com.example.roomdatabaseassignment.databinding.FragmentPostBinding
import com.example.roomdatabaseassignment.util.POST_KEY
import kotlinx.coroutines.launch

class PostFragment : Fragment() {

    private lateinit var mBinding: FragmentPostBinding
    var context: AppCompatActivity? = null
    private val postList: ArrayList<PostList> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        context = activity as AppCompatActivity

        mBinding = FragmentPostBinding.inflate(layoutInflater, container, false)


        // return inflater.inflate(R.layout.fragment_post, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as AppCompatActivity).supportActionBar?.title = "Post"

        val adapter = PostAdapter(postList, object : PostAdapter.OnCardViewClick {
            override fun onClickCard(post: PostList) {
                val fragment = PostDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(POST_KEY, post)
                    }
                }
                context?.addReplaceFragment(fragment, true, R.id.fragmentContainer, true)
            }
        })

        mBinding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvPost.adapter = adapter
        val apiInterface = ApiInstance.getApiData().create(PostService::class.java)

        lifecycleScope.launch {
            val result = apiInterface.getData()
            if (result.body() != null) {
                adapter.postList.clear()
                adapter.postList.addAll(result.body()!!)
                adapter.notifyDataSetChanged()
            }

        }
    }
}