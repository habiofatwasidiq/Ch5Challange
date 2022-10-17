package com.example.ch4challange.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch4challange.databinding.ActivityMenuGameBinding
import com.example.ch4challange.R
import com.example.ch4challange.ui.game.MainActivity

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
            MainActivity.startActivity(this,false)
        }
        binding.ivVsCom.setOnClickListener {
            MainActivity.startActivity(this,true)
        }
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