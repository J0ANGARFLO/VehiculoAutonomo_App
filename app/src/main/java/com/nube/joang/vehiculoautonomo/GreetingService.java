package com.nube.joang.vehiculoautonomo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GreetingService {
    @GET(ConstantesRestApi.URL_GET_STARTENGINE)
    Call<Greeting> getGreeting(@Path("grupo_id") String grupo_id);
}
