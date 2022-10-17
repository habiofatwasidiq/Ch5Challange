package com.example.ch4challange.ui.onboarding.entername

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ch4challange.R
import com.example.ch4challange.databinding.FragmentEnterNameBinding
import com.example.ch4challange.ui.game.MainActivity
import com.example.ch4challange.ui.menu.MenuGameActivity
import com.example.ch4challange.ui.onboarding.OnFinishNavigateListener

class EnterNameFragment : Fragment(),OnFinishNavigateListener {

    private lateinit var binding: FragmentEnterNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onFinishNavigateListener() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Please input your name !", Toast.LENGTH_SHORT).show()
        } else {
            navigateToMenu(name)
        }
    }
    private fun navigateToMenu(name: String) {
        MenuGameActivity.startActivity(requireContext(),name)
    }

}