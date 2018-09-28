package com.nube.joang.vehiculoautonomo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StartEngineService {
    @GET(ConstantesRestApi.URL_GET_STARTENGINE)
    Call<mapperAttributes> getStartEngine();
}
