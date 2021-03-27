package Warrior_Dragon;
//Hero vs Dragons
import java.util.Scanner;
import java.util.Random;
public class Game2 extends Game{
    public Game2()
    {
        super();

        System.out.println("Here is some information about this");
        System.out.println("Your actor is identified as 'hero' ");
        System.out.println("Your enemy is a bad dragon ");
        System.out.println("dragons has no powershield ");
        System.out.println("and your actor 'hero' is quite powerful ");
        System.out.println("hero具有高攻击力高防御力但是血量一般, 在进攻的过程中有一定几率打出会心一击(伤害*2)");
        System.out.println("且每一回合都有几率触发伙伴的帮助(恢复全部血量和护盾)");
        System.out.println("dragon属于boss, 具有超高血量和高攻击力, 但性格暴躁不会防守, 在血量低于一定程度时会暴怒(回复血量并提升攻击力)");

        hero my_hero=new hero();dragon my_dragon=new dragon();
        Scanner sc=new Scanner(System.in);
        Random rd=new Random();
        boolean raged=false;
        while(my_hero.getBlood()>=0&&my_dragon.getBlood()>=0)
        {
            System.out.println("Please choose your move (attack or defend)");
            String your_choice=sc.nextLine();
            int temp=rd.nextInt();if(temp%2==0){my_hero.regenerate();}

            switch(your_choice)
            {
                case "attack":
                {
                    System.out.print("you choose to attack the dragon ");
                    my_hero.attack(my_dragon);
                    System.out.print("the dragon attacked back ");
                    my_dragon.attack(my_hero);
                    break;
                }
                case "defend":
                {
                    System.out.println("The hero chose to defend ");
                    System.out.println("The dargon attacked you ");
                    my_dragon.attack(my_hero);
                    break;
                }
                default:{}

            }
            if(my_dragon.getBlood()<=1000&&!raged)
            {
                System.out.println("暴龙被彻底激怒了, 它的血量完全恢复并且攻击力提升一倍");
                my_dragon.raged();
                raged=true;
            }
            boolean hero_alive=(my_hero.getBlood()>0);
            boolean dragon_alive=(my_dragon.getBlood()>0);

            if(!hero_alive||!dragon_alive)
            {
                if(!hero_alive&&!dragon_alive)
                {
                    System.out.println("英雄和恶龙同归于尽了");
                    break;
                }
                if(!hero_alive)
                {
                    System.out.println("英雄倒了");
                    break;
                }
                System.out.println("英雄击败了恶龙");

                break;
            }
            my_hero.powerShiled_regenerate();
        }

    }


    class hero extends Actor{
        private int blood;
        private int id;
        private int power;
        private int defenseFactor;
        private int powerShield;private int powerShield_limit;
        private int powerShield_regenate_rate;
        private boolean alive;
        public hero()
        {

            super(200,666,"Big_Hero",200,60,400,40);
            alive=true;
            state="defense";
        }

        @Override
        public void attack(Actor target) {
            Random rd=new Random();
            int temp=rd.nextInt();
            if(temp%2==0)
            Attack(target,false,false);
            else
                Attack(target,false,true);
        }

        public void regenerate() {
            System.out.println("英雄获得了远方伙伴的加持, 血量和护盾完全恢复");
            this.setBlood(-200);
            this.setPowerShield(-400);
        }
    }
    ///////////////////////////////////////////////////////////////
    class dragon extends Actor{

        private int blood;
        private int id;
        private int power;
        private int defenseFactor;
        private int powerShield;private int powerShield_limit;
        private int powerShield_regenate_rate;
        private boolean alive;
        public dragon()
        {
         super(2000,999,"Bad_Dragon",100,100,0,0);
         alive=true;
         state="attack";
        }

        @Override
        public void attack(Actor target) {
            Attack(target,false,false);
        }
        public void raged()
        {
            this.setBlood(-2000);
            power=200;
        }
    }
//////////////////////////////////////////////////////////////////




}
