package com.example.easy_quiz.presentation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.easy_quiz.R
import com.example.easy_quiz.databinding.FragmentResultBinding

private const val ANSWER = "answer"

class ResultFragment : Fragment() {
    private var answer: String? = null
    private var _bind: FragmentResultBinding? = null
    private val bind get() = _bind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answer = it.getString(ANSWER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        findNavController().enableOnBackPressed(enabled = false)
        _bind = FragmentResultBinding.inflate(inflater)
        bind.continueButtonResults.setOnClickListener() {
            animatorFinish()
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment2)
        }
        animatorStart()
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.tResults.text = answer
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }

    private fun animatorStart() {
        bind.lottieView.translationY = 250f
        bind.lottieView.animate().apply {
            startDelay = 2200L
            translationY(0f)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            startDelay = 2600L
            setTarget(bind.tResults)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            startDelay = 2600L
            setTarget(bind.continueButtonResults)
            start()
        }
    }

    private fun animatorFinish() {
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.continueButtonResults)
            start()
        }
    }
}
