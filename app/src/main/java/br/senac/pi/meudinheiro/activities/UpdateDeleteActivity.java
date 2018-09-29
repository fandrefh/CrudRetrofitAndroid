package br.senac.pi.meudinheiro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.meudinheiro.R;
import br.senac.pi.meudinheiro.models.Category;
import br.senac.pi.meudinheiro.remote.APIUtils;
import br.senac.pi.meudinheiro.services.CategoryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteActivity extends AppCompatActivity {

    String id;
    CategoryService categoryService;
    Category category;
    EditText edtCatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        setTitle("Atualização de Categoria");

        id = this.getIntent().getStringExtra("id");

        categoryService = APIUtils.getCategoryService();

        category = new Category();

        edtCatName = findViewById(R.id.edt_cat_name);

        findViewById(R.id.btn_update_cat).setOnClickListener(catUpdate());
        findViewById(R.id.btn_delete_cat).setOnClickListener(delCategory());

        setEdtUpdateCategory();
    }

    private View.OnClickListener delCategory() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Category> call = categoryService.deleteCategory(Integer.parseInt(id));
                call.enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.code() == 204) {
                            Toast.makeText(UpdateDeleteActivity.this, "Categoria excluída com sucesso.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateDeleteActivity.this, CategoryActivity.class);
                            startActivity(intent);
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

    private View.OnClickListener catUpdate() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.setName(edtCatName.getText().toString());
                Call<Category> call = categoryService.updateCategory(Integer.parseInt(id), category);
                call.enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateDeleteActivity.this, "Categoria atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpdateDeleteActivity.this, CategoryActivity.class);
                            startActivity(intent);
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

    private void setEdtUpdateCategory() {
        Call<Category> call = categoryService.getCategoryById(Integer.parseInt(id));
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()) {
                    edtCatName.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.e("curso", t.getMessage());
            }
        });
    }
}
