package com.example.easy_quiz.quiz

object QuizStorage {
    fun getQuiz(locale: Locale): Quiz = when (locale) {
        Locale.Ru -> quizRu
        Locale.En -> quizEn
    }

    fun answer(quiz: Quiz, answers: List<Int>): String = quiz
        .questions
        .zip(answers) { question, index -> question.feedback[index] }
        .joinToString(separator = " ")

    enum class Locale {
        Ru, En
    }

    private val quizRu = object : Quiz {
        override val questions: List<Question> = listOf(
            Question(
                question = "Как вам наш продукт?",
                answers = listOf(
                    "на 2",
                    "на 3",
                    "на 4",
                    "на 5",
                ),
                feedback = listOf(
                    "Вам не нравится наш продукт.",
                    "Вас удовлетворяет наш продукт.",
                    "Вы оценили наш продукт хорошо.",
                    "Вы оценили наш продукт отлично.",
                ),
            ),
            Question(
                question = "Порекомендуете его друзьям?",
                answers = listOf(
                    "Обзательно",
                    "Уже порекомендовал",
                    "Посмотрим, что будет дальше",
                    "Нет",
                ),
                feedback = listOf(
                    "Вы готовы порекомендовать наш продукт.",
                    "Вы уже порекомендовали продукт своим друзьям.",
                    "Вам нужно больше времени, чтобы порекомендовать наш продукт.",
                    "Вы не готовы рекомендовать наш продукт.",
                ),
            ),
            Question(
                question = "Пользовались ли вы обратной связью?",
                answers = listOf(
                    "Да",
                    "Еще нет, но сейчас воспользуюсь",
                    "Нет, еще не приходилось",
                    "Нет, и не буду",
                ),
                feedback = listOf(
                    "Спасибо за обратную связь!",
                    "Спасибо за обратную связь!",
                    "Ждем вашу обратную связь!",
                    "Мы надеемся, что у вас пояится возможность дать нам обратную связь.",
                ),
            ),
        )
    }

    private val quizEn = object : Quiz {
        override val questions: List<Question> = listOf(
            Question(
                question = "Please, rate our product",
                answers = listOf(
                    "F",
                    "C",
                    "B",
                    "A++",
                ),
                feedback = listOf(
                    "You have rated us F",
                    "You have rated us C",
                    "You have rated us B",
                    "You have rated us A++",
                ),
            ),
            Question(
                question = "Would you recommend us to your friends?",
                answers = listOf(
                    "Sure",
                    "Already recommend",
                    "I don't know",
                    "No",
                ),
                feedback = listOf(
                    "You want to recommend our product",
                    "You already recommend our product",
                    "You need more time to make recommendations",
                    "You are not ready to recommend",
                ),
            ),
            Question(
                question = "Have you gave us your feedback yet?",
                answers = listOf(
                    "Yes",
                    "Not yet, but now I will use it",
                    "No, it hasn't happened yet",
                    "No, and I won't",
                ),
                feedback = listOf(
                    "Thank you for your feedback!",
                    "Thank you for your feedback!",
                    "Waiting for your feedback!",
                    "We hope you will have the opportunity to give us your feedback!",
                ),
            ),
        )
    }
}
