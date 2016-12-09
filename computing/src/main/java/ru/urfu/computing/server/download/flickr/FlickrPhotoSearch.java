package ru.urfu.computing.server.download.flickr;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

public class FlickrPhotoSearch {
	
    public static void main(String[] args) {
    	
    	String apiKey = "97eca87d19115e8d4b4f3c5a0a133dd3";
    	String sharedSecret = "f08218180a89afbf";
    	
    	Flickr f = new Flickr(apiKey, sharedSecret, new REST());
    	PhotosInterface IPhotos =  f.getPhotosInterface();
    	try {
    		SearchParameters params = new SearchParameters();
    		params.setUserId("59159233@N04");
    		int perPage = 10;
    		int page = 1;
    		PhotoList<Photo> res = IPhotos.search(params, perPage, page);
    		System.out.println(res.get(0).getUrl());
    	}
    	catch (FlickrException e)
    	{
    		e.printStackTrace();;
    	}
    }
}