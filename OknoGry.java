package GUI_p02;

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.time.temporal.JulianFields;
import static javax.swing.JOptionPane.showMessageDialog;
import java.lang.String;
import java.awt.List;
import java.lang.Iterable;
import java.awt.event.AdjustmentListener;

public class OknoGry {

    int level;
    int enemies_number;
    int lines;
    int speed;
    int score;
    private JFrame frame;
    private JFrame lframe;
    int weight = 600;
    int height = 600;
    JLabel nick_label;
    JTextField nick_field;
    JButton login_button;
    String nick;
    JButton level1;
    int defult_level = 2;
    int defult_lines = 3;
    int defult_speed = 5;

    public OknoGry(int level, int lines, int speed){

        this.level = level;
        this.lines = lines;
        this.speed = speed;

        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        JMenu submenu1 = new JMenu("Ilość lini wrogów");
        JMenu submenu2 = new JMenu("Prędkość opadania");
        JMenuItem i1 = new JMenuItem("Tryb odwrotny");
        JMenuItem i2 = new JMenuItem("3 (default)");
        JMenuItem i3 = new JMenuItem("5");
        JMenuItem i4 = new JMenuItem("8");
        JMenuItem i5 = new JMenuItem("Slow");
        JMenuItem i6 = new JMenuItem("Medium (default)");
        JMenuItem i7 = new JMenuItem("Fast");
        submenu1.add(i2);
        submenu1.add(i3);
        submenu1.add(i4);
        submenu2.add(i5);
        submenu2.add(i6);
        submenu2.add(i7);
        menu.add(submenu1);
        menu.add(submenu2);
        menu.add(i1);
        mb.add(menu);


        nick_label = new JLabel("Type your nickname:");
        nick_field = new JTextField();
        login_button = new JButton("LOGIN");
        lframe = new JFrame();


        login_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nick = nick_field.getText();
                lframe.dispose();
            }
        });



        lframe.setTitle("Login");
        lframe.setSize(300,300);
        lframe.setLayout(new BorderLayout());
        nick_label.setHorizontalAlignment(JLabel.CENTER);
        nick_field.setHorizontalAlignment(JTextField.CENTER);
        JPanel login_panel = new JPanel();
        login_panel.setLayout(new BorderLayout());
        login_panel.add(nick_label, BorderLayout.NORTH);
        login_panel.add(nick_field, BorderLayout.CENTER);
        login_panel.add(login_button, BorderLayout.SOUTH);
        lframe.add(login_panel, BorderLayout.SOUTH);

        JLabel level_label = new JLabel("Choose level");
        level1 = new JButton("1");
        JButton level2 = new JButton("2");
        JButton level3 = new JButton("3");
        JPanel level_panel = new JPanel();
        level_panel.setLayout(new BorderLayout());

        level_panel.add(level_label, BorderLayout.NORTH);
        level_panel.add(level1, BorderLayout.WEST);
        level_panel.add(level2, BorderLayout.CENTER);
        level_panel.add(level3, BorderLayout.EAST);
        lframe.add(level_panel, BorderLayout.CENTER);

        level1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                OknoGry oknoGry = new OknoGry(1, defult_lines, defult_speed);
                lframe.dispose();
            }
        });

        level2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                OknoGry oknoGry = new OknoGry(2, defult_lines, defult_speed);
                lframe.dispose();
            }
        });

        level3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                OknoGry oknoGry = new OknoGry(3, defult_lines, defult_speed);
                lframe.dispose();
            }
        });



        frame = new JFrame("Space Invaders");
        frame.setLayout(new BorderLayout());
        frame.setSize(weight, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new PanelGry(frame.getSize()));
        frame.pack();
        frame.setVisible(true);
        lframe.setJMenuBar(mb);
        lframe.setVisible(true);

        }


    public class PanelGry extends JPanel implements Runnable, MouseListener, KeyListener {
        private Thread animator;
        int osX = 30;
        int osY = 30;
        boolean start = false;
        Statek s;
        Wrog[][] enemy;
        Strzal sh;
        Ranking ranking = new Ranking();
        JLabel score_label;
        JButton right = new JButton(">");
        JButton left = new JButton("<");
        JButton up = new JButton("^");
        JButton down = new JButton("v");
        JButton sh_button = new JButton("X");
        JButton start_button = new JButton("START");



        public PanelGry(Dimension dimension){
            switch (level) {
                case 1:
                    enemies_number = 5;
                    break;
                case 2:
                    enemies_number = 10;
                    break;
                case 3:
                    enemies_number = 15;
                    break;
            }


            enemy = new Wrog[3][enemies_number];
            s = new Statek(270, 450, 57, 35, 5, "");
            sh = new Strzal(270, 450, 5, 20, 15, "shot.png");
            score = 0;
            score_label = new JLabel("Score: " + score);

            int x = 10;
            int y = 10;

            for(int i = 0; i < this.enemy.length; i++){
                for(int j = 0; j < this.enemy[0].length; j++){
                    this.enemy[i][j] = new Wrog(x, y, 30, 20, 5, "");
                    x += 35;
                }

                x = 10;
                y += 25;
            }

            right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(s.getX()<543){
                        s.setX(s.getX()+s.getSpeed());
                    }
                }
            });

            left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(s.getX()>0){
                        s.setX(s.getX()-s.getSpeed());
                    }
                }
            });

            up.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(s.getY()>0){
                        s.setY(s.getY()-s.getSpeed()*2);
                    }
                }
            });

            down.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(s.getY()<445){
                        s.setY(s.getY()+s.getSpeed()*2);
                    }
                }
            });

            sh_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    sh.goUp = true;
                    sh.setX(s.getX() + (s.getWidth() / 2));
                    sh.setY(s.getY());
                }
            });

            JPanel buttons = new JPanel();
            buttons.setLayout(new BorderLayout());
            buttons.add(right, BorderLayout.EAST);
            buttons.add(left, BorderLayout.WEST);
            buttons.add(up, BorderLayout.NORTH);
            buttons.add(down, BorderLayout.SOUTH);
            buttons.add(sh_button, BorderLayout.CENTER);

            setLayout(new BorderLayout());
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            addKeyListener(this);
            add(score_label, BorderLayout.NORTH);
            add(buttons, BorderLayout.SOUTH);
            setFocusable(true);
            if(this.animator == null){
                animator = new Thread(this);
                animator.start();
            }

            setDoubleBuffered(true);
        }

        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            // dodać tło
            g2.setColor(Color.white);
            g2.fillRect(0, 0, d.width, d.height);
            if(nick != null ){
                moveEnemy();
                s.move(0);
                sh.move(0);
                sh.draw(g2);
                s.draw(g2);
                hitDetect();

            }
            for(int i = 0; i < enemy.length; i++){
                for(int j = 0; j < enemy[0].length; j++){
                    if(enemy[i][j].visible){
                        enemy[i][j].draw(g2);
                    }
                }
            }

        }

        public void hitDetect(){
            for(int i = 0; i < enemy.length; i++){
                for(int j = 0; j < enemy[0].length; j++){
                    if (enemy[i][j].visible == true && sh.getX() + sh.getWidth() >= enemy[i][j].getX() &&
                    sh.getX() <= enemy[i][j].getX() + enemy[i][j].getWidth() &&
                    sh.getY() + sh.getHeight() >= (enemy[i][j].getY()) &&
                    sh.getY() <= enemy[i][j].getY() + enemy[i][j].getHeight()){
                        score ++;
                        score_label.setText("Score: " + score);
                        enemy[i][j].visible = false;
                        sh.x = -30;
                    }
                }
            }
        }

        public void moveEnemy(){
            boolean end = false;
            for(int i = 0; i < enemy.length; i++){
                for(int j = 0; j < enemy[0].length; j++){
                    if(enemy[i][j].getY() < s.getY()){
                        if(enemy[i][j].moveLeft){
                            enemy[i][j].setX(enemy[i][j].getX() - enemy[i][j].getSpeed());
                        }

                        if(enemy[i][j].moveRight){
                            enemy[i][j].setX(enemy[i][j].getX() + enemy[i][j].getSpeed());
                        }
                    }else{
                        end = true;
                    }
                }
            }

            if(end == true){

                JLabel game_over = new JLabel("Game over!");


                game_over.setHorizontalAlignment(JLabel.CENTER);
                Gracz abc = new Gracz();
                abc.setNick(nick);
                abc.setScore(score);
                ranking.checkScore(abc);

                JLabel sc = new JLabel("Your score:" + score);
                sc.setHorizontalAlignment(JLabel.CENTER);
                JButton top10 = new JButton("Show TOP 10");
                top10.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){

                        JFrame franking = new JFrame("Top 10");
                        franking.setSize(300,300);
                        franking.setLayout(new BorderLayout());
                        franking.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                        JLabel lranking = new JLabel(ranking.getTop10());


                        franking.setVisible(true);
                        lranking.setHorizontalAlignment(JLabel.CENTER);
                        franking.add(lranking, BorderLayout.CENTER);
                    }
                });
                JButton new_game = new JButton("New game");
                JFrame message = new JFrame("Message");
                new_game.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        OknoGry gra = new OknoGry(defult_level, defult_lines, defult_speed);
                        message.dispose();
                    }
                });
                message.setLayout(new GridLayout(0,1));
                message.setSize(300, 300);
                message.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                message.add(game_over);
                message.add(sc, BorderLayout.NORTH);
                message.add(top10, BorderLayout.CENTER);
                message.add(new_game, BorderLayout.SOUTH);
                message.setLocationRelativeTo(null);
                message.setVisible(true);
                frame.dispose();
            }

            for (int i = 0; i < enemy.length; i++){
                for(int j = 0; j < enemy[0].length; j++){
                    if(enemy[i][j].getX() > 600){
                        moveLeftRight(1);
                        break;
                    }

                    if(enemy[i][j].getX() < 0){
                        moveLeftRight(2);
                        break;
                    }
                }
            }
        }

        public void moveLeftRight(int d){
            for(int i = 0; i < enemy.length; i++){
                for(int j = 0; j < enemy[0].length; j++){
                    if(d == 1){
                        enemy[i][j].moveLeft = true;
                        enemy[i][j].moveRight = false;
                    } else{
                        enemy[i][j].moveLeft = false;
                        enemy[i][j].moveRight = true;
                    }

                    enemy[i][j].setY(enemy[i][j].getY() + 10);
                }
            }
        }

        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
        }

        public void mouseReleased(MouseEvent e){

        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e){
            int k = e.getKeyCode();
            s.setLeftRight(k);
            if(k == 32){
                sh.goUp = true;
                sh.setX(s.getX() + (s.getWidth() / 2));
                sh.setY(s.getY());
            }
        }

        public void keyReleased(KeyEvent e){
            int k = e.getKeyCode();
            s.stop();
        }

        public void run(){
            long beforeTime;
            beforeTime = System.currentTimeMillis();
            int animationDelay = 37;
            long time = System.currentTimeMillis();

            while(true){
                repaint();
                try{
                    time += animationDelay;
                    Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        }

        public void setEnemies(int value){
            enemies_number = value;
        }

    }

    public void setLinesNumber(int value){
        lines = value;
    }
}
