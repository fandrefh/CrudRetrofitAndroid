package br.senac.pi.meudinheiro.remote;

import br.senac.pi.meudinheiro.services.CategoryService;

public class APIUtils {

    public static final String BASE_URL = "http://10.0.2.2:8000/api/v1/";

    public static CategoryService getCategoryService() {
        return APIClient.client(BASE_URL).create(CategoryService.class);
    }
}
