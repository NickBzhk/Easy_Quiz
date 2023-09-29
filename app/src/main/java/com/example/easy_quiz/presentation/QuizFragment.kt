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
import com.example.easy_quiz.databinding.FragmentQuizBinding
import com.example.easy_quiz.quiz.QuizStorage
import java.util.Locale

class QuizFragment : Fragment() {

    companion object {
        private const val OPTION_1 = "paramOption1"
        private const val OPTION_2 = "paramOption2"
        private const val OPTION_3 = "paramOption3"
        private const val OPTION_4 = "paramOption4"
        private const val QUESTION = "paramQuestion"
        private const val IS_COMPLETE = "isComplete"
    }

    private var _bind: FragmentQuizBinding? = null
    private val bind get() = _bind!!

    private var index = 0

    private val quiz = if (Locale.getDefault().displayLanguage == "русский") {
        QuizStorage.getQuiz(QuizStorage.Locale.Ru)
    } else {
        QuizStorage.getQuiz(QuizStorage.Locale.En)
    }

    private val quizSize = quiz.questions.size
    private val answersSize = quiz.questions[0].answers.size - 1

    private var pQuestion: String? = null
    private var paramOption1: String? = null
    private var paramOption2: String? = null
    private var paramOption3: String? = null
    private var isComplete = false
    private var paramOption4: String? = null
    private var resultsList = mutableListOf<Int>()
    private val listButtons =
        mutableListOf<String?>(paramOption1, paramOption2, paramOption3, paramOption4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isComplete = it.getBoolean(IS_COMPLETE)
            pQuestion = it.getString(QUESTION)
            paramOption1 = it.getString(OPTION_1)
            paramOption2 = it.getString(OPTION_2)
            paramOption3 = it.getString(OPTION_3)
            paramOption4 = it.getString(OPTION_4)
        }
        index = 0
        resultsList.clear()
        isComplete = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentQuizBinding.inflate(inflater)
        bind.breakButtonQuizCard.setOnClickListener() {
            animatorFinish()
            findNavController().navigate(R.id.action_quizCardFragment_to_welcomeFragment2)
        }
        bind.continueButtonQuizCard.setOnClickListener() {
            if (index >= quizSize - 1) {
                isComplete = true
                openFragment(index)
            } else {
                index++
                openFragment(index)
            }
        }
        openFragment(0)
        animatorStart()
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // QuizFragment properties
    private fun openFragment(index: Int) {
        pQuestion = quiz.questions[index].question

        //  Assigning RadioButtons
        for (i: Int in 0..answersSize) {
            listButtons[i] = quiz.questions[index].answers[i]
        }

        if (!isComplete) {
            bind.tQuestion.text = pQuestion

            //  Assigning RadioButtons options
            bind.continueButtonQuizCard.isEnabled = false
            bind.rgOptionsGroup.clearCheck()
            bind.option1.text = listButtons[0]
            bind.option2.text = listButtons[1]
            bind.option3.text = listButtons[2]
            bind.option4.text = listButtons[3]

            //  Receiving results and collecting them into Mutable List "resultsList"
            val checkedId = bind.rgOptionsGroup.checkedRadioButtonId
            bind.rgOptionsGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == R.id.option1) {
                    resultsList.add(0)
                    bind.continueButtonQuizCard.isEnabled = true
                } else if (checkedId == R.id.option2) {
                    resultsList.add(1)
                    bind.continueButtonQuizCard.isEnabled = true
                } else if (checkedId == R.id.option3) {
                    resultsList.add(2)
                    bind.continueButtonQuizCard.isEnabled = true
                } else if (checkedId == R.id.option4) {
                    resultsList.add(3)
                    bind.continueButtonQuizCard.isEnabled = true
                } else {
                    bind.continueButtonQuizCard.isEnabled = false
                }
            }
        } else {

            openResults(resultsList.toList())
        }

    }

    private fun openResults(results: List<Int>) {
        animatorFinish()
        val bundle = Bundle().apply {
            putString("answer", QuizStorage.answer(quiz, results))
        }
        findNavController().navigate(R.id.action_quizCardFragment_to_resultFragment, bundle)
    }

    private fun animatorStart() {
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            setTarget(bind.rgOptionsGroup)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            setTarget(bind.breakButtonQuizCard)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_in) as AnimatorSet).apply {
            setTarget(bind.continueButtonQuizCard)
            start()
        }
    }

    private fun animatorFinish() {
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.rgOptionsGroup)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.breakButtonQuizCard)
            start()
        }
        (AnimatorInflater.loadAnimator(context, R.animator.soft_fade_out) as AnimatorSet).apply {
            setTarget(bind.continueButtonQuizCard)
            start()
        }
    }
}

