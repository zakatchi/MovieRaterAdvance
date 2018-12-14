package sit.it2107.nyp.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MovieListAdapter : ArrayAdapter<MovieEntity> {
    var mContext : Context
    var mResource : Int

    constructor(
        context: Context,
        resource: Int,
        objects: ArrayList<MovieEntity>
    ) : super(context, resource, objects) {
        this.mContext = context
        this.mResource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var title : String = getItem(position).title
        var desc : String = getItem(position).desc
        var language : String = getItem(position).language
        var date : String = getItem(position).date
        var suitable : String = getItem(position).suitable
        var review1 : String = getItem(position).review1
        var review2 : String = getItem(position).review2

        var tempMovie = MovieEntity(title, desc, language, date, suitable, review1, review2)

        var convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false)

        var displayMovieName = convertView.findViewById<TextView>(R.id.movieName)
        displayMovieName.setText(title)

        return convertView
    }
}