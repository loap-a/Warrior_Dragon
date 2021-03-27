package Warrior_Dragon;
import java.math.*;
import java.util.Random;
import java.util.Scanner;

public class Game1 extends Game{

    class Master extends Actor
    {
        private int blood;
        private int id;
        private int power;
        private int defenseFactor;
        private int powerShield;private int powerShield_limit;
        private int powerShield_regenate_rate;
        private boolean alive;
       public Master(int i)
       {
           super(100,0,"Master",50,20,100,10);
           alive=true;
           state="defense";
       }

        @Override
        public void attack(Actor target) {

           boolean defenses=(target.getState().equals("defense"));
           if(target.getName().equals("Warrior"))
           {
               Attack(target,defenses,true);
           }
           else Attack(target,defenses,false);
        }

    }



    /////////////////////////////////////////////////////////////
    class Warrior extends Actor
    {
        private int blood;
        private int id;
        private int power;
        private int defenseFactor;
        private int powerShield;private int powerShield_limit;
        private int powerShield_regenate_rate;
        private boolean alive;
        public Warrior(int i)
        {
            super(300,1,"Warrior",80,60,30,3);
            alive=true;
            state="defense";
        }

        @Override
        public void attack(Actor target) {
            boolean defenses=(target.getState().equals("defense"));
            if(target.getName().equals("Warrior"))
            {
                Attack(target,defenses,true);
            }
            else
                Attack(target,defenses,false);
        }

    }


////////////////////////////////////////////////////////////////
    @Override
    public Actor randomActor() {
      Random ran=new Random();
      int n=ran.nextInt();
      if(n%2==0)
      {
          return new Warrior(n);
      }
      else
      {
          return new Master(n+1);
      }


    }

    @Override
    public Actor setActor(String name) {

        switch(name)
        {
            case "Warrior":
            {
                return new Warrior(0);
            }
            case "Master":
            {
                return new Master(1);
            }
            case "Random":
            {
                return randomActor();
            }
            default:
            {
                System.out.println("Error");
                return null;
            }
        }

    }

    public Game1(String you,String enemy)
    {
        super();
        Actor your_actor;Actor enemy_actor;

        your_actor=setActor(you);
        enemy_actor=setActor(enemy);


        Scanner sc=new Scanner(System.in);
        Random rd=new Random();
        boolean you_alive=true;boolean enemy_alive=true;
        while(your_actor.getBlood()>=0&&enemy_actor.getBlood()>=0)
        {
            System.out.println("Please Choose your state (attack_defense)");
            String your_state=sc.nextLine();
            switch(your_state)
            {
                case "attack":
                {
                    int n=rd.nextInt();
                    if(n%2==0)
                    {
                        System.out.print("You Attacked "+enemy_actor.getName()+"1 ");
                        your_actor.attack(enemy_actor);
                        System.out.print("Your enemy chose to attack you ");
                        enemy_actor.attack(your_actor);

                    }
                    else {
                        System.out.print("You Attacked "+enemy_actor.getName()+"1 ");
                        your_actor.attack(enemy_actor);
                        System.out.println("Your enemy chose to defend ");
                        enemy_actor.defense();
                    }
                    break;

                }
                case "defense":
                {
                    your_actor.defense();
                    System.out.println("You choose to defend ");
                    int n=rd.nextInt();
                    if(n%2==0)
                    {
                        System.out.print("Your enemy chose to attack you ");
                        enemy_actor.attack(your_actor);

                    }
                    else {
                        System.out.println("Your enemy chose to defend ");
                        enemy_actor.defense();

                    }
                    break;
                }
                default: {

                }
            }


            if(your_actor.getBlood()<=0)you_alive=false;
            if(enemy_actor.getBlood()<=0)enemy_alive=false;

            if(!you_alive||!enemy_alive)
            {
                if(!you_alive&&!enemy_alive)
                {
                    System.out.println("You and your enemy both Died!!! it's a draw ");
                    break;
                }
                if(!you_alive)
                {
                    System.out.println("You Died and you lost ~~");
                break;
                }
                if(!enemy_alive)
                {
                    System.out.println("You Win !!!  Your enemy is DEAD !");
                    break;
                }


            }
            System.out.println("Your powershield remains "+your_actor.getPowerShield());
            System.out.println("Your blood remains "+your_actor.getBlood());
            System.out.println("Your enemy powershield remains "+enemy_actor.getPowerShield());
            System.out.println("Your enemy blood remains "+enemy_actor.getBlood());
            your_actor.powerShiled_regenerate();
            enemy_actor.powerShiled_regenerate();
        }

    }
}
