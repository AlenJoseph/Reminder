package reminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Alen Joseph 14/10/2018
 */
public class DateTimeValidator {

    static Date RDate, PDate;

    public static Boolean validator(String dt, String tm, String r) {
        
    try{
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        String d = dt + " " + tm;
        /* Check if date is 'null' */
        if (dt.trim().equals("")) {
            System.out.println(dt + " is Invalid Date format");
            return false;
        } /* Date is not 'null' */ else {
            /*
	     * Set preferred date format,
	     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sdfrmt.setLenient(false);
            /* Create Date object
	     * parse the string into date 
             */
            boolean flag = true;
            try {
                RDate = sdfrmt.parse(d);
                PDate = sdfrmt.parse(date);

            } /* Date format is invalid */ catch (ParseException e) {
                flag = false;
                System.out.println(dt + " is Invalid Date format");

            }
            if (RDate.after(PDate)) {
                System.out.println("valid Reminder");
                return true;
                /* Return true if date format is valid */

            } else if (flag == false) {

                return false;
            }else if (r==null){
                JOptionPane.showMessageDialog(null, "Enter a reminder");
                
            }
            
            else {
                return false;
            }
        }

    }catch(Exception e){
        
        return false;
    }
        return null;
       
    }
}
