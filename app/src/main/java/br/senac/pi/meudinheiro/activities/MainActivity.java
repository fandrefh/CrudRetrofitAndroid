package br.senac.pi.meudinheiro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.senac.pi.meudinheiro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_add_category).setOnClickListener(addCategory());
        findViewById(R.id.btn_add_expense).setOnClickListener(addExpense());
    }

    private View.OnClickListener addExpense() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener addCategory() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        };
    }
}
