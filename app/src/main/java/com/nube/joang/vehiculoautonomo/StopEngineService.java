package com.nube.joang.vehiculoautonomo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StopEngineService {
    @GET(ConstantesRestApi.URL_GET_STOPENGINE)
    Call<mapperAttributes> getStopEngine();
}
