package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.activity

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.R.xml.preferences)
    }
}