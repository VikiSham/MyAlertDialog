package com.example.myalertdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class CustomAlertDialog {
    private AlertDialog alertDialog;
    private ImageView alertImage;
    private TextView alertTitle;
    private TextView alertMessage;
    private Button btnNegative;
    private Button btnPositive;

    private Context context;
    private DialogClickListener listener;

    public interface DialogClickListener {
        void onPositiveButtonClick();
    }

    @SuppressLint("MissingInflatedId")
    public CustomAlertDialog(Context context, DialogClickListener listener) {
        this.context = context;
        this.listener = listener;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_alert_dialog, null);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_background);
        alertImage = view.findViewById(R.id.alertImage);
        alertTitle = view.findViewById(R.id.alertTitle);
        alertMessage = view.findViewById(R.id.alertMessage);
        btnNegative = view.findViewById(R.id.btnNegative);
        btnPositive = view.findViewById(R.id.btnPositive);

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // סגירת הדיאלוג הראשון
                alertDialog.dismiss();
                // פתיחת הדיאלוג השני
                showNextDialog();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPositiveButtonClick();
                alertDialog.dismiss();
            }
        });
    }

    // מתודה חדשה ליצירת הדיאלוג השני
    private void showNextDialog() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
        LayoutInflater inflater2 = LayoutInflater.from(context);
        // שימוש באותו קובץ עיצוב, אם זה מתאים למקרה
        View view2 = inflater2.inflate(R.layout.custom_alert_dialog, null);
        builder2.setView(view2);

        AlertDialog alertDialog2 = builder2.create();
        alertDialog2.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_background2);

        // מאתרים ומגדירים את האלמנטים של הדיאלוג השני
        TextView nextTitle = view2.findViewById(R.id.alertTitle);
        TextView nextMessage = view2.findViewById(R.id.alertMessage);
        Button nextBtnNegative = view2.findViewById(R.id.btnNegative);
        Button nextBtnPositive = view2.findViewById(R.id.btnPositive);

        nextTitle.setText("This is the next screen");
        nextMessage.setText("You can add new text here.");
        nextBtnPositive.setText("OK");
        nextBtnNegative.setVisibility(View.GONE); // לדוגמה, להעלים את כפתור "No" בדיאלוג השני

        nextBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Closing second dialog", Toast.LENGTH_SHORT).show();
                alertDialog2.dismiss();
            }
        });

        alertDialog2.show();
    }

    // שאר המתודות (setTitle, setMessage, וכו') נשארות כפי שהן
    public void setTitle(String title) {
        alertTitle.setText(title);
    }

    public void setMessage(String message) {
        alertMessage.setText(message);
    }

    // ... שאר הקוד
    public void setIcon(int idDrawable) {
        alertImage.setImageResource(idDrawable);
        alertImage.setVisibility(View.VISIBLE);
    }

    public void setButtonPositive(String text, int color) {
        btnPositive.setText(text);
        btnPositive.setTextColor(color);
    }

    public void setButtonNegative(String text, int color) {
        btnNegative.setText(text);
        btnNegative.setTextColor(color);
    }

    public void inVisibleButtonNegative() {
        btnNegative.setVisibility(View.GONE);
    }

    public void show() {
        alertDialog.show();
    }
}