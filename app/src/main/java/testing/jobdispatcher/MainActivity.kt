package testing.jobdispatcher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.firebase.jobdispatcher.GooglePlayDriver
import com.firebase.jobdispatcher.Lifetime
import com.firebase.jobdispatcher.Trigger

class MainActivity : AppCompatActivity() {

    val TASK_TAG = "TaskService"

    var dispatcher: FirebaseJobDispatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        scheduleTask()
    }

    private fun scheduleTask() {
        val job = dispatcher!!.newJobBuilder()
                .setService(TaskService::class.java)
                // A unique tag for the task, so it can be cancelled later
                .setTag(TASK_TAG)
                // Sets a job to recurring
                .setRecurring(true)
                // Replaces any current tasks with a new one if this method is called again
                .setReplaceCurrent(true)
                // Should make the task survive a reboot
                .setLifetime(Lifetime.FOREVER)
                // Sets the execution window(ex. start between 0 and 60 seconds from now)
                .setTrigger(Trigger.executionWindow(0, 60))
                .build()
        dispatcher?.mustSchedule(job)
    }

    private fun cancelTask() {
        dispatcher?.cancel(TASK_TAG)
    }

}
