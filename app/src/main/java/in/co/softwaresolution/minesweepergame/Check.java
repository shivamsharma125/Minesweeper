package in.co.softwaresolution.minesweepergame;

import java.util.ArrayList;

public class Check {

    ArrayList<ABC> so=new ArrayList<>();

    public boolean checkNo(ABC o)
    {
        ABC test;

        if(so.isEmpty())
        {
            so.add(o);
        }
        else
        {
            int len=so.size();
            while(len!=0) {
                test =so.get(len-1);
                if((test.i==o.i)&&(test.j==o.j))
                {
                    return false;
                }
                else
                {
                    // System.out.println("Hello from else if comparison in Check");
                    len=len-1;
                }
            }
            so.add(o);

        }
        return true;
    }

    public ArrayList<ABC> print() {

//        ABC abc;
        //System.out.println(so.size());

//        for(int i=0;i<=19;i++)
//        {
//           // System.out.println("Hello");
//            abc=so.get(i);
//            System.out.print(abc.i);
//            System.out.print(",");
//            System.out.print(abc.j);
//            System.out.println();
//        }

        return so;
    }
}
