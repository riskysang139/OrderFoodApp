package Json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataMonJson {
    String listMonAn="ListMonAn";
    @GET(listMonAn)
    Call<DataMonAnList> monTay();
}
