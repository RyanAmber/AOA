public class QuadraticFunction{
    private int a;
    private int b;
    private int c;
    QuadraticFunction(int a, int b, int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public double valueAt(double x){
        return a*x*x+b*x+c;
    }
    public String toString(){
        String str="";
        if (a!=0){
            if (a==1||a==-1){
                str+="x^2";
            }else{
                str+=a+"x^2";
            }
        }
        if (b!=0){
            if (b>0){
                str+="+";
            }
            if (b==1||b==-1){
                str+="x";
            }else{
                str+=b+"x";
            }
        }
        if (c!=0){
            if (c>0){
                str+="+";
            }
            str+=c;
        }
        return str;
    }
}