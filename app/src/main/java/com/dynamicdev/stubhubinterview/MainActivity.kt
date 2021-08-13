package com.dynamicdev.stubhubinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.dynamicdev.stubhubinterview.databinding.ActivityMainBinding
import com.dynamicdev.stubhubinterview.view.PhotoFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PhotoFragment())
                .commit()
    }
}

/**
 * Extension function to handle error display
 * Could be a Dialog
 */
fun FragmentActivity.showAToast(error: String){
    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
}