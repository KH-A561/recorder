package khilko.alexander.recorder;
//mine AIzaSyBDWldIQE0djM-MNsNCwVm9y4aboTBbQ-E
//Roma UCWYYieAoHdBBfkYVktkkzCA
//https://www.googleapis.com/youtube/v3/search?part=snippet&channelId={YOUR_CHANNEL_ID}&eventType=live&type=video&key={YOUR_API_KEY}
//UCP-jNugi2L8OfERJNy0ksfw
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;

public class Recorder extends Thread {
    private Robot bot;

    public Recorder(Robot bot) {
        this.bot = bot;
    }

    public void click(int x, int y) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void close() {
        try {
            click(510, 880);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            click(1200, 660);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
            "C:\\Users\\User\\Desktop\\obs64.lnk");
            Process p = pb.start();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                click(1200, 660);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            Thread.sleep(3000);
            try {
                click(510, 880);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
