package ui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class GameJframe extends JFrame implements KeyListener, ActionListener {
    // 游戏主界面
    String image_path = "images\\images\\pic.jpg"; // 存储图片路径
    ImageIcon image; // 存储处理后拼图完整图片
    ImageIcon[] images = new ImageIcon[16]; // 存储处理后拼图分割图片
    int[][] mess = new int[4][4]; // 存储乱序矩阵
    int _x,_y;  // 存储空白格的位置
    int step = 0; // 存储移动步数

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem IntroductionItem = new JMenuItem("游戏介绍");

    public GameJframe(){
        // 初始化窗口
        initJFrame(603,680,"拼图 v1.0");

        // 初始化菜单
        initJMenu();

        // 处理拼图图片
        initImage(420, 420);

        // 初始化乱序数组
        InitMess();

        // 加载图片
        loadImage();

        this.setVisible(true);
    }

    private void InitMess(){
        for (int k = 0, i = 0, j = 0; k < 16; k++, i = k / 4, j = k % 4)
            mess[i][j] = k;

        Random r = new Random();

        for (int k = 0, i = 0, j = 0; k < 16; k++, i = k / 4, j = k % 4) {
            int r1 = r.nextInt(mess.length);
            int r2 = r.nextInt(mess.length);
            int temp = mess[i][j];
            mess[i][j] = mess[r1][r2];
            mess[r1][r2] = temp;

            if(mess[i][j]==15){
                _x = i;
                _y = j;
            }
            else if(mess[r1][r2]==15){
                _x = r1;
                _y = r2;
            }
        }
    }

    private void loadImage() {
        // 清除已有图片
        this.getContentPane().removeAll();

        // 判断胜利
        if(victory()){
            JLabel winjl = new JLabel(new ImageIcon("images\\win.png"));
            winjl.setBounds(203, 283, 195, 70);
            this.getContentPane().add(winjl);
        }

        // 步数显示
        JLabel stepCount = new JLabel("步数："+ step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        // 加载图片
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                JLabel jl = new JLabel(images[mess[i][j]]);
                jl.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                if(i!=_x||j!=_y)
                    jl.setBorder(new BevelBorder(BevelBorder.RAISED));
                this.getContentPane().add(jl);
            }

        JLabel background = new JLabel(new ImageIcon("images\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initImage(int width, int height) {
        try {
            // 读取原始图像
            BufferedImage originalImage = ImageIO.read(new File(image_path));

            // 调整图像尺寸，缩放宽/高
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newWidth, newHeight;

            if (originalWidth >= originalHeight) {
                newHeight = height;
                newWidth = originalWidth * height/originalHeight;
            } else {
                newWidth = width;
                newHeight = originalHeight * width/originalWidth;
            }

            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            resizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

            // 裁剪居中的多余区域
            int x = (resizedImage.getWidth() - width) / 2; // 裁剪区域左上角 x 坐标
            int y = (resizedImage.getHeight() - height) / 2; // 裁剪区域左上角 y 坐标

            BufferedImage croppedImage = resizedImage.getSubimage(x, y, width, height);
            image = new ImageIcon(croppedImage);
            // 将裁剪后的图像分割成16份
            int subWidth = width / 4;
            int subHeight = height / 4;
            for (int i = 0, k = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++,k++) {
                    int startX = j * subWidth;
                    int startY = i * subHeight;

                    BufferedImage subImage = croppedImage.getSubimage(startX, startY, subWidth, subHeight);

                    images[k] = ((i==3&&j==3) ? new ImageIcon() : new ImageIcon(subImage));
                }
            }
        }
        catch (Exception e) {
            System.out.println("图像加载失败：" + e.getMessage());
        }
    }

    private void initJMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(IntroductionItem);

        // 菜单两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        IntroductionItem.addActionListener(this);

        // 给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame(int width, int height, String title) {
        this.setSize(width,height);
        this.setTitle(title);
        // this.setAlwaysOnTop(true);       //设置窗口置顶
        this.setLocationRelativeTo(null);   //设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        //关闭模式:0什么都不做，1默认关闭，2关闭最后界面则停止虚拟机，3关闭一个窗口则关虚拟机
        this.setLayout(null); //取消默认的居中放置，只有取消了才会按照xy轴的形式添加组件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();

            JLabel all = new JLabel(image);
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("C:\\Users\\86503\\Desktop\\background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory())
            return;

        // 上下左右判断
        int code = e.getKeyCode();
        // System.out.println(code); //获取按键事件id
        if(code==37){
            // 左
            if(_y<3) {
                mess[_x][_y] = mess[_x][_y+1];
                mess[_x][++_y] = 15;
                loadImage();
                step++;
            }
        }
        else if(code==38){
            // 上
            if(_x<3) {
                mess[_x][_y] = mess[_x + 1][_y];
                mess[++_x][_y] = 15;
                loadImage();
                step++;
            }
        }
        else if(code==39){
            // 右
            if(_y>0) {
                mess[_x][_y] = mess[_x][_y-1];
                mess[_x][--_y] = 15;
                loadImage();
                step++;
            }
        }
        else if(code==40){
            // 下
            if(_x>0) {
                mess[_x][_y] = mess[_x - 1][_y];
                mess[--_x][_y] = 15;
                loadImage();
                step++;
            }
        }
        else if(code==87){
            mess = new int[][]{
                    {0,1,2,3},
                    {4,5,6,7},
                    {8,9,10,11},
                    {12,13,14,15}
            };
            _x=3;
            _y=3;
            loadImage();
        }
        else if(code==65){
            loadImage();
        }
    }

    public boolean victory(){
        int[][] win = new int[][]{
                {0,1,2,3},
                {4,5,6,7},
                {8,9,10,11},
                {12,13,14,15}
        };
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(mess[i][j]!=win[i][j])
                    return false;
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){  // 重新游玩
            step = 0;
            InitMess();
            loadImage();
        }
        else if(obj == reLoginItem){ // 重新登录
            this.setVisible(false); // 关闭当前页面
            new LoginJframe(); //进入登录界面
        }
        else if(obj == closeItem){ // 关闭游戏
            System.exit(0);
        }
        else if(obj == IntroductionItem){ // 游戏介绍
            JDialog jd = new JDialog();

            JLabel jl1 = new JLabel("快捷键：  [ W ] 一键通关  [ A ] 查看原图");
            jl1.setBounds(45,45,100,20);
            jd.getContentPane().add(jl1);

            jd.setSize(344,344);
            jd.setLocationRelativeTo(this);
            jd.setModal(true); // 不关闭则无法操作下面的界面
            jd.setVisible(true);
        }
    }
}