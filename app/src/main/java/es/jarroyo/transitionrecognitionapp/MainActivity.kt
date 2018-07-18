package es.jarroyo.transitionrecognitionapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTransitionRecognition: TransitionRecognition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTransitionRecognition()
    }

    override fun onResume() {
        super.onResume()
        showPreviousTransitions()
    }

    override fun onPause() {
        mTransitionRecognition.stopTracking()
        super.onPause()
    }


    /**
     * INIT TRANSITION RECOGNITION
     */
    fun initTransitionRecognition(){
        mTransitionRecognition = TransitionRecognition()
        mTransitionRecognition.startTracking(this)
    }

    /**
     * Show previous transitions. This is an example to explain how to detect user's activity. To
     * see this activity we have to relaunch the app.
     */
    fun showPreviousTransitions() {
        val sharedPref = getSharedPreferences(
                TransitionRecognitionUtils.SHARED_PREFERENCES_FILE_KEY_TRANSITIONS, Context.MODE_PRIVATE)

        var previousTransitions = sharedPref.getString(TransitionRecognitionUtils.SHARED_PREFERENCES_KEY_TRANSITIONS, "")

        main_activity_tv.text = "Your activity:\n" + previousTransitions
    }
}
