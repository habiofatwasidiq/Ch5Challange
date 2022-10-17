package com.example.ch4challange.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ch4challange.R
import com.example.ch4challange.R.string.text_desc_onboarding_1
import com.example.ch4challange.ui.onboarding.entername.EnterNameFragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro2(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setupSliderFragment()
    }


    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if (currentFragment is OnFinishNavigateListener){
            currentFragment.onFinishNavigateListener()
        }
    }

    private fun setupSliderFragment(){
        isSkipButtonEnabled = false
        addSlide(AppIntroFragment.createInstance(
            description = getString(R.string.text_title_onboarding_1),
            descriptionColorRes = R.color.black,
            imageDrawable = R.drawable.ic_landing_page1

        ))
        addSlide(AppIntroFragment.createInstance(
            description = getString(R.string.text_title_onboarding_2),
            descriptionColorRes = R.color.black,
            imageDrawable = R.drawable.ic_landing_page2

        ))
        addSlide(EnterNameFragment())

    }
}
interface OnFinishNavigateListener {
    fun onFinishNavigateListener()
}