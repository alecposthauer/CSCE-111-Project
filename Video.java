import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

//Video file 

public class Video implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        playVideo();
    }

    private void playVideo() {
        try {
            URI uri = new URI("https://www.youtube.com/watch?v=AvSnQK7geDQ");
            Desktop.getDesktop().browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}