package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  //defining the binding class
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root)
        
        val rollButton = findViewById<Button>(R.id.btn_Roll)
        rollButton.setOnClickListener {
            rollDice()
        }
    
        // Do a dice roll when the app starts
        rollDice()
    }
    
    private fun makeDrawableResource(dice: Dice): Int {
        return when (dice.roll()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
    
    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // Create new Dice object with 6 sides and roll the dice
        val listOfDices = List<Dice>(6){Dice(6)}
        
        val listOfDiceImages = listOf(
            binding.ivDice1,
            binding.ivDice2,
            binding.ivDice3,
            binding.ivDice4,
            binding.ivDice5,
            binding.ivDice6,
        )
      
        for (i in 0..5) {
            listOfDiceImages[i].setImageResource(makeDrawableResource(Dice(6)))
            listOfDiceImages[i].contentDescription = listOfDices[i].roll().toString()
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}