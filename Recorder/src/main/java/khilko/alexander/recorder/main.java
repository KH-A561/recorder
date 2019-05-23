package khilko.alexander.recorder;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class main {
    public static void main(String[] args) throws AWTException {
        String url = null;
        while (url == null) {
            url = Search.check();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=" + url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        Recorder recorder = new Recorder(new Robot());
        recorder.start();
        while (url != null) {
            url = Search.check();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        recorder.close();
    }
}
