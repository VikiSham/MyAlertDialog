package com.example.myalertdialog;

import static com.example.myalertdialog.R.drawable.*;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements CustomAlertDialog.DialogClickListener {

    ConstraintLayout layout;
    Button btn1, btn2, btn3;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleAlert();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configuredAlert();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAlert();
            }
        });
    }


    public void init()
    {
        layout=findViewById(R.id.layout);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

    }
    public void simpleAlert()
    {
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Simple Alert");
        builder.setMessage("This is a simple alert");
        builder.setIcon(R.drawable.outline_crisis_alert_24);
        builder.setCancelable(false);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "This is Simple Alert", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Close",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog=builder.create();
        alertDialog.show();
    }

    private void configuredAlert() {
        final  String[] colors={"Red","Green","Blue"};
        int [] color=new int[]{0,0,0};
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.outline_crisis_alert_24);

        //Open one of the options
        //oneChoiseList(colors,color);
        //multiChoiseList(colors,color);
        inputAlert();

        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog=builder.create();
        alertDialog.show();
    }

    private void oneChoiseList(String[] colors,int [] color)
    {
        builder.setTitle("Configured Alert\nThis is List of colors - one choise");
        //setMessage not working with setItems!!!
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                color[i]=255;
                layout.setBackgroundColor(android.graphics.Color.rgb(color[0],color[1],color[2]));
            }
        });
    }
    private void multiChoiseList(String[] colors,int [] color)
    {
        builder.setTitle("Configured Alert\nThis is List of colors - multi choise");
        builder.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                if(isChecked)
                    color[i]=255;
                else if(color[i]==255)
                    color[i]=0;
                layout.setBackgroundColor(android.graphics.Color.rgb(color[0],color[1],color[2]));
            }
        });
    }
    private void inputAlert() {
        builder.setTitle("Configured Alert\nThis is Input Alert");
        // כאשר יש יותר מרכיב גרפי אחד להצגה ע"ג הדיאלוג
        // ועמ למנוע מצב שאחד דורס את השני יוצרים תסדיר דינמי ועליו מסדרים את כל הרכיבים
        // יוצרים LinearLayout כדי להכיל את כל הרכיבים
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL); // מסדר את הרכיבים בטור
        layout.setPadding(30, 30, 30, 30); // מוסיפים ריפוד לשיפור המראה
        layout.setBackgroundColor(Color.CYAN);
        // יוצרים את הרכיבים
        final EditText et1=new EditText(MainActivity.this);
        final EditText et2=new EditText(MainActivity.this);
        final TextView tv=new TextView(MainActivity.this);
        final Button btn=new Button(MainActivity.this);
        et1.setHint("Enter first number");
        et2.setHint("Enter second number");
        btn.setText("Calculate");
        btn.setBackgroundColor(Color.MAGENTA);
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);

        // מוסיפים את הרכיבים ל-LinearLayout
        layout.addView(et1);
        layout.addView(et2);
        layout.addView(tv);
        layout.addView(btn);
        // מכניסים את ה-LinearLayout לתוך הדיאלוג
        builder.setView(layout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1,s2;
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                if(s1.equals("")||s2.equals(""))
                {
                    tv.setText("Enter both numbers");
                }
                else {
                    int num1 = Integer.parseInt(s1);
                    int num2 = Integer.parseInt(s2);
                    tv.setText("Sum of 2 numbers = " + (num1 + num2));
                }
            }
        });
    }

    private void customAlert() {
        customAlertDialog = new CustomAlertDialog(MainActivity.this, (CustomAlertDialog.DialogClickListener) this);
        customAlertDialog.setIcon(outline_crisis_alert_24);
        customAlertDialog.setTitle("Important Message!!!");
        customAlertDialog.setMessage("This is a custom alert!\n" +
                "Pressing on 'continue' button will move to the next screen.");
        customAlertDialog.setButtonPositive("Continue", Color.GREEN);
        customAlertDialog.setButtonNegative("No", Color.RED);
        //customAlertDialog.inVisibleButtonNegative();
        customAlertDialog.show();
    }

    @Override
    public void onPositiveButtonClick() {
        Toast.makeText(this, "Yes!", Toast.LENGTH_SHORT).show();

    }
}