package sit.it2107.nyp.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_submit_review.*
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast


class submit_review : AppCompatActivity() {

    var title_string_buffer=""
    var overview_string_buffer=""
    var language_string_buffer=""
    var date_string_buffer=""
    var suitable_string_buffer=""
    var rating_float: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_review)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val reuse = intent.extras.get("submitKey") as MovieEntity
        title_string_buffer = reuse.title
        overview_string_buffer = reuse.desc
        language_string_buffer = reuse.language
        date_string_buffer = reuse.date
        suitable_string_buffer = reuse.suitable

        ratingTEXT.text = "Enter your review for the movie: " +reuse.title
        val ratingBar = findViewById(R.id.simpleRatingBar) as RatingBar

        ratingBar.onRatingBarChangeListener =
                OnRatingBarChangeListener { ratingBar, rating, fromUser -> rating_float = rating.toString() }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_for_submit_review, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.submit_review_to_overview) {
            val review2_new = findViewById(R.id.submit_review2) as EditText
            val index = intent.getIntExtra("index", -1)
            val obj_back_to_overview = MovieEntity(title_string_buffer, overview_string_buffer, language_string_buffer, date_string_buffer, suitable_string_buffer, rating_float, review2_new.text.toString())
            val intent = Intent(this, movie_overview::class.java)
            intent.putExtra("key", obj_back_to_overview)
            intent.putExtra("index", index)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
