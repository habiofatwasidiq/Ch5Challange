package com.example.ch4challange.ui.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch4challange.R
import com.example.ch4challange.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.ivRefresh.setOnClickListener{
            val intent = intent
            finish()
            startActivity(intent)
        }


            binding.apply {
                binding.ivRockLeft.setOnClickListener {
                    binding.ivRockLeft.setImageResource(R.drawable.ic_rock_bg)
                    checkGameResult("rock")
                }

                binding.ivPaperLeft.setOnClickListener{
                    binding.ivPaperLeft.setImageResource(R.drawable.ic_paper_bg)
                    checkGameResult("paper")
                }

                binding.ivScissorLeft.setOnClickListener {
                    binding.ivScissorLeft.setImageResource(R.drawable.ic_scissor_bg)
                    checkGameResult("scissor")
                }
            }

    }

    private fun checkGameResult(player: String): String {
        var com = ""
        val randomChoice = Random
        val choiceCom = randomChoice.nextInt(3) +1

        if (choiceCom == 1) {
            com = "rock"
        } else if (choiceCom == 2) {
            com = "paper"
        } else if (choiceCom == 3) {
            com = "scissor"
        }

        if (com == "rock") {
            binding.ivRockRight.setImageResource(R.drawable.ic_rock_bg)
        } else if (com == "paper") {
            binding.ivPaperRight.setImageResource(R.drawable.ic_paper_bg)
        } else if (com == "scissor") {
            binding.ivScissorRight.setImageResource(R.drawable.ic_scissor_bg)
        }

        val comWin = binding.tvResult.setText(getString(R.string.comwin)).toString()

        return if (com == player) {
            binding.tvResult.setText(getString(R.string.draw)).toString()
        } else if (player == "rock" && com == "scissor") {
            binding.tvResult.setText(getString(R.string.player1win)).toString()
        } else if (player == "scissor" && com == "paper") {
            binding.tvResult.setText(getString(R.string.player1win)).toString()
        } else if (player == "paper" && com == "rock") {
            binding.tvResult.setText(getString(R.string.player1win)).toString()
        } else if (player == "rock" && com == "paper") {
            comWin
        } else if (player == "paper" && com == "scissor") {
            comWin
        } else {
            comWin
        }

    }
    companion object {
        private const val EXTRAS_MULTIPLAYER_MODE = "EXTRAS_MULTIPLAYER_MODE"

        fun startActivity(context: Context, isUsingMultiplayerMode: Boolean) {
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRAS_MULTIPLAYER_MODE, isUsingMultiplayerMode)
            })
        }
    }

}