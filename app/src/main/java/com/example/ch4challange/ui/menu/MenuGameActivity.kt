package com.example.ch4challange.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch4challange.databinding.ActivityMenuGameBinding
import com.example.ch4challange.R
import com.example.ch4challange.ui.game.MainActivity
import com.example.ch4challange.ui.game.MainActivity.Companion.EXTRAS_MULTIPLAYER_MODE
import com.example.ch4challange.ui.game.MainActivity.Companion.EXTRAS_PLAYER_NAME
import com.example.ch4challange.ui.game.MainActivity.Companion.PLAYER_VS_COM
import com.example.ch4challange.ui.game.MainActivity.Companion.PLAYER_VS_PLAYER

class MenuGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuGameBinding

    private val name: String? by lazy {
        intent.getStringExtra(EXTRAS_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setNameOnTitle()
        setMenuClickListeners()
    }

    private fun setMenuClickListeners() {
        binding.ivVsPlayer.setOnClickListener {
            navigateToGame(mode = PLAYER_VS_PLAYER)
        }
        binding.ivVsCom.setOnClickListener {
            navigateToGame(mode = PLAYER_VS_COM)
        }
    }
    private fun navigateToGame(mode: Int) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRAS_PLAYER_NAME, name)
            putExtra(EXTRAS_MULTIPLAYER_MODE, mode)
        }
        startActivity(intent)
    }


    private fun setNameOnTitle() {
        binding.tvTextMenuVsPlayer.text = getString(R.string.placeholder_text_menu_vs_player, name)
        binding.tvTextMenuVsCom.text = getString(R.string.placeholder_text_menu_vs_com, name)
    }

    companion object {
        private const val EXTRAS_NAME = "EXTRAS_NAME"

        fun startActivity(context: Context, name: String) {
            context.startActivity(Intent(context,MenuGameActivity::class.java).apply {
                putExtra(EXTRAS_NAME,name)
            })
        }
    }
}