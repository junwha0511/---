import java.util.Scanner; 
class Koi_watch{ 
   public static void main(String[] args){
          Scanner s = new Scanner(System.in);
          String[] ary=s.nextLine().split(" ");
          int a=Integer.parseInt(ary[0]),b=Integer.parseInt(ary[1]),c=Integer.parseInt(ary[2]);
          int t=s.nextInt()+a*3600+b*60+c,q; 
          
          q=t/3600;
          System.out.print(((q!=0)?q:0)%24+" ");
          t=(q!=0)?t%3600:t;
           
          q=t/60;
          System.out.print(((q!=0)?q:0)+" ");
          t=(q!=0)?t%60:t;
           
          System.out.print(t+" ");

   }
   
}