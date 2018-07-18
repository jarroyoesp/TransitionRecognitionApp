package es.jarroyo.transitionrecognitionapp

import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionEvent
import com.google.android.gms.location.DetectedActivity
import java.text.SimpleDateFormat
import java.util.*

object TransitionRecognitionUtils {

    val SHARED_PREFERENCES_FILE_KEY_TRANSITIONS = "SHARED_PREFERENCES_FILE_KEY_TRANSITIONS"
    val SHARED_PREFERENCES_KEY_TRANSITIONS = "SHARED_PREFERENCES_KEY_TRANSITIONS"

    fun createTranstionString(activity: ActivityTransitionEvent): String {
        val theActivity = toActivityString(activity.getActivityType())
        val transType = toTransitionType(activity.getTransitionType())

        return ("Transition: "
                + theActivity + " (" + transType + ")" + "   "
                + SimpleDateFormat("HH:mm:ss", Locale.UK)
                .format(Date()) +"\n\n")
    }


    fun toActivityString(activity: Int): String {
        when (activity) {
            DetectedActivity.STILL -> return "STILL"
            DetectedActivity.WALKING -> return "WALKING"
            DetectedActivity.ON_BICYCLE -> return "ON_BICYCLE"
            DetectedActivity.ON_FOOT -> return "ON_FOOT"
            DetectedActivity.IN_VEHICLE -> return "IN_VEHICLE"
            DetectedActivity.RUNNING -> return "RUNNING"
            else -> return "UNKNOWN"
        }
    }

    fun toTransitionType(transitionType: Int): String {
        when (transitionType) {
            ActivityTransition.ACTIVITY_TRANSITION_ENTER -> return "ENTER"
            ActivityTransition.ACTIVITY_TRANSITION_EXIT -> return "EXIT"
            else -> return "UNKNOWN"
        }
    }

}