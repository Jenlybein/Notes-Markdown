package ui;

import javax.swing.*;

/**
 * ClassName: LoginJframe
 * Package: ui
 * Description:
 *
 * @Author: Jenlybein
 * @Create: 21/04/2024 - 16:27
 * @Version:
 */
public class LoginJframe extends JFrame {
    // 登录相关界面
    public LoginJframe(){
        this.setSize(488,430);

        this.setTitle("注册");
        // this.setAlwaysOnTop(true);       //设置窗口置顶
        this.setLocationRelativeTo(null);   //设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
