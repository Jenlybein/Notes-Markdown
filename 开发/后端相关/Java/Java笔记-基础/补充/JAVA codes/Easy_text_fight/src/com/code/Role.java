package com.code;

import java.util.Random;

public class Role {
    private String name;
    private int blood;

    // 构造
    public Role() {
    }

    public Role(String name, int blood) {
        this.name = name;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    //攻击
    public void  attack(Role role){
        //随机伤害
        Random r = new Random();
        int hurt = r.nextInt(20)+1;

        //被打人的剩余血量
        role.setBlood(role.getBlood() - hurt);

        System.out.println(this.getName()+"举起拳头，打了"+ role.getName() + "一下，造成了" + hurt +
                "伤害，" + role.getName() + "还剩下" + role.getBlood() + "点血。");
    }

}
