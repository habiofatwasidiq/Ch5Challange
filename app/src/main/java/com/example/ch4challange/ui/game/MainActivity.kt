package com.example.ch4challange.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ch4challange.R
import com.example.ch4challange.databinding.ActivityMainBinding
import com.example.ch4challange.enum.PlayerChar
import com.example.ch4challange.usecase.GetResultUseCase
import com.google.android.material.snackbar.Snackbar
import com.bumptech.glide.Glide
import com.example.ch4challange.enum.PlayerSide
import com.example.ch4challange.ui.dialog.DialogUtils
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var playerOne: Int = PlayerChar.ROCK.ordinal
    private var playerTwo: Int = PlayerChar.ROCK.ordinal

    private var multiplayerMode: Int = PLAYER_VS_COM

    private lateinit var onResultGame: ResultPlayer

    val name by lazy {
        intent.getStringExtra(EXTRAS_PLAYER_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        multiplayerMode = intent.extras?.getInt(EXTRAS_MULTIPLAYER_MODE) ?: 0
        onResultGame = GetResultUseCase()

        setOnCLickListeners()
        supportActionBar?.hide()
        imgLoad()

        with(binding) {
            Snackbar.make(
                root,
                "${name?.replaceFirstChar { it.uppercase() }} Turn",
                Snackbar.LENGTH_SHORT
            ).show()
            ivRefresh.setOnClickListener {
                resetGame()
                Snackbar.make(
                    root,
                    "${name?.replaceFirstChar { it.uppercase() }} Turn",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            tvPlayer1.text = "${name?.replaceFirstChar { it.uppercase() }}"
        }
    }

    private fun imgLoad() {
        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding.ivLogoTitle)
    }

    private fun showDialogResult() {
        when (onResultGame.onResultPlayer(this.playerOne, this.playerTwo)) {
            GetResultUseCase.DRAW -> {
                DialogUtils.showInputNameDialog(context = this, "SERI!") { dialog, value ->
                    dialog?.dismiss()
                    if (value == "back") {
                        finish()
                    } else {
                        resetGame()
                        Snackbar.make(
                            binding.root,
                            "${name?.replaceFirstChar { it.uppercase() }} Turn",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            GetResultUseCase.PLAYER_ONE_WINNER -> {
                DialogUtils.showInputNameDialog(
                    context = this,
                    "${name?.replaceFirstChar { it.uppercase() }} MENANG!"
                ) { dialog, value ->
                    dialog?.dismiss()
                    if (value == "back") {
                        finish()
                    } else {
                        resetGame()
                        Snackbar.make(
                            binding.root,
                            "${name?.replaceFirstChar { it.uppercase() }} Turn",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            GetResultUseCase.PLAYER_TWO_WINNER -> {
                DialogUtils.showInputNameDialog(
                    context = this,
                    "Pemain 2 \n MENANG!"
                ) { dialog, value ->
                    dialog?.dismiss()
                    if (value == "back") {
                        finish()
                    } else {
                        resetGame()
                        Snackbar.make(
                            binding.root,
                            "${name?.replaceFirstChar { it.uppercase() }} Turn",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun resetGame() {
        if (multiplayerMode == PLAYER_VS_PLAYER) {
            binding.btnSelectUser.visibility = View.INVISIBLE
            binding.btnSelectPlayer2.visibility = View.INVISIBLE
        }
        binding.apply {
            ivRockLeft.isEnabled = true
            ivPaperLeft.isEnabled = true
            ivScissorLeft.isEnabled = true

            ivRockLeft.setImageResource(R.drawable.ic_rock)
            ivPaperLeft.setImageResource(R.drawable.ic_paper)
            ivScissorLeft.setImageResource(R.drawable.ic_scissor)
            ivRockRight.setImageResource(R.drawable.ic_rock_com)
            ivPaperRight.setImageResource(R.drawable.ic_paper_com)
            ivScissorRight.setImageResource(R.drawable.ic_scissor_com)
        }
    }

    private fun setOnCLickListeners() {
        if (multiplayerMode == PLAYER_VS_PLAYER) {
            binding.apply {
                ivRockRight.isEnabled = false
                ivPaperRight.isEnabled = false
                ivScissorRight.isEnabled = false

                btnSelectPlayer2.setOnClickListener {
                    ivRockRight.isEnabled = false
                    ivPaperRight.isEnabled = false
                    ivScissorRight.isEnabled = false
                    btnSelectPlayer2.visibility = View.INVISIBLE
                    showDialogResult()
                }
            }
            binding.ivRockLeft.setOnClickListener {
                playerOne = PlayerChar.ROCK.ordinal
                setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                binding.apply {
                    btnSelectUser.visibility = View.VISIBLE
                    btnSelectUser.setOnClickListener {
                        ivRockRight.isEnabled = true
                        ivPaperRight.isEnabled = true
                        ivScissorRight.isEnabled = true
                        ivRockLeft.isEnabled = false
                        ivPaperLeft.isEnabled = false
                        ivScissorLeft.isEnabled = false
                        btnSelectUser.visibility = View.INVISIBLE
                        btnSelectPlayer2.visibility = View.VISIBLE

                        Snackbar.make(binding.root, "Player 2 Turn", Snackbar.LENGTH_SHORT).show()
                        ivRockLeft.setImageResource(
                            R.drawable.ic_rock
                        )
                    }
                }
            }
            binding.ivPaperLeft.setOnClickListener {
                playerOne = PlayerChar.PAPER.ordinal
                setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                binding.apply {
                    btnSelectUser.visibility = View.VISIBLE
                    btnSelectUser.setOnClickListener {
                        ivRockRight.isEnabled = true
                        ivPaperRight.isEnabled = true
                        ivScissorRight.isEnabled = true
                        ivRockLeft.isEnabled = false
                        ivPaperLeft.isEnabled = false
                        ivScissorLeft.isEnabled = false
                        btnSelectUser.visibility = View.INVISIBLE
                        btnSelectPlayer2.visibility = View.VISIBLE

                        Snackbar.make(binding.root, "Player 2 Turn", Snackbar.LENGTH_SHORT).show()
                        ivPaperLeft.setImageResource(
                            R.drawable.ic_paper
                        )
                    }
                }
            }
            binding.ivScissorLeft.setOnClickListener {
                playerOne = PlayerChar.SCISSOR.ordinal
                setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                binding.apply {
                    btnSelectUser.visibility = View.VISIBLE
                    btnSelectUser.setOnClickListener {
                        ivRockRight.isEnabled = true
                        ivPaperRight.isEnabled = true
                        ivScissorRight.isEnabled = true
                        ivRockLeft.isEnabled = false
                        ivPaperLeft.isEnabled = false
                        ivScissorLeft.isEnabled = false
                        btnSelectUser.visibility = View.INVISIBLE
                        btnSelectPlayer2.visibility = View.VISIBLE

                        Snackbar.make(binding.root, "Player 2 Turn", Snackbar.LENGTH_SHORT).show()
                        ivScissorLeft.setImageResource(
                            R.drawable.ic_scissor
                        )
                    }
                }
            }
            binding.apply {
                ivRockRight.setOnClickListener {
                    playerTwo = PlayerChar.ROCK.ordinal
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                }
                ivPaperRight.setOnClickListener {
                    playerTwo = PlayerChar.PAPER.ordinal
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                }
                ivScissorRight.setOnClickListener {
                    playerTwo = PlayerChar.SCISSOR.ordinal
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                }
            }
        } else {
            binding.apply {
                ivRockLeft.setOnClickListener {
                    Snackbar.make(
                        binding.root,
                        "${name?.replaceFirstChar { it.uppercase() }} Choose Rock",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    playerOne = PlayerChar.ROCK.ordinal
                    playerTwo = PlayerChar.values()[Random.nextInt(0, 3)].ordinal
                    setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                    showDialogResult()
                }
                ivPaperLeft.setOnClickListener {
                    Snackbar.make(
                        binding.root,
                        "${name?.replaceFirstChar { it.uppercase() }} Choose Paper",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    playerOne = PlayerChar.PAPER.ordinal
                    playerTwo = PlayerChar.values()[Random.nextInt(0, 3)].ordinal
                    setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                    showDialogResult()
                }
                ivScissorLeft.setOnClickListener {
                    Snackbar.make(
                        binding.root,
                        "${name?.replaceFirstChar { it.uppercase() }} Choose Scissor",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    playerOne = PlayerChar.SCISSOR.ordinal
                    playerTwo = PlayerChar.values()[Random.nextInt(0, 3)].ordinal
                    setPlayerMovement(PlayerSide.PLAYER_ONE, playerOne)
                    setPlayerMovement(PlayerSide.PLAYER_TWO, playerTwo)
                    showDialogResult()
                }
                ivRefresh.visibility = View.GONE
            }
        }
    }

    private fun setPlayerMovement(playerSide: PlayerSide, playerChar: Int) {
        if (playerSide == PlayerSide.PLAYER_ONE && playerChar == PlayerChar.ROCK.ordinal) {
            binding.apply {
                ivRockLeft.setImageResource(
                    R.drawable.ic_rock_bg
                )
                ivPaperLeft.setImageResource(
                    R.drawable.ic_paper
                )
                ivScissorLeft.setImageResource(
                    R.drawable.ic_scissor
                )
            }
        } else if (playerSide == PlayerSide.PLAYER_ONE && playerChar == PlayerChar.PAPER.ordinal) {
            binding.apply {
                ivRockLeft.setImageResource(
                    R.drawable.ic_rock
                )
                ivPaperLeft.setImageResource(
                    R.drawable.ic_paper_bg
                )
                ivScissorLeft.setImageResource(
                    R.drawable.ic_scissor
                )
            }
        } else if (playerSide == PlayerSide.PLAYER_ONE && playerChar == PlayerChar.SCISSOR.ordinal) {
            binding.apply {
                ivRockLeft.setImageResource(
                    R.drawable.ic_rock
                )
                ivPaperLeft.setImageResource(
                    R.drawable.ic_paper
                )
                ivScissorLeft.setImageResource(
                    R.drawable.ic_scissor_bg
                )
            }
        } else if (playerSide == PlayerSide.PLAYER_TWO && playerChar == PlayerChar.ROCK.ordinal) {
            binding.apply {
                ivRockRight.setImageResource(
                    R.drawable.ic_rock_bg
                )
                ivPaperRight.setImageResource(
                    R.drawable.ic_paper_com
                )
                ivScissorRight.setImageResource(
                    R.drawable.ic_scissor_com
                )
            }
        } else if (playerSide == PlayerSide.PLAYER_TWO && playerChar == PlayerChar.PAPER.ordinal) {
            binding.apply {
                ivRockRight.setImageResource(
                    R.drawable.ic_rock_com
                )
                ivPaperRight.setImageResource(
                    R.drawable.ic_paper_bg
                )
                ivScissorRight.setImageResource(
                    R.drawable.ic_scissor_com
                )
            }
        } else if (playerSide == PlayerSide.PLAYER_TWO && playerChar == PlayerChar.SCISSOR.ordinal) {
            binding.apply {
                ivRockRight.setImageResource(
                    R.drawable.ic_rock_com
                )
                ivPaperRight.setImageResource(
                    R.drawable.ic_paper_com
                )
                ivScissorRight.setImageResource(
                    R.drawable.ic_scissor_bg
                )
            }
        }
    }

    companion object {
        const val EXTRAS_MULTIPLAYER_MODE = "EXTRAS_MULTIPLAYER_MODE"
        const val EXTRAS_PLAYER_NAME = "EXTRAS_PLAYER_NAME"

        const val PLAYER_VS_COM = 0
        const val PLAYER_VS_PLAYER = 1
    }
}

interface ResultPlayer {
    fun onResultPlayer(playerOne: Int, playerTwo: Int): Int
}