package in.co.softwaresolution.minesweepergame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home_Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String Preference="shared_preference";
    public static final String NAME_KEY="name_key";
    public static final String AGE_KEY="age_key";

    EditText editTextName;
    EditText editTextAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        editTextName =findViewById(R.id.nameEditText);
        editTextAge=findViewById(R.id.ageEditText);

        sharedPreferences=getSharedPreferences(Preference,MODE_PRIVATE);
        String name=sharedPreferences.getString(NAME_KEY,null);
        int age=sharedPreferences.getInt(AGE_KEY,0);
        if(name!=null) {
            editTextName.setText(name);
        }
        if(age!=0){
            editTextAge.setText(Integer.toString(age));
        }

    }

    public void onHomeClick(View view) {
        String name= editTextName.getText().toString();
        int age;
        if(editTextAge.getText().toString().isEmpty()){
            age=0;
        }
        else {
            age = Integer.parseInt(editTextAge.getText().toString());
        }

            if (name.isEmpty()) {
                editTextName.setError("Please enter your name");
            } else {
                if ((age > 3)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(NAME_KEY, name);
                    editor.putInt(AGE_KEY,age);
                    editor.commit();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    editTextAge.setError("Below 3 not allowed");
                    }
                }
            }

}
