package sit.it2107.nyp.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.content.Intent



class add_movie : AppCompatActivity() {
    //global var
    var reason_array = arrayListOf<String>()
    var checkbox_rating_buffer: String = "true"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val checkBoxFirst = findViewById<CheckBox>(R.id.checkbox1)
        val checkBoxSecond = findViewById<CheckBox>(R.id.checkbox2)
        val checkBoxThird = findViewById<CheckBox>(R.id.checkbox3)
        checkBoxFirst?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBoxSecond.setVisibility(View.VISIBLE)
                checkBoxThird.setVisibility(View.VISIBLE)
                checkbox_rating_buffer = "false"

                checkBoxSecond?.setOnCheckedChangeListener { buttonView2, isChecked2 ->
                    if(isChecked2) {
                        reason_array.add("Violence")
                    }
                    else {
                        reason_array.remove("Violence")
                    }

                }

                checkBoxThird?.setOnCheckedChangeListener { buttonView3, isChecked3 ->
                    if(isChecked3) {
                        reason_array.add("Language")
                    }
                    else {
                        reason_array.remove("Language")
                    }

                }
            } else {
                checkBoxSecond.setVisibility(View.INVISIBLE)
                checkBoxThird.setVisibility(View.INVISIBLE)
                checkbox_rating_buffer = "true"
            }
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_for_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        val titleValid = findViewById(R.id.title) as EditText
        val descValid = findViewById(R.id.desc) as EditText
        val dateValid = findViewById(R.id.date) as EditText
        val radioAnswer = findViewById(R.id.radioResults) as RadioGroup
        var language_buffer: String = ""




        if(item?.itemId == R.id.add_fun) {
            val titleStr: String = titleValid.text.toString()
            val descStr: String = descValid.text.toString()
            val dateStr: String = dateValid.text.toString()
            val radioStr: Int = radioAnswer.checkedRadioButtonId





            //Converting languages to string(fromt Int)
            if(radioStr == 2131165305)
                language_buffer = "English"
            else if(radioStr == 2131165306)
                language_buffer = "Chinese"
            else if(radioStr == 2131165307)
                language_buffer = "Malay"
            else if(radioStr == 2131165308)
                language_buffer = "Tamil"

            if (titleStr.isEmpty()) {
                titleValid.setError("Please enter movie title!")

                if (descStr.isEmpty()) {
                    descValid.setError("Please enter movie description!")

                    if (dateStr.isEmpty()) {
                        dateValid.setError("Field empty")
                    }
                }
            } else {
                if (descStr.isEmpty()) {
                    descValid.setError("Please enter movie description!")

                    if (dateStr.isEmpty()) {
                        dateValid.setError("Field empty")
                    }
                } else {
                    if (dateStr.isEmpty()) {
                        dateValid.setError("Field empty")
                    } else {
                        //return values
                        Toast.makeText(applicationContext, "Title = "+titleStr
                                +"\n" + "Overview ="+descStr + "\n" + "Release date = " +dateStr
                                +"\n" + "Language = "+language_buffer + "\n" + "Suitable for all ages = " +checkbox_rating_buffer
                                +"\nReason: \n" +reason_array.joinToString(separator="\n"), Toast.LENGTH_SHORT).show()


                        val obj = MovieEntity(titleStr, descStr, language_buffer, dateStr, checkbox_rating_buffer)
                        val intent = Intent(this, movie_overview::class.java)
                        intent.putExtra("key", obj)
                        startActivity(intent)
                    }
                }
            }
        }
        else if (item?.itemId == R.id.clear_entries) {

            val refresh = Intent(this, add_movie::class.java)
            startActivity(refresh)
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}