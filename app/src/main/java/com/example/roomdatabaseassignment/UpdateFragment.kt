package com.example.roomdatabaseassignment

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.data.UserViewModel


class UpdateFragment : Fragment() {

    var context: AppCompatActivity? = null

    private var user: User? = null

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var userAge: EditText
    private lateinit var btnUpdate: Button

    private lateinit var mUSerViewModel: UserViewModel

    private lateinit var updateFirstname: String
    private lateinit var updateLastname: String
    private var updateAge: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        context = activity as AppCompatActivity

        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        user = arguments?.getParcelable("user")

        firstName = view.findViewById(R.id.updateUserFirstName)
        lastName = view.findViewById(R.id.updateUserLastName)
        userAge = view.findViewById(R.id.updateUserAge)

        btnUpdate = view.findViewById(R.id.btnUpdateUser)

        firstName.setText(user?.firstname)
        lastName.setText(user?.lastname)
        userAge.setText(user?.age.toString())

        btnUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {

        updateFirstname = firstName.text.toString().trim()
        updateLastname = lastName.text.toString().trim()
        updateAge = userAge.text.toString().trim().toInt()


        if (inputCheck(updateFirstname, updateLastname, updateAge)) {


            val updateUser = User(user!!.id, updateFirstname, updateLastname, updateAge)
            mUSerViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "User $updateFirstname Update Successfully !", Toast.LENGTH_LONG).show()

            //Navigate Bake after add data
            (context as AppCompatActivity).addReplaceFragment(fragment = MainFragment(),
                addFragment = false, R.id.fragmentContainer, false)

        } else
            Toast.makeText(requireContext(), "Please Fill Out All Fields", Toast.LENGTH_LONG).show()

    }

    private fun inputCheck(fName: String, lName: String, age: Int): Boolean {
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && age != 0)
    }

}
