package ui;

import javax.swing.*;

/**
 * ClassName: RegisterJframe
 * Package: ui
 * Description:
 *
 * @Author: Jenlybein
 * @Create: 21/04/2024 - 16:31
 * @Version:
 */
public class RegisterJframe extends JFrame {
    // 注册相关界面
    public RegisterJframe(){
        this.setSize(488,500);

        this.setTitle("注册");
        // this.setAlwaysOnTop(true);       //设置窗口置顶
        this.setLocationRelativeTo(null);   //设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
