package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intervaltimerapp.R
import id.ac.ui.intervaltimerapp.R
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Home"
    }
}