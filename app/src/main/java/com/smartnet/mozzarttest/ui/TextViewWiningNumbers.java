package com.smartnet.mozzarttest.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.smartnet.mozzarttest.R;

import java.util.ArrayList;

public class TextViewWiningNumbers extends androidx.appcompat.widget.AppCompatTextView {

    private int winingNumber;


    public TextViewWiningNumbers(Context context, int winingNumber) {
        super(context);
        this.winingNumber = winingNumber;
        this.setText("" + winingNumber);
        this.setTextSize(18);
        this.setGravity(Gravity.CENTER);
        this.setTextColor(getResources().getColor(R.color.black, context.getTheme()));
    }
}
