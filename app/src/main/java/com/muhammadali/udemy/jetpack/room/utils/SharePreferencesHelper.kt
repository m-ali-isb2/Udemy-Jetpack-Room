package com.muhammadali.udemy.jetpack.room.utils

import android.app.backup.SharedPreferencesBackupHelper
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.muhammadali.udemy.jetpack.room.model.DogDatabase

/**
 * Created by Muhammad Ali on 19-May-20.
 * Email muhammad.ali9385@gmail.com
 */
class SharePreferencesHelper {

    companion object {
        private const val PREFS_TIME="prefs_time"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharePreferencesHelper? = null
        private var lock = Any()


        operator fun invoke(context: Context): SharePreferencesHelper =
            instance ?: kotlin.synchronized(lock) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

        private fun buildHelper(context: Context): SharePreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharePreferencesHelper()
        }
    }

    fun updateTime(time:Long){
        prefs?.edit(commit = true) {
            putLong(PREFS_TIME,time)
        }
    }

    fun getTime() = prefs?.getLong(PREFS_TIME,0L)
}