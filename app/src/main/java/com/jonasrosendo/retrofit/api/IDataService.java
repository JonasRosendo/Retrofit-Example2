package com.jonasrosendo.retrofit.api;

import com.jonasrosendo.retrofit.model.Photo;
import com.jonasrosendo.retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDataService {

    @GET("/photos")
    Call<List<Photo>> retrievePhotos();

    @GET("/posts")
    Call<List<Post>> retrievePosts();
}
