package com.example.roomdatabaseassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.roomdatabaseassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addReplaceFragment(MainFragment(), addFragment = true, R.id.fragmentContainer, false)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
           supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}

fun FragmentActivity.addReplaceFragment(
    fragment: Fragment,
    addFragment: Boolean,
    containerLayout: Int,
    backAllow: Boolean,
) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.setCustomAnimations(R.anim.slide_in,
        R.anim.fade_out,
        R.anim.fade_in,
        R.anim.slide_out)
    if (addFragment) {
        transaction.add(containerLayout, fragment)
    } else {
        transaction.replace(containerLayout, fragment)
    }
    if (backAllow) {
        transaction.addToBackStack(MainFragment().toString())
    } else {
        transaction.addToBackStack(null)
    }
    transaction.commit()

}