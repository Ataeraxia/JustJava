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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        displayQuantity(coffeeQuantity);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (coffeeQuantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            coffeeQuantity += 1;
            displayQuantity(coffeeQuantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (coffeeQuantity == 1) {
            Toast.makeText(this, "You cannot order fewer than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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
        String userName = getUserName.getText().toString();
        totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(hasWhippedCream, hasChocolate, userName);
//        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
//        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
//        }
    }

    /**
     * Calculates the total price of the order.
     *
     * @param hasWhippedCream stores whether or not the whipped cream box is checked.
     * @param hasChocolate stores whether or not the chocolate box is checked.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = coffeePrice;
        if (hasWhippedCream) {
            basePrice += whippedCreamPrice;
        }
        if (hasChocolate) {
            basePrice += chocolatePrice;
        }
        return basePrice * coffeeQuantity;
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
                                      String userName) {
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