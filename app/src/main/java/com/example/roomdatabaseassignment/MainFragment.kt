package com.example.roomdatabaseassignment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabaseassignment.data.UserViewModel

import com.example.roomdatabaseassignment.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout


open class MainFragment : Fragment() {
    private lateinit var mBinding : FragmentMainBinding
    var context: AppCompatActivity? = null

    lateinit var mUSerViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = activity as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)

        mBinding.floatingActionButton.setOnClickListener {
            (context as AppCompatActivity).addReplaceFragment(fragment = AddUserFragment(),
                addFragment = false, R.id.fragmentContainer,true)
        }

        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        //Add Menu
        setHasOptionsMenu(true)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as AppCompatActivity).supportActionBar?.title = "Room DB"

        (context as AppCompatActivity).addReplaceFragment(fragment = ViewAllDataFragment(),
            addFragment = true, R.id.frameLayout,false)


        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    context?.addReplaceFragment(fragment = ViewAllDataFragment(),
                        addFragment = false, R.id.frameLayout,false)
                } else {
                    context?.addReplaceFragment(fragment = View50PlusDataFragment(),
                        addFragment = true, R.id.frameLayout,false)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        if(item.itemId == R.id.menu_file){
            (context as AppCompatActivity).addReplaceFragment(fragment = PostFragment(),
                addFragment = false, R.id.fragmentContainer,false)
        }

        return super.onOptionsItemSelected(item)
    }

    //Delete All User
    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUSerViewModel.deleteAllUser()
            Toast.makeText(requireContext(),
                "Successfully Delete All Data !",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete All Data ?")
        builder.setMessage("Are You Sure You Want To Delete All Data ?")
        builder.create().show()
    }


}





