package com.example.roomdatabaseassignment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabaseassignment.data.User
import com.example.roomdatabaseassignment.data.UserViewModel

class AddUserFragment : Fragment() {

    var context: AppCompatActivity? = null

    private lateinit var mUSerViewModel: UserViewModel

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var userAge: EditText

    lateinit var storeFirstName: String
    lateinit var storeLastName: String
    lateinit var storeAge: Editable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)

        context = activity as AppCompatActivity

        var submitbtn = view.findViewById<Button>(R.id.addUser)

        firstName = view.findViewById(R.id.userFirstName)
        lastName = view.findViewById(R.id.userLastName)
        userAge = view.findViewById(R.id.userAge)

        mUSerViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        submitbtn.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        storeFirstName = firstName.text.toString()
        storeLastName = lastName.text.toString()
        storeAge = userAge.text

        if (inputCheck(storeFirstName, storeLastName, storeAge)) {
            // Create User Object
            val user = User(0, storeFirstName, storeLastName, Integer.parseInt(storeAge.toString()))
            // Add Data to Database
            mUSerViewModel.addUser(user)
            Toast.makeText(requireContext(), "User $storeFirstName Add Successful !", Toast.LENGTH_LONG)
                .show()
            //Navigate Bake after add data
            (context as AppCompatActivity).addReplaceFragment(fragment = MainFragment(),
                addFragment = false, R.id.fragmentContainer, false)

        } else
            Toast.makeText(requireContext(), "Please Fill Out All Fields", Toast.LENGTH_LONG).show()

    }

    private fun inputCheck(fName: String, lName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && age.isEmpty())
    }

}
