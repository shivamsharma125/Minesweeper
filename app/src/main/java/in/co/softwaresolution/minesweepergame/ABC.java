package in.co.softwaresolution.minesweepergame;

//public class ABC {
//}

import java.util.ArrayList;
import java.util.Random;

public class ABC
{
    int i;
    int j;

    public static ArrayList<ABC> main()
    {
        Check ch=new Check();
        boolean b;
        int len=MainActivity.BOMB_SIZE;
        int arri[]=new int[9];
        int arrj[]=new int[9];
        for(int i=0;i<(MainActivity.ijnumbers.size());i++){
            arri[i]=(MainActivity.ijnumbers.get(i)).getI();
            arrj[i]=(MainActivity.ijnumbers).get(i).getJ();
        }

        while(len!=0){
            b=false;
            ABC abc=new ABC();
            Random rd=new Random();
            int rno1=rd.nextInt(MainActivity.SIZE);
            int rno2=rd.nextInt(MainActivity.SIZE);
                abc.i = rno1;
                abc.j = rno2;
                boolean flag=false;
                for(int i=0;i<(MainActivity.ijnumbers.size());i++){
                    if((abc.i == arri[i]) && (abc.j == arrj[i])){
                        flag=true;
                        break;
                    }
                }
                if(flag == false) {
                    b = ch.checkNo(abc);
                }
                //  System.out.print("b =");
                //  System.out.println(b);
                if (b == true) {
                    len = len - 1;
                }
        }

       ArrayList<ABC> arr=ch.print();
        return arr;
    }

}