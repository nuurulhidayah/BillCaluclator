package sg.edu.rp.c346.id19040408.billcaluclator;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etBill, etNumPax, etDiscount;
    ToggleButton tbSvs, tbGst;
    TextView tvTotalBill, tvEachPay;
    Button btnSplit, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBill = findViewById(R.id.editTextBill);
        etNumPax = findViewById(R.id.editTextNumPax);
        tbSvs = findViewById(R.id.toggleButtonSVS);
        tbGst = findViewById(R.id.toggleButtonGST);
        etDiscount = findViewById(R.id.editTextDiscount);
        tvTotalBill = findViewById(R.id.textViewTotalBill);
        tvEachPay = findViewById(R.id.textViewEachPays);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double bill = 0.00;
                if (etBill.getText().toString().trim().length() > 0 && etNumPax.getText().toString().trim().length() > 0){
                    if (tbSvs.isChecked()==false && tbGst.isChecked()==false) {
                        bill = Double.parseDouble(etBill.getText().toString());
                    }
                    else if (tbSvs.isChecked()==true && tbGst.isChecked()==false) {
                        bill = Double.parseDouble(etBill.getText().toString()) * (1 + 0.07);
                    }
                    else if (tbSvs.isChecked()==false && tbGst.isChecked()==true) {
                        bill = Double.parseDouble(etBill.getText().toString()) * (1 + 0.10);
                    }
                    else if (tbSvs.isChecked()==true && tbGst.isChecked()==true) {
                        bill = Double.parseDouble(etBill.getText().toString()) * (1 + 0.10 + 0.07);
                    }

                    if (etDiscount.getText().toString().length() > 0) {
                        bill = bill * (1 - Double.parseDouble(etDiscount.getText().toString()) / 100);
                    }

                    tvTotalBill.setText("Total Bill: $" + String.format("%.2f", bill));

                    int numPax = Integer.parseInt(etNumPax.getText().toString());
                    if (numPax > 0) {
                        tvEachPay.setText("Each Pays: $" + String.format("%.2f", bill/numPax));
                    }
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etBill.setText("");
                etNumPax.setText("");
                tbSvs.setChecked(false);
                tbGst.setChecked(false);
                etDiscount.setText("");
                tvTotalBill.setText("Total Bill: $0.00");
                tvEachPay.setText("Each Pays: $0.00");
            }
        });
    }
}
