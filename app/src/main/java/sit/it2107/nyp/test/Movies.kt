package sit.it2107.nyp.test

import android.graphics.Movie

class Movies {
    companion object MovieEntities {
        var movie_entities = arrayListOf<MovieEntity>()
        var count : Int = 0

        fun create() : Movies = Movies()

        @JvmStatic
        fun addMovieEntity(movie : MovieEntity) {
            movie_entities.add(movie)
            count += 1
        }

        @JvmStatic
        fun returnMovieEntities() : ArrayList<MovieEntity> {
            return movie_entities
        }

        @JvmStatic
        fun setMovie(index : Int, movie : MovieEntity) {
            movie_entities.set(index, movie)
        }
    }
}