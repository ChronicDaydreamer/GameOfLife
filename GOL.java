import java.awt.*;
import javax.swing.*;
import java.util.*;
public class GOL extends JPanel implements Runnable{
    public static final int WIDTH=800, HEIGHT=WIDTH/12 *9;
    public static final int GAMEW=WIDTH/10,GAMEH=HEIGHT/10;
    public int[][] game;

    public GOL(){
        this.game=new int[GAMEW][GAMEH];
        this.initialState();
    }
    
    public void Main() {
        // Create the frame
        JFrame frame = new JFrame("Painting Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        // Add our custom panel to the frame
        frame.add(new GOL());

        // Center and show
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void initialState(){
        System.out.print("Running initialState()");
        Random random=new Random();
        for(int x=0;x<GAMEW;x++){
            for(int y=0;y<GAMEH;y++){
                this.game[x][y]=random.nextInt(2);
            }
        }
    }
    public void updateState(){
        int[][] newState=new int[GAMEW][GAMEH];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the background
        System.out.println("Running paintComponent()");
        for(int x=0;x<GAMEW;x++){
            for(int y=0;y<GAMEH;y++){
                if(this.game[x][y]==1){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x*10, y*10,(x+1)*10,(y+1)*10);
            }
        }
    }

    @Override
    public void run() {
    }
    public static void main(String[] args){
        GOL gol=new GOL();
        gol.Main();
    }
}
