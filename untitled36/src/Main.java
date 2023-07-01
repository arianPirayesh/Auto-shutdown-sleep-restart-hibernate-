import javax.management.Notification;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws AWTException {
	// write your code here
        Main td = new Main();

        while(true) {
            String[] buttons = {"count down", "specified time", "help", "exit"};
            ImageIcon menuImage = new ImageIcon("menu.png");
            int choice = JOptionPane.showOptionDialog(null, null, "auto shutdown/sleep/restart/hibernate", 1, 1, menuImage, buttons, buttons[0]);

            if(choice==0 || choice==1) {


                String[] buttons2 = {"shut down", "sleep", "restart", "hibernate", "cancel"};
                int choice2 = JOptionPane.showOptionDialog(null, "choose which one to do?", null, 1, 1, null, buttons2, buttons2[0]);


                if (choice2!=4) {
                    int time = getTime(choice);

                    if (time != -1){
                        if (choice2 == 0) {
                            td.displayTray("count down for shut down started", "your pc will shut down in " + showTimeHHMMSS(time) , TrayIcon.MessageType.INFO);

                            try {
                                Thread.sleep((long) (time - (time * 0.1)));
                            } catch (InterruptedException e) {
                            }

                            td.displayTray("your pc will shut down less than " + showTimeHHMMSS((int) (time*0.1)), "", TrayIcon.MessageType.WARNING);

                            try {
                                Thread.sleep((long) (time * 0.1));
                            } catch (InterruptedException e) {
                            }

                            shutDown();
                            System.exit(0);
                        } else if (choice2 == 1) {

                            td.displayTray("count down for sleep started", "your pc will sleep in " + showTimeHHMMSS(time), TrayIcon.MessageType.INFO);

                            try {
                                Thread.sleep((long) (time - (time * 0.1)));
                            } catch (InterruptedException e) {
                            }

                            td.displayTray("your pc will sleep less than " + showTimeHHMMSS((int) (time*0.1)), "", TrayIcon.MessageType.WARNING);

                            try {
                                Thread.sleep((long) (time * 0.1));
                            } catch (InterruptedException e) {
                            }


                            sleep();
                            System.exit(0);

                        } else if (choice2 == 2) {


                            td.displayTray("count down for restart started", "your pc will restart in " +showTimeHHMMSS(time), TrayIcon.MessageType.INFO);

                            try {
                                Thread.sleep((long) (time - (time * 0.1)));
                            } catch (InterruptedException e) {
                            }

                            td.displayTray("your pc will restart less than " + showTimeHHMMSS((int) (time*0.1)), "", TrayIcon.MessageType.WARNING);

                            try {
                                Thread.sleep((long) (time * 0.1));
                            } catch (InterruptedException e) {
                            }


                            restart();
                            System.exit(0);

                        } else if (choice2 == 3) {


                            td.displayTray("count down for hibernate started", "your pc will hibernate in " + showTimeHHMMSS(time), TrayIcon.MessageType.INFO);

                            try {
                                Thread.sleep((long) (time - (time * 0.1)));
                            } catch (InterruptedException e) {
                            }

                            td.displayTray("your pc will hibernate less than " + showTimeHHMMSS((int) (time*0.1)) , "", TrayIcon.MessageType.WARNING);

                            try {
                                Thread.sleep((long) (time * 0.1));
                            } catch (InterruptedException e) {
                            }


                            hibernate();
                            System.exit(0);


                        }
                }
            }

            }else if (choice==2){
                help();
            }else if (choice==3 || choice==-1){
                break;
            }





        }



    }

    private static void help() {
        String[] firstPageButtons = {"next","back"};
        String[] midPageButtons = {"previous","next","back"};
        String[] lastPageButtons = {"previous","back"};
        ImageIcon[] images = {new ImageIcon("page1.png"), new ImageIcon("page2.png"), new ImageIcon("page3.png"), new ImageIcon("page4.png"), new ImageIcon("page5.png"), new ImageIcon("page6.png"), new ImageIcon("page7.png"), new ImageIcon("page8.png"), new ImageIcon("page9.png"), new ImageIcon("page10.png")};
        int currentPage = 1;
        int lastPage=images.length;
        while (true) {
            if (currentPage == 1) {
                int choice = JOptionPane.showOptionDialog(null, null, "page 1", 0, 0, images[currentPage-1], firstPageButtons, firstPageButtons[0]);
                if (choice==0){
                    currentPage+=1;
                }else if(choice==1){
                    break;
                }
            }else if(currentPage==lastPage){
                int choice = JOptionPane.showOptionDialog(null, null, "page "+currentPage, 0, 0, images[currentPage-1], lastPageButtons, lastPageButtons[0]);
                if (choice==0){
                    currentPage-=1;
                }else if(choice==1){
                    break;
                }
            }else{
                int choice = JOptionPane.showOptionDialog(null, null, "page "+currentPage, 0, 0, images[currentPage-1], midPageButtons, midPageButtons[1]);
                if (choice==0){
                    currentPage-=1;
                }else if (choice==1){
                    currentPage+=1;
                }else if(choice==2){
                    break;
                }
            }
        }
    }


    private static int getTime(int choice) {
        if(choice==0) {
            String time = "";
            while (true) {
                time = JOptionPane.showInputDialog(null, "enter time with this format:\n...h...m...s");
                if (time == null || time.length()==0) {
                    return -1;
                } else if (Pattern.matches("([0-9]+[h])?([0-9]*[m])?([0-9]*[s])?", time)) {
                    int hour = 0,minutes = 0,seconds = 0;
                    int lastIndexOfPreviousIdentifier=0;
                    if(Pattern.matches("([0-9]+[h])(.)*", time)) {
                         hour = Integer.parseInt(time.substring(lastIndexOfPreviousIdentifier, time.indexOf('h')));
                        lastIndexOfPreviousIdentifier=time.indexOf('h');
                    }

                    if(Pattern.matches("(.)*([0-9]+[m])(.)*", time)) {
                         minutes = Integer.parseInt(time.substring(lastIndexOfPreviousIdentifier + 1, time.indexOf('m')));
                        lastIndexOfPreviousIdentifier=time.indexOf('m');
                    }

                    if(Pattern.matches("(.)*([0-9]+[s])", time)) {
                         seconds = Integer.parseInt(time.substring(lastIndexOfPreviousIdentifier + 1, time.indexOf('s')));
                    }
                    //System.out.println(hour+" "+minutes+" "+seconds);
                    return (hour*3600+minutes*60+seconds)*1000;
                } else {
                    JOptionPane.showMessageDialog(null, "please enter a valid time", "error", 2);
                }
            }

        }else{


            String time = "";
            int  hour , minutes , seconds;
            while (true) {
                time = JOptionPane.showInputDialog(null, "enter time in 24 hours format like:\nhh:mm:ss");
                if (time == null || time.length()==0 ) {
                    return -1;
                } else if (Pattern.matches("[0-9]{2}:[0-9]{2}:[0-9]{2}", time)) {
                     hour = Integer.parseInt(time.substring(0 , 2));
                     minutes = Integer.parseInt(time.substring(3 , 5));
                     seconds = Integer.parseInt(time.substring(6 , 8));
                        //System.out.println(hour+"/"+minutes+"/"+seconds);
                    if(hour<24) {
                        if(minutes<60){
                            if(seconds<60){
                                break;
                            }else {
                                JOptionPane.showMessageDialog(null, "the seconds that you entered was more than 59", "error", 2);

                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "the minutes that you entered was more than 59", "error", 2);

                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "the hour that you entered was more than 23", "error", 2);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "please enter a valid time", "error", 2);
                }
            }

            String currentTime=currentTime();
            int currentHour = Integer.parseInt(currentTime.substring(0 , 2));
            int currentMinutes = Integer.parseInt(currentTime.substring(3 , 5));
            int currentSeconds = Integer.parseInt(currentTime.substring(6 , 8));

            int remainingHour = hour - currentHour;
            int remainingMinutes = minutes - currentMinutes;
            int remainingSeconds = seconds - currentSeconds;

            //System.out.println(remainingHour+" "+remainingMinutes+" "+remainingSeconds);

            if (remainingSeconds<0){
                remainingSeconds+=60;
                remainingMinutes-=1;
            }
            if (remainingMinutes<0){
                remainingMinutes+=60;
                remainingHour-=1;
            }
            if (remainingHour<0){
                remainingHour+=24;
            }


            return ((remainingHour*3600)+(remainingMinutes*60)+remainingSeconds)*1000;
        }
    }

    private static void shutDown() {
        try {
            Runtime.getRuntime().exec("shutdown.exe -s -t 0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        try {
            Runtime.getRuntime().exec("rundll32.exe powrprof.dll, SetSuspendState Sleep");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void restart() {
        try {
            Runtime.getRuntime().exec("shutdown.exe -r -t 0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void hibernate() {
        try {
            Runtime.getRuntime().exec("rundll32.exe powrprof.dll, SetSuspendState Hibernate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTray(String title , String text , TrayIcon.MessageType type) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(title, text, type);
    }

    private static String currentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String MyDate = formatter.format(date);
        return MyDate.substring(11);
    }

    private static String showTimeHHMMSS(int time){
        time/=1000;
        int hour = (time/3600);
        time -= hour*3600;
        int minute = time/60;
        time -= minute*60;
        int seconds = time;
        //System.out.println(hour+" "+minute+" "+seconds);
        String addAll="";

        if(hour==1){
            addAll+=(hour+" hour ");
        }else if(hour>1){
            addAll+=(hour+" hours ");
        }
        if(hour!=0 && minute!=0){
            addAll+=",";
        }
        if(minute==1){
            addAll+=(minute+" minute ");
        }else if(minute>1){
            addAll+=(minute+" minutes ");
        }
        if(hour!=0 && minute!=0 && seconds!=0){
            addAll+="and ";
        }else if(minute!=0 && seconds!=0){
            addAll+=",";
        }else if(hour!=0 && seconds!=0){
            addAll+=",";
        }
        if(seconds==1){
            addAll+=(seconds+" second ");
        }else if(seconds>1){
            addAll+=(seconds+" seconds ");
        }
        return addAll;
    }

}
