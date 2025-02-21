import java.util.concurrent.TimeUnit;
public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String name){
        this.name=name;
        hitPoints=100;
    }

    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public String toString(){
        return "Hero{name='"+name+"', hitPoints="+hitPoints+"}";
    }
    public void attack(Hero opponent){
        double r=Math.random();
        if(r<0.5){
            opponent.hitPoints-=10;
        }
        else{
            this.hitPoints-=10;
        }
    }
    public void senzuBean(){
        hitPoints=100;
    }
    public void fightUntilTheDeathHelper(Hero opponent){
        while(opponent.getHitPoints()>0 && this.hitPoints>0){
            attack(opponent);
        }
    }
    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        this.fightUntilTheDeathHelper(opponent);
        return this.name + ": " + this.hitPoints + " " + opponent.name + ": " + opponent.hitPoints;
    }
    private int[] nFightsToTheDeathHelper(Hero opponent,int n){
        int[] wins=new int[2];
        for(int i=0;i<n;i++){
            this.senzuBean();
            opponent.senzuBean();
            this.fightUntilTheDeathHelper(opponent);
            if (this.getHitPoints()>0) {
                wins[0]++;
            } else {
                wins[1]++;
            }
        }
        return wins;
    }
    public String nFightsToTheDeath(Hero opponent,int n){
        int[] wins=nFightsToTheDeathHelper(opponent,n);
        String result =this.name+": "+wins[0]+" wins\n"+opponent.name+": "+wins[1]+" wins\n";
        if (wins[0]>wins[1]) {
            result +=this.name+" wins!";
        }
        else if (wins[0]<wins[1]) {
            result +=opponent.name + " wins!";
        }
        else {
            result +="draw";
        }
        return result;
    }
    public void dramaticFightToTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        while (opponent.getHitPoints()>0 && this.getHitPoints()>0) {
            this.attack(opponent);
            System.out.println(this.getName()+": "+this.getHitPoints()+"  "+opponent.getName()+": " +opponent.getHitPoints());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (opponent.getHitPoints()>0) {
                opponent.attack(this);
                System.out.println(this.getName()+": "+this.getHitPoints()+"  "+opponent.getName()+": "+opponent.getHitPoints());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (this.getHitPoints()>0) {
            System.out.println(this.getName()+" wins!");
        } else {
            System.out.println(opponent.getName()+" wins!");
        }
    }
}
