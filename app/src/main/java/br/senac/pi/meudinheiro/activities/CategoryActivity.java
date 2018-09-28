package br.senac.pi.meudinheiro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senac.pi.meudinheiro.R;
import br.senac.pi.meudinheiro.adapters.CategoryAdapter;
import br.senac.pi.meudinheiro.models.Category;
import br.senac.pi.meudinheiro.remote.APIUtils;
import br.senac.pi.meudinheiro.services.CategoryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    CategoryService categoryService;

    private ListView lvCategories;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setTitle(getString(R.string.category_screen_title));

        categoryService = APIUtils.getCategoryService();

        lvCategories = findViewById(R.id.lv_categories);

        findViewById(R.id.btn_save_category).setOnClickListener(saveCategory());
        findViewById(R.id.btn_show_categories).setOnClickListener(listAllCategories());
    }

    private View.OnClickListener listAllCategories() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Category>> call = categoryService.getCategories();
                call.enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.isSuccessful()) {
                            categoryList = response.body();
                            lvCategories.setAdapter(new CategoryAdapter(CategoryActivity.this, categoryList));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Log.e("curso", t.getMessage());
                    }
                });
            }
        };
    }

    private View.OnClickListener saveCategory() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                final EditText edtCategoryName = findViewById(R.id.edt_category_name);
                category.setName(edtCategoryName.getText().toString());
                Call<Category> call = categoryService.addCategory(category);
                call.enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CategoryActivity.this,
                                    getString(R.string.category_success), Toast.LENGTH_LONG)
                                    .show();
                            edtCategoryName.setText("");
                            edtCategoryName.requestFocus();
                        }
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        Log.e("curso", t.getMessage());
                    }
                });
            }
        };
    }
}
