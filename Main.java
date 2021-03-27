package Warrior_Dragon;

import java.util.Scanner;

public class Main {

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请选择游戏模式 0(warrior_master模式)  1(hero_dragon模式)");
        int mode = sc.nextInt();

        if (mode == 0) {

            System.out.println("Please Choose Your Character");
            String you = sc.nextLine();
            System.out.println("Please Choose Your Enemy's Character (Warrior_Master_Random)");
            String enemy = sc.nextLine();
            Game1 on_going_game = new Game1(you, enemy);
        }
        else if(mode==1)
        {
            System.out.println("你选择了勇者斗恶龙模式");
            Game2 on_going_game = new Game2();
        }

    }
}
