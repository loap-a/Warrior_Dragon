package Warrior_Dragon;

public class Actor implements Canplay {
   private int blood;
   private int id;
   private int power;
   private int defenseFactor;
   private int powerShield;private int powerShield_limit;
   private int powerShield_regenate_rate;
   private boolean alive;
    public String actor_name;
    public String state="defense";

    public Actor(int b,int i,String n,int p, int d, int po, int shield)
    {
        blood=b;
        id=i;
        actor_name=n;
        power=p;
        defenseFactor=d;
        powerShield=po;powerShield_limit=po;
        powerShield_regenate_rate=shield;
        alive=true;
    }
    public Actor(){}

    public void attack(Actor target)
    {
        String enemy_state=target.getState();
        switch(enemy_state)
        {
            case "attack":
            {
                Attack(target,false,false);

            }
            case "defense":
            {
                Attack(target,true,false);
            }


        }
    }

    public boolean defense()
    {
        state=new String("defense");
        return true;
    }

    public String getState()
    {

        return state;
    }

    public void Attack(Actor target,boolean defend,boolean weak)
    {
        double damage=0;

        if(defend)
        {
            if(weak)
            damage=this.getPower()*((double)target.getDefenseFactor()/100)*2;
            else damage=this.getPower()*((double)target.getDefenseFactor()/100);
            if(target.getPowerShield()>0)
            {
               int temp= target.setPowerShield((int)damage);
               if(weak)
                System.out.print("and caused a double damage on the shield by "+damage+" ");
               else
                   System.out.print("and caused a general damage on the shield by "+damage+" ");

               if(temp>0)
               {
                   System.out.println("and a penetrated damage on the blood by "+temp);
               }
               else
                   System.out.println("");
            }
            else {
                target.setBlood((int)damage);
                if(weak)
                System.out.println("and caused a double damage on the blood by "+damage);
                else
                    System.out.println("and caused a general damage on the blood by "+damage);
            }

        }
        else
        {
            if(weak)damage=this.getPower()*2;
            else damage=this.getPower();
            if(target.getPowerShield()>0)
            {
               int temp = target.setPowerShield((int)damage);
               if(weak)
                System.out.print("and caused a double damage on the shield by "+damage+" ");
               else
                   System.out.print("and caused a general damage on the shield by "+damage+" ");

                if(temp>0)
                {
                    System.out.println("and a penetrated damage on the blood by "+temp);
                }
                else
                    System.out.println("");
            }
            else
            {
                target.setBlood((int)damage);
                if(weak)
                System.out.println("and caused a double damage on the blood by "+damage);
                else
                    System.out.println("and caused a general damage on the blood by "+damage);

            }
        }
    }


    public int getBlood()
    {
        return blood;
    }
    public void setBlood(int b)
    {
        if(b<0){blood=-b;return;}
        blood=blood-b;
        if(blood<0)blood=0;
    }
    public String getName()
    {
        return actor_name;
    }

    public int getPower()
    {
        return this.power;
    }
    public int getDefenseFactor()
    {
        return defenseFactor;
    }
    public int getPowerShield()
    {
        return powerShield;
    }
    public int setPowerShield(int n) {

        if(n<0){
            powerShield=-n;
            return 0;
        }

        int temp = n - powerShield;
        powerShield = powerShield - n;
        if (powerShield <= 0)
        {
            powerShield = 0;
            setBlood(temp);
            return temp;
        }
        return 0;

    }
    public void powerShiled_regenerate()
    {
        if(powerShield<=powerShield_limit-10)
        powerShield=powerShield+10;
        else
            powerShield=powerShield_limit;
    }
    public int getId()
    {
        return id;
    }

}
