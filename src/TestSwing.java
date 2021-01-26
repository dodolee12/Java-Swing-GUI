
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestSwing {
     public static void main(String[] args) {
         JFrame frame = new JFrame();
         frame.setSize(800,600);
         
         try {
         JLabel img = new JLabel(new ImageIcon(ImageIO.read(new File("images\\img2.jfif")))); // jlabel api
         frame.add(img);

         }
         catch(IOException e) {
             System.out.println("wrong");
         
         }
         
         
         frame.setVisible(true);

         
     }
}
