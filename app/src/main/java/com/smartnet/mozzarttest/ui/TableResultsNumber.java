package com.smartnet.mozzarttest.ui;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.smartnet.mozzarttest.R;
import com.smartnet.mozzarttest.networking.networkingModels.Results;

import java.util.ArrayList;

public class TableResultsNumber extends TableLayout {
    private int colums;
    private int rows;
    private ArrayList<Integer> winingNumbers;
    private static TableResultsNumber instance;


    public TableResultsNumber(Context context, int colums, int rows, ArrayList<Integer> winingNumbers) {
        super(context);
        this.rows = rows;
        this.colums = colums;
        this.winingNumbers = winingNumbers;
        createTableWithNumbers();
        this.setStretchAllColumns(true);
    }


    /**
     * Helper method to provide table row for table layout
     *
     * @return
     */
    private TableRow provideTableRow() {
        TableRow tableRow = new TableRow(super.getContext());
        return tableRow;
    }


    /**
     * Helper method to create table with rows and colums
     */
    private void createTableWithNumbers() {
        int number = -1;
        for (int i = 0; i < rows; i++) {
            TableRow tr = provideTableRow();
            for (int j = 0; j < colums; j++) {
                number++;
                int winingNumber = winingNumbers.get(number);
                TextViewWiningNumbers textViewWiningNumbers = new TextViewWiningNumbers(getContext(), winingNumber);
                tr.addView(textViewWiningNumbers);
            }
            this.addView(tr);
        }
    }
}
