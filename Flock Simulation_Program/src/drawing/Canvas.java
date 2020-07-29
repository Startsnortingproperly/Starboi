package drawing;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import turtle.*;


public class Canvas extends JPanel implements MouseListener {    
    Turtles turtles; //Object of turtles representing the group of turtles
    final int w, h; // height and width of the window
    int n; //number of turtles in the group of turtles initially
    
    public Canvas() {
        w = 800;
        h = 600;
        n = 50;
 
        setPreferredSize(new Dimension(w, h));  // set the dimensions of the window
        setBackground(Color.white);
        addMouseListener(this); // Mouse listener event to increase the number of turtles onClick
 
        this.turtles = Turtles.spawn(-300, h * 0.5, n); 
 
        new Timer(10, (ActionEvent e) -> {
            if (turtles.hasLeftTheBuilding(w))
                this.turtles = Turtles.spawn(-300, h * 0.5, n);
            repaint();
        }).start();
    }
 
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
 
        turtles.run(g, w, h);
    }
    
    
    public void mousePressed(MouseEvent me) {
      n+=30;
      repaint(); // call repaint() method
    }
    
    // all abstract method implemented
    public void mouseClicked(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
   
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Canvas");
            f.setResizable(false);
            f.add(new Canvas(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    
}
