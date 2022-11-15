package com.fcmb.sampletestingappwithcicd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.fcmb.sampletestingappwithcicd.roomdb.database.AppDatabase
import com.fcmb.sampletestingappwithcicd.roomdb.entity.Blog

class MainActivity : AppCompatActivity() {
    init {
        System.loadLibrary("keys")
    }

    private lateinit var saveButton: Button
    private lateinit var secondActivityButton: Button
    private lateinit var etBlogTitle: EditText
    private lateinit var displayDbText: TextView
    private lateinit var displayDbTextLiveData: MutableLiveData<String>

    private var db: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayDbTextLiveData = MutableLiveData()
        db = AppDatabase.getInstance(applicationContext)
        saveButton = findViewById(R.id.btn_save_blog)
        secondActivityButton = findViewById(R.id.button_second_activity)
        etBlogTitle = findViewById(R.id.et_blog_title)
        displayDbText = findViewById(R.id.tv_display_db_title)

        val dao = AppDatabase.getBlogDao()

        findViewById<TextView>(R.id.basekey).text = getBaseApi()
        findViewById<TextView>(R.id.facebook_key).text = getFacebookApiKey()

//        displayDbTextLiveData.observe(this) {
//            displayDbText.text = it
//        }

//        lifecycleScope.launch {
//            withContext(Dispatchers.IO) {
//                displayDbTextLiveData.postValue(
//                    dao?.getAllBlogs()?.last()?.title ?: ""
//                )
//            }
//        }
        getString(R.string.parse_application_id)

        secondActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        saveButton.setOnClickListener {
            val blogTitle = etBlogTitle.text.toString()
            val entity = Blog(
                0, blogTitle,
                "It is always an abstract class and from here you can " +
                    "return a room database builder instance to use though out application.",
                "Abishek"
            )
            dao?.insert(entity)
        }
    }

    external fun getFacebookApiKey(): String?
    external fun getBaseApi(): String?
}
