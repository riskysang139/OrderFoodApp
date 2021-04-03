package Json;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhoToArrayJson {
    String listMonAn="ListMonAn";
    @GET(listMonAn)
    Call<PhoToArray> image();
    @GET(listMonAn)
    Call<PhoToArray1> image1();
    @GET(listMonAn)
    Call<PhoToArray2> image2();
    @GET(listMonAn)
    Call<PhoToArray3> image3();
    @GET(listMonAn)
    Call<ThongBaoArray1> thongBao1();
    @GET(listMonAn)
    Call<ThongBaoArray2> thongBao2();
    @GET(listMonAn)
    Call<ThongBaoArray3> thongBao3();
}
