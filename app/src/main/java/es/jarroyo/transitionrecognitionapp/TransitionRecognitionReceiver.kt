package es.jarroyo.transitionrecognitionapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.ActivityTransitionEvent
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity

class TransitionRecognitionReceiver : BroadcastReceiver() {

    lateinit var mContext: Context

    override fun onReceive(context: Context?, intent: Intent?) {
        mContext = context!!

        Log.d("TransitionReceiver", "onReceive")

        if (ActivityTransitionResult.hasResult(intent)) {
            var result = ActivityTransitionResult.extractResult(intent)

            if (result != null) {
                processTransitionResult(result)
            }
        }
    }


    fun processTransitionResult(result: ActivityTransitionResult) {
        for (event in result.transitionEvents) {
            onDetectedTransitionEvent(event)
        }
    }

    private fun onDetectedTransitionEvent(activity: ActivityTransitionEvent) {
        when (activity.activityType) {
            DetectedActivity.ON_BICYCLE,
            DetectedActivity.RUNNING,
            DetectedActivity.WALKING ->
                /*case DetectedActivity.TILTING:
                case DetectedActivity.STILL:*/ {

                saveTransition(activity)
            }
            else -> {

            }
        }
    }

    /**
     * In this example we save in preferences, but is a bad way to do that.
     * Is an Example, in a real app we have to save in database.
     */
    private fun saveTransition(activity: ActivityTransitionEvent){
        // Save in Preferences
        val sharedPref = mContext?.getSharedPreferences(
                TransitionRecognitionUtils.SHARED_PREFERENCES_FILE_KEY_TRANSITIONS, Context.MODE_PRIVATE)

        var previousTransitions = sharedPref.getString(TransitionRecognitionUtils.SHARED_PREFERENCES_KEY_TRANSITIONS, "")
        with (sharedPref.edit()) {
            val transitions =  previousTransitions + TransitionRecognitionUtils.createTranstionString(activity)
            putString(TransitionRecognitionUtils.SHARED_PREFERENCES_KEY_TRANSITIONS, transitions)
            commit()
        }
    }
}