package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface UserApi {
    String linkUser="http://demo3861295.mockable.io/";
    String user="User";
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    UserApi userApi=new Retrofit.Builder()
            .baseUrl(linkUser)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApi.class);
    @GET(user)
    Call<List<User>> getUser();

}
