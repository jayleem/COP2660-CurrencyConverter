package com.jayleem.currencyconversion;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.math.BigDecimal;
        import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double conversionRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Display action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Instantiate controls
        final EditText numPrint = (EditText) findViewById(R.id.numCurrency);
        final RadioButton radioBtns[] = {(RadioButton) findViewById(R.id.radioBtnOpt1), (RadioButton) findViewById(R.id.radioBtnOpt2), (RadioButton) findViewById(R.id.radioBtnOpt3)};
        final Button myButton = (Button) findViewById(R.id.orderBtn);
        final TextView costLabel = (TextView) findViewById(R.id.costLabel);

        //RadioButton event listener
        for (RadioButton button : radioBtns) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Get sender and assign it as tmpButton
                    RadioButton tmpBtn = (RadioButton) findViewById(view.getId());

                    //Logic for updating cost variable
                    if (tmpBtn == radioBtns[0]) {
                        conversionRate = 0.901183;
                    }
                    if (tmpBtn == radioBtns[1]) {
                        conversionRate = 19.4055;
                    }
                    if (tmpBtn == radioBtns[2]) {
                        conversionRate = 1.32800;
                    }

                    //Uncheck other radio buttons
                    for (RadioButton button : radioBtns) {
                        if (button != tmpBtn) {
                            button.setChecked(false);
                        }
                    }
                }
            });
        }

        //Set myButton event listener
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat currency = new DecimalFormat("$###,###.################");
                if (numPrint.getText().length() > 0) {
                    if (Integer.valueOf(numPrint.getText().toString()) > 0 && Integer.valueOf(numPrint.getText().toString()) <= 100000)
                    {
                        double total = conversionRate * Integer.valueOf(numPrint.getText().toString());
                        costLabel.setText(currency.format(total));
                        //BigDecimal total = new BigDecimal((total));
                        //costLabel.setText(total.toString());
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Currency amount must be between 1 and 100,000", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Currency amount must have a value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}