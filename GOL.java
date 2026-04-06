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
        int iters=10;
        while(iters>0){
            //System.out.println("ticking...");
            this.updateState();
            frame.repaint();
            iters--;
        }

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
        System.out.println("Updating...");
        int[][] newState=new int[GAMEW][GAMEH];
        for(int x=1;x<GAMEW-1;x++){
            for(int y=1;y<GAMEH-1;y++){
                if(this.game[x][y]==1){
                    if(checkDeath(x,y)){
                        //System.out.println("killing cell...");
                        newState[x][y]=0;
                    }else{
                        newState[x][y]=1;
                    }
                }else{
                   if(checkLife(x,y)){
                    //System.out.println("reviving cell...");
                        newState[x][y]=1;
                    }else{
                        newState[x][y]=0;
                    } 
                }
            }
        }
        Boolean same = true;
        for(int x=0;x<GAMEW;x++){
            for(int y=0;y<GAMEH;y++){
                if(this.game[x][y]!=newState[x][y]){
                    same=false;
                }
            }
        }
        System.out.println(same);
        this.game=newState;
    }

    public Boolean checkDeath(int x, int y){
        int counter=0;
        if(x!=0 && x!=GAMEW && y!=0 && y!=GAMEH){
            for(int i=x-1;i<x+1;i++){
                for(int j=y-1;j<y+1;j++){
                    if(this.game[i][j]==1){
                        counter++;
                    }
                }
            }
        }
        if(counter<2||counter>3){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkLife(int x, int y){
        int counter=0;
        if(x!=0 && x!=GAMEW && y!=0 && y!=GAMEH){
            for(int i=x-1;i<x+1;i++){
                for(int j=y-1;j<y+1;j++){
                    if(this.game[i][j]==1){
                        counter++;
                    }
                }
            }
        }
        if(counter==3){
            return true;
        }else{
            return false;
        }
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
    public static void main(String[] args) throws InterruptedException{
        Thread t=new Thread();
        t.start();
        GOL gol=new GOL();
        gol.Main();
        
        
    }
}
