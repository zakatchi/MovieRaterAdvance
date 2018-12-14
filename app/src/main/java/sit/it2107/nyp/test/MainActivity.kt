package sit.it2107.nyp.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val movies = Movies.returnMovieEntities()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listView : ListView = findViewById<ListView>(R.id.list_view)
        var adapter = MovieListAdapter(this, R.layout.list_view, movies)

        listView.adapter = adapter

        registerForContextMenu(list_view)

        list_view.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, movie_overview::class.java).apply {
                putExtra("key", movies.get(position))
                putExtra("index", position)
            }
            startActivity(intent)
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == 1001) {
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            var index : Int = info.position
            val intent = Intent(this, edit_review::class.java).apply {
                putExtra("key", movies.get(index))
                putExtra("index", index)
            }
            startActivity(intent)
        }

        return super.onContextItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

        if(v?.id== R.id.list_view){
            menu?.add(1, 1001, 1, "Edit")
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_for_landing_page, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.landing_page_add) {
            val intent = Intent(this, add_movie::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}