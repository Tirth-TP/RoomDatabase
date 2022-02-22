package com.example.roomdatabaseassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseassignment.adapter.UserAdapter
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.data.UserViewModel
import com.example.roomdatabaseassignment.databinding.FragmentView50PlusDataBinding

class View50PlusDataFragment : Fragment() {

    private lateinit var mBinding: FragmentView50PlusDataBinding
    var context: AppCompatActivity? = null

    lateinit var mUSerViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        mBinding = FragmentView50PlusDataBinding.inflate(layoutInflater, container, false)
        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recycler View
        val adapter = UserAdapter(object : UserAdapter.BtnClickListener {
            override fun onBtnEdit(user: User) {   //BtnClick For edit (Update)


                val fragment = UpdateFragment()         //Creating Object For Fragment
                val args = Bundle()                    //Creating Object For Bundle
                args.putParcelable("user", user)      //Put Value In Bundle Object "user" is a key for get data which is used in UpdateFragment.
                fragment.arguments = args            //Set That value in fragment Object

                //For Use putParcelable Create Parcelize User data class (Also Add plugins)
                // (Second method for this is available in ViewAllDataFragment)

//It will Pass Fragment (UpdateFragment) with Bundle Which have information of some Particular user

                activity?.addReplaceFragment(fragment = fragment,
                    addFragment = false, R.id.fragmentContainer, true)

            }

            override fun onBtnDelete(user: User) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Yes") { _, _ ->
                    mUSerViewModel.delete(user)
                    Toast.makeText(requireContext(),
                        "Successfully Delete ${user.firstname}",
                        Toast.LENGTH_LONG).show()
                }
                builder.setNegativeButton("No") { _, _ -> }
                builder.setTitle("Delete ${user.firstname} ?")
                builder.setMessage("Are You Sure You Want To Delete ${user.firstname} ?")
                builder.create().show()
            }

        })

        mBinding.filterView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.filterView.adapter = adapter

        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUSerViewModel.readFilterData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }
    }
}




