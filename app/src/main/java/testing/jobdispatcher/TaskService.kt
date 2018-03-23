package testing.jobdispatcher

import android.util.Log
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService

/**
 * Created by jordanholland on 2018/03/23.
 */
class TaskService : JobService() {

    override fun onStartJob(job: JobParameters?): Boolean {
        Log.e(TaskService::class.java.name, "START JOB")
        return false
    }

    override fun onStopJob(job: JobParameters?): Boolean {
        // Return whether job should be retried or not
        return false
    }

}