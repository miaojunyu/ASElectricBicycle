package com.wxxiaomi.ming.electricbicycle.api.service;


import com.wxxiaomi.ming.electricbicycle.db.bean.Option;
import com.wxxiaomi.ming.electricbicycle.db.bean.User;
import com.wxxiaomi.ming.electricbicycle.db.bean.UserCommonInfo;
import com.wxxiaomi.ming.electricbicycle.db.bean.UserLocatInfo;

import com.wxxiaomi.ming.electricbicycle.db.bean.format.FootPrintGet;
import com.wxxiaomi.ming.electricbicycle.db.bean.format.UserInfo;
import com.wxxiaomi.ming.electricbicycle.api.constant.Result;
import com.wxxiaomi.ming.electricbicycle.improve.update.Version;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 12262 on 2016/5/31.
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("android/lbs_publishfootprint")
    Observable<Result<String>> publishFootPrint(@Field("content") String content,@Field("picture") String picture
            ,@Field("locat_tag") String locat_tag,@Field("lat") double lat,@Field("lng") double lng);

    @GET("android/lbs_listfootprint")
    Observable<Result<FootPrintGet>> listUserFootPrint(@Query("target_id") int userid);

    @POST("android/user_upLoadCover")
    Observable<Result<String>> upLoadUserCover(@Query("coverPath") String coverPath);


    @FormUrlEncoded
    @POST("android/user_updateuserfriends")
    Observable<Result<List<UserCommonInfo>>> updateUserFriend2(@Field("friends") String friends);
    @GET("version.json")
    Observable<Version> checkUpdate();

    @FormUrlEncoded
    @POST("android/user_updateuserfriends")
    Observable<Result<List<UserCommonInfo>>> updateUserFriend3(@Field("friends") String friends);

    @POST("android/user_updateuserfriends")
    Observable<Result<List<UserCommonInfo>>> updateUserFriend(@Body Map<String,String> friends);

    @POST("android/user_updateuserinfo")
    Observable<Result<String>> updateUserInfo3(@Body UserCommonInfo userinfo);
    @FormUrlEncoded
    @POST("android/user_updateuserinfo")
    Observable<Result<String>> updateUserInfo(@FieldMap Map<String, String> options);
    @FormUrlEncoded
    @POST("android/user_updateuserinfo")
    Observable<Result<String>> updateUserInfo2(@Field("name") String name);
    @GET("android/user_userinfobyid")
    Observable<Result<UserCommonInfo>> getUserInfoById(@Query("taget_userid")int userid);
    @GET("android/user_userinfoandoptions")
    Observable<Result<UserInfo>> getUserInfoAndOption(@Query("taget_userid") int userid);

    @GET("android/user_longToken")
    Observable<Result<String>> getSToken(@Query("long_token")String long_token,@Query("phoneId")String phoneId);

    @GET("android/user_login")
    Observable<Result<User>> readBaidu(@Query("username") String username, @Query("password") String password,@Query("uniqueNum") String uniqueNum);

    @GET("ActionServlet?action=inituserinfo")
    Observable<Result<List<UserCommonInfo>>> initUserInfo(@Query("username") String username, @Query("password") String password);

    @GET("android/user_infosbyems")
    Observable<Result<List<UserCommonInfo>>> getUserListByEmList(@Query("emnamelist") String emnamelist);

    @GET("android/lbs_near")
    Observable<Result<List<UserLocatInfo>>> getNearByFromServer(@Query("latitude") double latitude, @Query("longitude") double longitude);

    @GET("android/user_userinfobyname")
    Observable<Result<List<UserCommonInfo>>> getUserCommonInfoByName(@Query("name") String name);

    @FormUrlEncoded
    @POST("android/user_register")
    Observable<Result<User>> registerUser(@Field("username") String username, @Field("password") String password,@Field("uniqueNum") String uniqueNum);

//    @GET("android/user_optionlog")
//    Observable<Result<List<OptionLogs>>> optionLogs(@Query("userid") int userid);

    /**
     *
     * 上传一张图片
     * @param imgs
     * @return
     */
    @Multipart
    @POST("android/up_head")
    Observable<Result<String>> uploadImage(@Query("userid") String userid,
                             @Part("file\"; fileName=\"demo.jpg\"") RequestBody imgs);

    @GET("android/topic_list")
    Observable<String> listTopic(@Query("start") int start);

    @GET("android/user_optionlog")
    Observable<Result<List<Option>>> listOption(@Query("userid") int userid);


    @GET
    Observable<String> sendGet(@Url String url, @QueryMap Map<String,String> options);

    @FormUrlEncoded
    @POST
    Observable<String> sendPost(@Url String url, @FieldMap Map<String, String> options);

//    Observable<Response<>>
}
