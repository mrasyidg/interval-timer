package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.intervaltimerapp.R


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(id.ac.ui.intervaltimerapp.R.xml.preferences)
    }
}