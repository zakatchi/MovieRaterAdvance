package sit.it2107.nyp.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.activity_edit_review.*

class edit_review : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_review)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<MovieEntity>("key")
        var movieIndex = intent.getIntExtra("index", -1)

        val checkBoxFirst = findViewById<CheckBox>(R.id.edit_checkbox1)
        val checkBoxSecond = findViewById<CheckBox>(R.id.edit_checkbox2)
        val checkBoxThird = findViewById<CheckBox>(R.id.edit_checkbox3)

        findViewById<EditText>(R.id.edit_title).setText(movie.title)
        findViewById<EditText>(R.id.edit_desc).setText(movie.desc)
        findViewById<EditText>(R.id.edit_date).setText(movie.date)


        checkBoxFirst?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxSecond.setVisibility(View.VISIBLE)
                checkBoxThird.setVisibility(View.VISIBLE)



            } else {
                checkBoxSecond.setVisibility(View.INVISIBLE)
                checkBoxThird.setVisibility(View.INVISIBLE)
            }
        }

        //No Logic for intermediate
        //Only for advance separately
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_for_edit_review, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_update) {
            var edit_TITLE : String = edit_title.text.toString()
            var edit_DESC : String = edit_desc.text.toString()
            var edit_DATE : String = edit_date.text.toString()

            var movieDATA = intent.getParcelableExtra<MovieEntity>("key")
            var movieIndex = intent.getIntExtra("index", -1)
            var movie = MovieEntity(edit_TITLE, edit_DESC, movieDATA.language, edit_DATE, movieDATA.suitable, movieDATA.review1, movieDATA.review2)
            Movies.setMovie(movieIndex, movie)

            val intent = Intent(this, movie_overview::class.java)
            intent.putExtra("key", movie)
            intent.putExtra("index", movieIndex)
            startActivity(intent)

        } else if (item?.itemId == R.id.menu_no_update) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}