package com.example.learnandroidxtestcases.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.util.Log
import android.widget.TextView


/**
 * Class contains static util methods for reuse
 */
object Util {
    private const val TAG = "Util"
    /**
     * Utility method to update given TextView with the current process string.
     */
    fun setCurrentRunningProcess(
        textView: TextView,
        activityContext: Context
    ) {
        val currentProcName: String
        val manager =
            activityContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == Process.myPid()) {
                currentProcName = processInfo.processName
                Log.i(TAG, currentProcName)
                textView.text = currentProcName
                break
            }
        }
    }
}