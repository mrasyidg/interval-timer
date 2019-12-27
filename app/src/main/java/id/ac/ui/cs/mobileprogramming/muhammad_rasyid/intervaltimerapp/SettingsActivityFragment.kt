package id.example.intervaltimerapp

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.intervaltimerapp.R


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}