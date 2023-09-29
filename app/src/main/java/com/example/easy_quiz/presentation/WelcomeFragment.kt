package com.example.easy_quiz.presentation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easy_quiz.R
import com.example.easy_quiz.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

class WelcomeFragment : Fragment() {
    private lateinit var bind: FragmentWelcomeBinding
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat.getDateInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentWelcomeBinding.inflate(layoutInflater)
        animatorStart()
        bind.continueButtonWelcome.setOnClickListener { clickAction() }
        return bind.root
    }

    private fun clickAction() {
        val dateDialog = MaterialDatePicker.Builder.datePicker()
            .setTitleText(resources.getString(R.string.date_picker_title))
            .build()
        dateDialog.addOnPositiveButtonClickListener { timeInMillis -> calendar.timeInMillis = timeInMillis
            Snackbar.make(bind.continueButtonWelcome,
                "${resources.getString(R.string.date_picker_birthday_is_text)} ${dateFormat.format(calendar.time)}",
                Snackbar.LENGTH_LONG).show()
            animatorFinish()
            findNavController().navigate(R.id.action_welcomeFragment2_to_quizCardFragment)
        }
        dateDialog.show(parentFragmentManager, "DatePicker")
    }

    private fun animatorStart() {
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            setTarget(bind.continueButtonWelcome)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            setTarget(bind.appCompatImageView)
            start()
        }
    }

    private fun animatorFinish() {
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.continueButtonWelcome)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.appCompatImageView)
            start()
        }
    }

}