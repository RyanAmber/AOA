import java.util.*;

public class BonusLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums=scanner.nextInt();
        List<Integer> power=new ArrayList<Integer>();
        int iterations=scanner.nextInt();
        for(int i=0;i<nums;i++){
            power.add(scanner.nextInt());
        }
        String total="";
        for (int iter=0;iter<iterations&&iter<nums;iter++){
            int max=-1;
            int top=0;
            int added=0;
            for (int spider=0;spider<iterations+added&&spider<power.size();spider++){
                if(power.get(spider)>max){
                    top=spider;
                    max=power.get(spider);
                }
                if (power.get(spider)==-1){
                    added++;
                }
            }
            total+=(top+1)+" ";
            for (int i=0;i<iterations+added&&i<power.size();i++){
                if(i!=top){
                    power.set(i, power.get(i)>0?power.get(i) - 1:power.get(i));
                }else{
                    power.set(i,-1);
                }
            }
            System.out.println(power);
        }
        System.out.println(total);
        scanner.close();
    }
}