package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter
import java.time.LocalDate

class MoviesActivity : AppCompatActivity() {

    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesView()
    }

    private fun initMoviesView() {
        moviesListView.adapter = MoviesAdapter(this, getMovies())
    }

    private fun getMovies() = listOf(
        Movie(
            name = "해리포터",
            posterImage = null,
            screeningDate = LocalDate.of(2000, 10, 1),
            runningTime = 120,
            description = "마법영화"
        )
    )
}