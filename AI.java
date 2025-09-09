import java.util.*;
public class AI {
    public static void main(String[] args) {
        int ans=3;
        int[] amt=new int[6];
        List<Integer> data=new ArrayList<Integer>();
        for (int i=0;i<18;i++){
            data.add(i%6+1);
            amt[i%6]++;
        }
        for (int i=0;i<200;i++){
            int guess=data.get((int)(Math.random()*data.size()));
            if (guess==ans&&amt[guess-1]<10){
                data.add(guess);
            }else if (amt[guess-1]>1){
                data.remove(data.indexOf(guess));
            }
            //System.out.println(data);
            System.out.println(guess);
            if (Math.random()<0.01&&ans<6){
                ans++;
                System.out.println("UP"+ans);
            }else if(Math.random()<0.01&&ans>1){
                ans--;
                System.out.println("DOWN"+ans);
            }
        }
    }
}
