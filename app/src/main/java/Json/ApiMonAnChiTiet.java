package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiMonAnChiTiet {
    String linkLisMonAn="http://demo3861295.mockable.io/";
    String supCozido="SupCozido";
    String banhBruschessta="BanhBruschessta";
    String miY="MiY";
    String banhRosti="BanhRosti";
    String banhPizza="banhPizzza";
    String banhCrepe="BanhCrepe";
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create();
    //link API http://demo3861295.mockable.io/ListMonAn"
   ApiMonAnChiTiet apiserver=new Retrofit.Builder().
            baseUrl(linkLisMonAn)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiMonAnChiTiet.class);
    @GET(supCozido)
    Call<ChiTietMonAn> supCoZido();
    @GET(banhBruschessta)
    Call<ChiTietMonAn> banhBruschessta();
    @GET(miY)
    Call<ChiTietMonAn> miY();
    @GET(banhRosti)
    Call<ChiTietMonAn> banhRosti();
    @GET(banhPizza)
    Call<ChiTietMonAn> banhPizza();
    @GET(banhCrepe)
    Call<ChiTietMonAn> banhCrepe();
}