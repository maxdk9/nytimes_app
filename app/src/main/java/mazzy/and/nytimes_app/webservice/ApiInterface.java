package mazzy.and.nytimes_app.webservice;

import mazzy.and.nytimes_app.model.ResponseResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("svc/{mostpopular}/{v2}/{emailed}/{period}.json")
    Call<ResponseResult> getMostPopular(@Path("mostpopular") String mostpopular, @Path("v2") String v2 , @Path("emailed") String emailed, @Path("period") int period,
                                        @Query("api-key") String apiKey);

    //https://api.nytimes.com/svc/mostpopular/v2/emailed/{period}.json
}
