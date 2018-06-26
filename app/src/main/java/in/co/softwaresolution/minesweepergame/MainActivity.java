package in.co.softwaresolution.minesweepergame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener {

    boolean ch=false;
    public static int SIZE=8;
    public static int BOMB_SIZE;
    LinearLayout rl;
    ArrayList<LinearLayout> root;
    public static ArrayList<MSBoard> ijnumbers=new ArrayList<>();
    int[] ai=new int[]{-1,-1,-1,0,0,1,1,1};
    int[] aj=new int[]{-1,0,1,-1,1,-1,0,1};

    public static final int BOMB=-1;
    public static final int NO_BOMB=0;
    public static final int NUMBER=1;

    public static final int INCOMPLETE=1;
    public static final int WON_GAME=2;

    MSBoard[][] msBoard;
    ArrayList<ABC> randomNo;

    public int currentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl=findViewById(R.id.rootLayout);
        setBoardButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.resetID){
            ch=false;
            setBoardButton();
        }
        else if(id == R.id.easyID){
            ch=false;
            SIZE=6;
            setBoardButton();
        }
        else if(id == R.id.mediumID){
            ch=false;
            SIZE=8;
            setBoardButton();
        }
        else if(id == R.id.hardID){
            ch=false;
            SIZE=10;
            setBoardButton();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBoardButton()
    {
        currentStatus=INCOMPLETE;
        setBoard();
    }


    public void setBoard()
    {
        BOMB_SIZE=(int)((SIZE*SIZE)*(.2));
        root=new ArrayList<>();
        msBoard=new MSBoard[SIZE][SIZE];
        rl.removeAllViews();
        for(int i=0;i<SIZE;i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            linearLayout.setLayoutParams(layoutParams);
            rl.addView(linearLayout);
            root.add(linearLayout);
        }

        for (int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                MSBoard ms=new MSBoard(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                layoutParams.setMargins(10,10,10,10);
                ms.setLayoutParams(layoutParams);
                ms.setOnClickListener(this);
                ms.setOnLongClickListener(this);
                LinearLayout ll=root.get(i);
                ll.addView(ms);
                msBoard[i][j]=ms;
                ms.ijvalues(i,j);
                ms.setPadding(10,10,10,10);
                ms.setBackgroundColor(getResources().getColor(R.color.lightblue));
            }
        }

    }

    public void setupRandomNo()
    {
        ABC abc=new ABC();
        randomNo=abc.main();
    }

    public void setupBomb()
    {
        MSBoard msbutton;
        ABC al;
        int a;
        int b;
        for(int i=0;i<BOMB_SIZE;i++)
        {
            al=randomNo.get(i);
            a=al.i;
            b=al.j;
            msbutton=msBoard[a][b];
            msbutton.setNumber(BOMB);


        }

    }

    public void setupBombCover() {
        MSBoard msbutton;
        ABC al;
        int a;
        int b;

        for (int m = 0; m < BOMB_SIZE; m++) {
            al = randomNo.get(m);
            for(int n=0;n<8;n++){
            a = al.i + ai[n];
            b = al.j + aj[n];
            if ((a >= 0 && a <=SIZE-1) && (b >= 0 && b <=SIZE-1)) {
                msbutton = msBoard[a][b];
                if (msbutton.getType() != BOMB) {

                    msbutton.setNumber(NUMBER);
                }
            }
            }
        }

    }

        public void onClick(View view){

            if (currentStatus == INCOMPLETE) {
                MSBoard button = (MSBoard) view;
                if (button.getlongCount() == false) {
                        if (ch == false) {
                            button.setNumber(NO_BOMB);
                            ijnumbers.clear();
                            arrayFill(button);
                            setupRandomNo();
                            setupBomb();
                            setupBombCover();
                            ch = true;
                        }
                    checkGameStatus(button);
                }
            }

        }

        public void arrayFill(MSBoard board)
        {
            MSBoard board1;
            int a;
            int b;
            a=board.getI();
            b=board.getJ();
            board1=msBoard[a][b];
            ijnumbers.add(board1);
            for(int i=0;i<8;i++)
            {
                a=board.getI()+ai[i];
                b=board.getJ()+aj[i];
                if((a>=0 && a<=SIZE-1) && (b>=0 && b<=SIZE-1)){
                    board1=msBoard[a][b];
                    ijnumbers.add(board1);
                }
            }
        }


    public void freeze()
    {
        MSBoard button;
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                button=msBoard[i][j];
                button.setEnabled(false);
            }
        }
    }

    public void checkGameStatus(MSBoard bb)
    {
        MSBoard msBoard2;
        bb.setEnabled(false);
        bb.updateCount();
        bb.setBackgroundColor(getResources().getColor(R.color.mediumblue));
        if((bb.getType() == BOMB))
        {
            Toast.makeText(this,"Game Over",Toast.LENGTH_LONG).show();
            for(int i=0;i<SIZE;i++)
            {
                for(int j=0;j<SIZE;j++)
                {
                    msBoard2=msBoard[i][j];
                    if(msBoard2.getType() == BOMB) {
                        msBoard2.setBackgroundResource(R.drawable.abc);
                    }
                }
            }
            freeze();
        }
        else if(bb.getType() == NUMBER)
        {
            bb.setText(Integer.toString(bb.getNumber()));
            bb.setTextColor(getResources().getColor(R.color.black));
            bb.setBackgroundColor(getResources().getColor(R.color.mediumblue));

        }
        else if(bb.getType() == NO_BOMB){

            MSBoard msbutton;
            int a;
            int b;
            bb.updateNoLoop();
            bb.updateCount();
            bb.setBackgroundColor(getResources().getColor(R.color.mediumblue));
            bb.setTextColor(getResources().getColor(R.color.black));


            for(int i=0;i<8;i++)
            {
                a =bb.getI()+ai[i];
                b =bb.getJ()+aj[i];
                if ((a >= 0 && a <= SIZE-1) && (b >= 0 && b <= SIZE-1)) {
                    msbutton = msBoard[a][b];
                        if (msbutton.getType() == NO_BOMB) {
                            if (msbutton.getNoLoop() == false) {
                                checkGameStatus(msbutton);
                            }
                        }
                        if(msbutton.getType() == NO_BOMB){
                            msbutton.setText(" ");
                            msbutton.setBackgroundColor(getResources().getColor(R.color.mediumblue));
                            msbutton.setTextColor(getResources().getColor(R.color.black));
                            msbutton.updateCount();
                        }
                        else {
                            msbutton.setBackgroundColor(getResources().getColor(R.color.mediumblue));
                            msbutton.setText(Integer.toString(msbutton.getNumber()));
                            msbutton.setTextColor(getResources().getColor(R.color.black));
                            msbutton.updateCount();
                        }
                    msbutton.setEnabled(false);
            }

            }

    }
        int flag=0;
        MSBoard msBoard1;
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                msBoard1=msBoard[i][j];
                if((msBoard1.getType() == NO_BOMB)||(msBoard1.getType() == NUMBER))
                {
                    if(msBoard1.getCount()==0)
                    {
                        flag=1;
                        break;
                    }
                }
            }
        }

        if(flag==0) {
            Toast.makeText(this, "WON", Toast.LENGTH_LONG).show();
            currentStatus = WON_GAME;

        }
    }

    @Override
    public boolean onLongClick(View view) {

        MSBoard msBoard3;
        if(currentStatus == INCOMPLETE)
        {

            msBoard3=(MSBoard)view;
            if(msBoard3.getlongCount() == false)
            {
                msBoard3.setBackgroundColor(Color.BLUE);
                msBoard3.updatelongCount();
            }
            else if(msBoard3.getlongCount() == true)
            {
                msBoard3.setBackgroundColor(getResources().getColor(R.color.lightblue));
                msBoard3.setTextColor(getResources().getColor(R.color.black));
                msBoard3.updatelongCount();
            }

        }
        return true;
    }
}
