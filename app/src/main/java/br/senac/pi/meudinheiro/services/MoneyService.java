package br.senac.pi.meudinheiro.services;

import java.util.List;

import br.senac.pi.meudinheiro.models.Money;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MoneyService {

    @GET("money/")
    Call<List<Money>> getAllMoney();

    @GET("money/{id}/")
    Call<Money> getMoneyById(@Path("id") long id);

    @POST("money/")
    Call<Money> addMoney(@Body Money money);

    @PUT("money/{id}/")
    Call<Money> updateMoney(@Path("id") long id, @Body Money money);

    @DELETE("money/{id}/")
    Call<Money> deleteMoney(@Path("id") long id);
}
