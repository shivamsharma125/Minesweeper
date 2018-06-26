package in.co.softwaresolution.minesweepergame;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class MSBoard extends AppCompatButton {

    public  boolean firstCheck=false;
    private boolean noLoop=false;
    private boolean longCount=false;
    private int count=0;
    private int i;
    private int j;
    private int type=MainActivity.NO_BOMB;
    private int number=0;

    public MSBoard(Context context) {
        super(context);
    }

    public boolean getNoLoop()
    {
        return noLoop;
    }
    public void updateNoLoop()
    {
        if(noLoop == true)
        {
            noLoop=false;
        }
        else
        {
            noLoop=true;
        }
    }
    public void correctNo()
    {
        number=number-1;
    }
    public void updatelongCount()
    {
        if(longCount == true)
        {
            longCount=false;
        }
        else
        {
            longCount=true;
        }
    }

    public boolean getlongCount()
    {
        return longCount;
    }

    public void updateCount()
    {
        count=1;
    }

    public int getCount()
    {
        return count;
    }

    public void ijvalues(int i,int j){

        this.i=i;
        this.j=j;
    }

    public int getI()
    {
        return this.i;
    }
    public int getJ()
    {
        return this.j;
    }

    public void setNumber(int type)
    {

        if(type == MainActivity.BOMB)
        {
            number=-1;
            this.type=MainActivity.BOMB;
        }
        else if(type == MainActivity.NO_BOMB)
        {
            number=0;
            this.type=MainActivity.NO_BOMB;
        }
        else if(type == MainActivity.NUMBER)
        {
            number=number+1;
            this.type=MainActivity.NUMBER;
        }

    }

    public int getType()
    {
        return this.type;
    }

    public int getNumber()
    {
        return this.number;
    }

}
