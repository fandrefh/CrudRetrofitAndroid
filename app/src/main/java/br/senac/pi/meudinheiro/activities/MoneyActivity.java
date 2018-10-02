package br.senac.pi.meudinheiro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.meudinheiro.R;
import br.senac.pi.meudinheiro.adapters.CategoryAdapter;
import br.senac.pi.meudinheiro.models.Category;
import br.senac.pi.meudinheiro.remote.APIUtils;
import br.senac.pi.meudinheiro.services.CategoryService;
import br.senac.pi.meudinheiro.services.MoneyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoneyActivity extends AppCompatActivity {

    private EditText edtExpenseDescription;
    private EditText edtValueExpense;
    private Spinner spCategories;

    MoneyService moneyService;
    CategoryService categoryService;

    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        setTitle("Adicionar Despesa");

        moneyService = APIUtils.getMoneyService();
        categoryService = APIUtils.getCategoryService();

        edtExpenseDescription = findViewById(R.id.edt_money_description);
        edtValueExpense = findViewById(R.id.edt_money_value);
        spCategories = findViewById(R.id.sp_category_name);

        setSpinnerListCategories();
    }

    private void setSpinnerListCategories() {
        Call<List<Category>> call = categoryService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categories = response.body();
                    spCategories.setAdapter(new CategoryAdapter(MoneyActivity.this,
                            categories));
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }
}
