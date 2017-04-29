import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.animation.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;

public class main extends Application{
  public static int width=1000;
  public static int height=1000;
  public static void main(String[] args){
    launch(args);
  }
  public void start(Stage alphaStage){
    alphaStage.setTitle("Drawing stuffs");
    Group root=new Group();
    Scene scene=new Scene(root);
    Scanner scan=new Scanner(System.in);

    alphaStage.setScene(scene);

    Canvas canvas=new Canvas(width,height);
    root.getChildren().add(canvas);

    GraphicsContext gc=canvas.getGraphicsContext2D();
    System.out.println("Number of wheels");
    System.out.print(">>");
    int num=scan.nextInt();
    //int[] wheels=new int[num];
    List<Double> sizes = new ArrayList<>();
    //for(int i=0;i<wheels.length;i++){
      //System.out.println("Size of wheel "+(i+1)+" compared to wheel 1");
      //System.out.print(">>");
      //sizes.add(scan.nextDouble());
    //}

    final List<Double> total = sizes;
    final int numOfSides=num;
    List<Integer> tx=new ArrayList<>();
    List<Integer> ty=new ArrayList<>();

    for(int i=0;i<numOfSides;i++){
      tx.add((int)(Math.random()*width));
      ty.add((int)(Math.random()*height));
    }
    int r=numOfSides;
    double inc=(2*Math.PI)/(double)r;
    //Making circle
    ty.clear();
    tx.clear();
    for(int n=0;n<r;n++){
      gc.fillOval((double)450*Math.sin(n*inc)+width/2,(double)450*Math.cos(n*inc)+height/2,1,1);
      ty.add((int)((double)450*Math.cos(n*inc)+height/2));
      tx.add((int)((double)450*Math.sin(n*inc)+width/2));
    }
    final List<Integer> x=tx;
    final List<Integer> y=ty;

    final long startNanoTime=System.nanoTime();

    new AnimationTimer(){
      public int it=0;
      public List<Double> wheelers=total;
      public List<Integer> xCo=x;
      public List<Integer> yCo=y;
      public int startX=0;
      public int startY=0;
      public double compute(int switcher,double time){
        double summation=0;
        switch(switcher){
          case 0:
            for(double a: wheelers){
              summation+=Math.sin(a*time);
            }
          break;
          case 1:
            for(double a: wheelers){
              summation+=Math.cos(a*time);
            }
          break;
        }
        return summation;
      }
      public double factorial(double n){
        if(n<=2){
          return n;
        }
        return n*1/factorial(n-1);
      }
      public int selectX(int s){
        startX=(startX+xCo.get(s))/3;
        return startX;
      }
      public int selectY(int s){
          startY=(startY+yCo.get(s))/3;
          return startY;
      }
      public int num=0;
      public int gg=1;
      public void handle(long currentNanoTime){
        double t=currentNanoTime-startNanoTime;
        t=(t/10000000000.0)+0;
        it++;
        //int p=it/(255*255);
        //int o=(it/255)-p*255;
        //int l=it-o*255-p*255*255;
        //gc.setFill(Color.rgb(p,o,l));
        //gc.setFill(Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        gc.setFill(Color.rgb((int)(0*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        //for(double i=10;i<=20;i=i+2){gc.fillOval(5*i*Math.tan(t)+500,5*i*Math.cos(t)+500,4,4);}
        //gc.fillOval(10*t-num,50*factorial(t),1,1);
        //System.out.println(num+";"+t+";"+width);
        //if(t>width*gg/10){gg++;num+=width;}
        int selection=(int)(Math.random()*numOfSides);
        for(int i=0;i<numOfSides;i++){
          gc.fillOval(xCo.get(i),yCo.get(i),3,3);
        }
        gc.fillOval(selectX(selection),selectY(selection),1,1);
        //gc.fillOval(70*compute(0,t)+500,70*compute(1,t)+500,2,2);
        //gc.fillOval(10*Math.tan(t)+500,10*t+500,2,2);
        //gc.fillOval(100*(1-2*t)*Math.cos(99*t)+500,100*(1-2*t)*Math.sin(100*t)+500,2,2);
        //gc.fillOval(100*4*Math.sin(1*t/4)+500,100*3*Math.sin(t)+500,2,2);
        //gc.fillOval(100*Math.pow(Math.cos(3*t/4),3)+500,100*Math.pow(Math.sin(t),3)+500,2,2);
        //gc.fillOval(100*(1*Math.sin(t)+Math.sin(10*t))+500,100*(1*Math.cos(t)+Math.cos(10*t))+500,2,2);
        //gc.fillOval(100*(3.5*Math.sin(t)-Math.sin(3*t)+0.7*Math.sin(29.25*t))+500,100*(4*Math.cos(t)-1.5*Math.cos(2*t)-0.6*Math.cos(3*t)-0.2*Math.cos(4*t))+500,2,2);
        //gc.fillOval(100*(1)*Math.sin(Math.tan(t))+500,100*(1)*Math.tan(Math.sin(t))+500,2,2);
        //gc.fillOval(100*(Math.sin(t))*(Math.pow(Math.E,Math.cos(t))-2*Math.cos(4*t)-Math.pow(Math.sin(t/12),5))+500,-100*(Math.cos(t))*(Math.pow(Math.E,Math.cos(t))-2*Math.cos(4*t)-Math.pow(Math.sin(t/12),5))+500,2,2);
        //gc.setFill(Color.rgb(255,0,0));for(double i=10;i>=0;i=i-0.01){gc.fillOval(i*16*Math.pow(Math.sin(t),3)+500,-i*(13*Math.cos(t)-5*Math.cos(2*t)-2*Math.cos(3*t)-Math.cos(4*t))+500,2,2);}
        //gc.setFill(Color.rgb(0,0,255));gc.fillOval(t*50+500,500,1,1);gc.fillOval(500,t*50+500,1,1);
      }
    }.start();
    alphaStage.show();
  }
}
