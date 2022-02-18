package com.example.roomdatabaseassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseassignment.adapter.UserAdapter
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.data.UserViewModel

class ViewAllDataFragment : Fragment() {

    lateinit var mUserViewModel: UserViewModel
    var context: AppCompatActivity? = null

    private lateinit var mUSerViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        context = activity as AppCompatActivity
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_all_data, container, false)

        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recycler View
        val adapter = UserAdapter(object : UserAdapter.BtnClickListener {
            override fun onBtnEdit(user: User) {


                //Set Object of fragment
                //Apply will set all operation in UpdateFragment() and it will set all operation and data in fragment (Object)
                val fragment = UpdateFragment().apply {
                    //Create object for bundle. (arguments is default) apply will set all operation in that (It will set all operation in fragment object)
                    arguments = Bundle().apply {
                        putParcelable("user", user)
                    }
                }

                (context as AppCompatActivity).addReplaceFragment(fragment = fragment,
                    addFragment = false, R.id.fragmentContainer, true)
            }

            override fun onBtnDelete(user: User) {
                deleteUser(user)
            }
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
    }

    // Delete User
    private fun deleteUser(user: User) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.delete(user)
            Toast.makeText(requireContext(),
                "Successfully Delete ${user.firstname}",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${user.firstname} ?")
        builder.setMessage("Are You Sure You Want To Delete ${user.firstname} ?")
        builder.create().show()

    }
}