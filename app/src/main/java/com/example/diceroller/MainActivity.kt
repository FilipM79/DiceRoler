package com.example.diceroller

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding  //defining the binding class
    private var dice1Selected = false
    private var dice2Selected = false
    private var dice3Selected = false
    private var dice4Selected = false
    private var dice5Selected = false
    private var dice6Selected = false
    private var rollCounter = 3
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root)
        
        setStartImages()
        binding.message.text = getString(R.string.start_play_message)
        
        binding.btnRoll.setOnClickListener {
        if(rollCounter > 0) {
                rollUnselectedDices()
            }
        }
        allDicesSetOnClickListener()
        Log.d("MainAct1", "Roll counter: $rollCounter")
        
        binding.reset.setOnClickListener {
            if (rollCounter == 0) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
        
    }
    
    private fun setStartImages() {
        binding.ivDice1.setImageResource(R.drawable.dice_1)
        binding.ivDice2.setImageResource(R.drawable.dice_2)
        binding.ivDice3.setImageResource(R.drawable.dice_3)
        binding.ivDice4.setImageResource(R.drawable.dice_4)
        binding.ivDice5.setImageResource(R.drawable.dice_5)
        binding.ivDice6.setImageResource(R.drawable.dice_6)
        
        binding.ivDice1.imageAlpha = 50
        binding.ivDice2.imageAlpha = 50
        binding.ivDice3.imageAlpha = 50
        binding.ivDice4.imageAlpha = 50
        binding.ivDice5.imageAlpha = 50
        binding.ivDice6.imageAlpha = 50
    }
    
    private fun makeRandomDrawableResource(dice: Dice): Int {
        return when (dice.roll()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun rollUnselectedDices() {
        // Create new Dice object with 6 sides and roll the dice
    
        binding.ivDice1.imageAlpha = 255
        binding.ivDice2.imageAlpha = 255
        binding.ivDice3.imageAlpha = 255
        binding.ivDice4.imageAlpha = 255
        binding.ivDice5.imageAlpha = 255
        binding.ivDice6.imageAlpha = 255
        
        rollCounter--
        binding.tvNumOfRollsLeft.text = buildString {
        append("You have ")
        append(rollCounter)
        append(" rolls left.")
    
        binding.message.text = when (rollCounter) {
            3 -> "Press Roll to start"
            in 1..2 -> "Select Dices and press Roll"
            else -> "Press the Reset button"
        }
    }
        if (!dice1Selected) {
            binding.ivDice1.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
        if (!dice2Selected) {
            binding.ivDice2.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
        if (!dice3Selected) {
            binding.ivDice3.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
        if (!dice4Selected) {
            binding.ivDice4.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
        if (!dice5Selected) {
            binding.ivDice5.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
        if (!dice6Selected) {
            binding.ivDice6.setImageResource(makeRandomDrawableResource(Dice(6)))
        }
    }
    
    private fun diceSelector(diceImageView: ImageView, _selected: Boolean) : Boolean {
        var selected = _selected
        diceImageView.setBackgroundColor(Color.YELLOW)
    
        selected = if (!selected) {
            diceImageView.setBackgroundColor(Color.YELLOW)
            true
        } else {
            diceImageView.setBackgroundColor(Color.TRANSPARENT)
            false
        }
        
        return selected
    }
    
    private fun allDicesSetOnClickListener() {
        binding.ivDice1.setOnClickListener {
            if(rollCounter < 3) {
                dice1Selected = diceSelector(binding.ivDice1, dice1Selected)
            }
        }
        binding.ivDice2.setOnClickListener {
            if(rollCounter < 3) {
                dice2Selected = diceSelector(binding.ivDice2, dice2Selected)
            }
        }
        binding.ivDice3.setOnClickListener {
            if(rollCounter < 3) {
                dice3Selected = diceSelector(binding.ivDice3, dice3Selected)
            }
        }
        binding.ivDice4.setOnClickListener {
            if(rollCounter < 3) {
                dice4Selected = diceSelector(binding.ivDice4, dice4Selected)
            }
        }
        binding.ivDice5.setOnClickListener {
            if(rollCounter < 3) {
                dice5Selected = diceSelector(binding.ivDice5, dice5Selected)
            }
        }
        binding.ivDice6.setOnClickListener {
            if(rollCounter < 3) {
                dice6Selected = diceSelector(binding.ivDice6, dice6Selected)
            }
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random(Random)
    }
}