package com.heunyam.firstapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.heunyam.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val myNumberViewModel: MyNumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNumberViewModel.currentValue.observe(this) { value ->
            Log.d(TAG, "currentValue Data Changed: $value")
            binding.numberTextview.text = "$value"
        }

        binding.plusBotton.setOnClickListener(this)
        binding.minusBotton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val userInput = binding.userinputEdittext.text.toString().toInt()

        when(v?.id) {
            R.id.plusBotton ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            R.id.minusBotton ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}