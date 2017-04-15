package com.usc.searchonfb.Rest.NetworkInterface;

import com.usc.searchonfb.Rest.Model.SearchModel.SearchDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adarsh on 4/15/2017.
 */

public interface GetLocationSearchService {

    @GET("search_aws.php?")
    Observable<SearchDataList> getDataList(@Query("search_query") String query, @Query("type") String type, @Query("lat") String lat, @Query("lng") String lng);

}
