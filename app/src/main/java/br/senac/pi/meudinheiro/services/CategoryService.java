package br.senac.pi.meudinheiro.services;

import java.util.List;

import br.senac.pi.meudinheiro.models.Category;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("categories/")
    Call<List<Category>> getCategories();

    @POST("categories/")
    Call<Category> addCategory(@Body Category category);

    @PUT("categories/{id}/")
    Call<Category> updateCategory(@Path("id") int id, @Body Category category);

    @DELETE("categories/{id}/")
    Call<Category> deleteCategory(@Path("id") int id);
}
