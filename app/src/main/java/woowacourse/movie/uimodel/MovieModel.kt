package woowacourse.movie.uimodel

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Name
import woowacourse.movie.domain.movie.ScreeningPeriod
import java.io.Serializable

data class MovieModel(
    val name: Name,
    val posterImage: Int?,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val description: String
) : Serializable {

    fun toDomain(): Movie {
        return Movie(
            name = name,
            screeningPeriod = screeningPeriod,
            runningTime = runningTime,
            description = description
        )
    }

    companion object {
        const val MOVIE_INTENT_KEY = "woowacourse/movie/domain/movie"
    }
}
