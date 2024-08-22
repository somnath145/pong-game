import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ping_pong extends JFrame implements ActionListener , KeyListener{  
    Timer timer ;
    Image ball , left_bar , right_bar ;
    int x_axix = 100;
    int y_axix = 100;
    int left_bar_y = 100;
    int right_bar_y = 100;

    boolean x_move = true;
    boolean y_move = true;
    boolean x_bar_move = false;
    boolean game_over = false;
    
    int number = (int)(Math.random()*10)+1;

    int lower_bound_r =100;
    int uper_bound_r =100+128;

    int lower_bound_l =100;
    int uper_bound_l =100+128;

    int score_l=0;
    int score_r=0;

    ping_pong(){
        setBounds(500, 200, 500, 500);
        getContentPane().setBackground(Color.black);
        setLayout(null);

        timer = new Timer(80, this);
        timer.start();

        ball = Toolkit.getDefaultToolkit().getImage("D://Java//SOMNATH//icons//head.png");
        left_bar = Toolkit.getDefaultToolkit().getImage("D:\\Java\\SOMNATH\\icons\\Screenshot 2024-07-30 193108.png");
        right_bar = Toolkit.getDefaultToolkit().getImage("D:\\Java\\SOMNATH\\icons\\Screenshot 2024-07-30 193053.png");

        addKeyListener(this);
        setVisible(true);

    }

    
    public void move(){

        if(x_move==true && y_move==true){
            x_axix += 10;
            y_axix += number;
        }
        if(x_move == false && y_move == true){
            x_axix -= 10;
            y_axix += number;
        }
        if(x_move == true && y_move == false){
            x_axix += 10;
            y_axix -= number;
        }
        if(x_move == false && y_move == false){
            x_axix -= 10;
            y_axix -= number;
        }
        
        if(x_axix == 460 && (y_axix >=lower_bound_r && y_axix <= uper_bound_r)){
            x_move=false;
            score_r += 1;
        }
        if(x_axix <=23 && (y_axix >=lower_bound_l && y_axix <= uper_bound_l)){
            x_move=true;
            score_l += 1;
        }
        if(y_axix >= 480){
            y_move=false;
        }
        if(y_axix <= 30){
            y_move=true;
        }

        if(x_axix >= 500 || x_axix <=0){
            game_over=true;
        }
        
    }

    public void paint(Graphics g){
        super.paint(g);

        g.drawImage(ball, x_axix, y_axix, this);
        g.drawImage(left_bar, 5, left_bar_y, this);
        g.drawImage(right_bar, 470, right_bar_y, this);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Console", Font.PLAIN, 15));
        g.drawString("player 1 : "+score_l, 70, 60);
        g.drawString("player 2 : "+score_r, 300, 60);
       

        if(game_over==true){
            g.setColor(Color.RED);
            g.setFont(new Font("Goudy Stout", Font.PLAIN, 30));
            g.drawString("Game Over", 100, 300);

            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            if(score_l < score_r){
                g.drawString("Player 2 win", 150, 350);
            }
            else if(score_l > score_r){
                g.drawString("Player 1 win", 150, 350);
            }
            else{
                g.drawString("Draw", 150, 350);
            }
            timer.stop();
        }
        
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            left_bar_y -= 50;

            lower_bound_l = left_bar_y;
            uper_bound_l = left_bar_y+128;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            left_bar_y += 50;

            lower_bound_l = left_bar_y;
            uper_bound_l = left_bar_y+128;
        }
        if(e.getKeyCode() == KeyEvent.VK_U){
            right_bar_y -= 50;

            lower_bound_r = right_bar_y;
            uper_bound_r = right_bar_y+128;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right_bar_y += 50;

            lower_bound_r = right_bar_y;
            uper_bound_r = right_bar_y+128;
        }

    }

    public void actionPerformed(ActionEvent e){
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
       new ping_pong();
    }

    
}
