package com.example.intervaltimerapp

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}