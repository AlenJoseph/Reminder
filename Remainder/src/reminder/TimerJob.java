package reminder;

import ds.desktop.notify.DesktopNotify;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Alen Joseph 14/10/2018
 */
public class TimerJob implements Job {

    /**
     * TimerJOb is a class which implement Job class of Quartz scheduler We are
     * creating a job which check the database for reminder , if reminder is
     * found it display it in a dialog box and shows desktop notification
     */
    String r;
    int i;
    static AudioStream audios;

    @Override

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Connection con = dbcon.dbConnector();
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);// Gets the local time and format it in yyyy-MM-dd HH:mm format
        String date = simpleDateFormat.format(new Date());
        String d = date.substring(0, 10);//divide the date and time into 2 parts
        String t = date.substring(11, 16);
        try {
            //Cheks for remainder every minute in the database
            String query = "SELECT * FROM `rem` WHERE `Reminder_date`=? AND `Reminder_time`=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, d);
            pst.setString(2, t);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {

                count++;
                r = rs.getString("Reminder");
                i = rs.getInt("id");
            }
            if (count == 1) {
                music();
                int dialogButton = JOptionPane.OK_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, r, "You Have a Reminder", dialogButton);
                DesktopNotify.showDesktopMessage("You Have a Reminder", r);// Displays desktop notification
                if (dialogResult == JOptionPane.OK_OPTION) {
                    AudioPlayer.player.stop(audios);
                    String sql = "DELETE FROM `rem`  WHERE `id`=?";
                    PreparedStatement pt = con.prepareStatement(sql);
                    pt.setInt(1, i);
                    pt.execute();

                }

            }
        } catch (HeadlessException | SQLException e) {
            System.err.println(e);
        }

    }

    public static void music() {
        // Plays a music file when remainder found
        String fpath;
        fpath = "src\\Audio\\alarm.wav";
        FileInputStream m;
        try {
            m = new FileInputStream(new File(fpath));
            audios = new AudioStream(m);
            AudioPlayer.player.start(audios);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
