package reminder;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Alen Joseph 14/10/2018
 */
public class DbScheduler implements Runnable {

    /*
     * This thread takes care of scheduler which run the job every minute checking the Database for reminder and start when window is activated
     */
    public void run() {

        try {
            // define the job and tie it to our Job class
            JobDetail j = JobBuilder.newJob(TimerJob.class).build();
            // We are usinig default build in quartz trigger set to run every minute
            Trigger t = TriggerBuilder.newTrigger().withIdentity("CroneTrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever()).build();

            // Grab the Scheduler instance from the Factory
            Scheduler s = StdSchedulerFactory.getDefaultScheduler();

            s.start();
             // Tell quartz to schedule the job using our trigger
            s.scheduleJob(j, t);
        } catch (SchedulerException se) {
            System.err.println(se);
        }
    }

}
