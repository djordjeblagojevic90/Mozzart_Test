package com.smartnet.mozzarttest.ui;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class TableNumbers extends TableLayout {

    private int colums;
    private int rows;
    private ArrayList<Integer> listOfNumbers;

    public TableNumbers(Context context, int colums, int rows, ArrayList<Integer> listOfNumbers) {
        super(context);
        this.rows = rows;
        this.colums = colums;
        this.listOfNumbers = listOfNumbers;
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
        int number = 0;
        for (int i = 0; i < rows; i++) {
            TableRow tr = provideTableRow();
            for (int j = 0; j < colums; j++) {
                number++;
                TextViewNumbers textViewNumbers = new TextViewNumbers(getContext(), "" + number, listOfNumbers);
                tr.addView(textViewNumbers);
            }
            this.addView(tr);
        }
    }
}
