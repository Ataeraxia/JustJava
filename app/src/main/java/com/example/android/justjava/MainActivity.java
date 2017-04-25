/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    // GLOBAL VARIABLES

    // Number of cups of coffee ordered
    int coffeeQuantity = 2;

    //Cost of one coffee
    int coffeePrice = 5;

    //Cost of whipped cream
    int whippedCreamPrice = 1;

    //Cost of chocolate
    int chocolatePrice = 2;

    //Total price of the order
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        coffeeQuantity += 1;
        displayQuantity(coffeeQuantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        coffeeQuantity -= 1;
        displayQuantity(coffeeQuantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText getUserName = (EditText) findViewById(R.id.user_name_edittext);
        Editable userName = getUserName.getText();
        totalPrice = coffeeQuantity * coffeePrice;
        if (hasWhippedCream & hasChocolate) {
            totalPrice += whippedCreamPrice + chocolatePrice;
        }
        else if (hasWhippedCream) {
            totalPrice += whippedCreamPrice;
        }
        else if (hasChocolate) {
            totalPrice += chocolatePrice;
        }
        else {
            totalPrice = coffeeQuantity * coffeePrice;
        }
        String orderSummary = createOrderSummary(hasWhippedCream, hasChocolate, userName);
        displayMessage(orderSummary);
    }
    /**
     * Creates the order summary text.
     *
     * @param userName is the text input of our EditText, user_name_edittext.
     * @param hasWhippedCream stores whether or not the whipped cream box has been checked.
     * @param hasChocolate stores whether or not the chocolate box has been checked.
     *
     * @return orderSummary.
     */
    private String createOrderSummary(boolean hasWhippedCream,
                                      boolean hasChocolate,
                                      Editable userName) {
        String orderSummary = "Name: " + userName;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + coffeeQuantity;
        orderSummary += "\nTotal: $" + totalPrice;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int amount ) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}