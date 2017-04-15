package com.usc.searchonfb.Rest.NetworkInterface;

import com.usc.searchonfb.Rest.Model.DetailModel.DetailsData;
import com.usc.searchonfb.Rest.Model.SearchModel.SearchDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adarsh on 4/15/2017.
 */

public interface GetDetailService {
    @GET("search_aws.php?")
    Observable<DetailsData> getDetailList(@Query("id") String id, @Query("details") String details);
}
