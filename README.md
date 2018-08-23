## Background

This is a demo app to demonstrate loading a gallery of images with the flickr api.

## Design

Due to time constraints this design is simple. It consist of a gallery of images from flickr with the title, dim and image size as part of each image entry.

One issue with this is the original directions say to use *flickr.photos.search* but now it no longer supported without a search parameter. So I added a tag search string along with other formatted search queries to get the needed data such as image url to load, rest response format such as json, and etc.

## Architecture

This is pretty simple, it breaks down to a network layer, and a MVC design. The network layer brings the data to the model in which the controller interacts with to load it into the display layer as a gridview of images.

The network uses Retrofit2 that uses a callback to do some task after getting the http response. I used this in place of a asynctask call since it handles a lot of customization and scalability such as adding more options for the method to be called in the url and passing in parameters.  

The images are loaded into a Recyclerview with a grid layout manager using Google's recommend Glide library. This not only handles image caching, but also handles errors and place holder for missing images and image scaling.

The rest data is in json, so I used fasterxml jackson to handle the conversion of the json into a POJO. It also works with Retrofit to do this automatically when making the call and getting the response. 

## Scalability 

This can improve if given more time. But it includes features that can handle this. 

1) The flickr api has an option to say how many results per call and what page to get them from. if created a helper class, you can create endless scrolling. This would require a scroll listener for the recyclerview that activates when you hit bottom of list. you can then make visible a progress bar loading screen and while the background pulls the next page of images that will then be updated into the list using *notifyItemRangeInserted* since you only one to update from that point on. if you did *notifyDataSetChanged* it may flicker the list and set you back to top of list, we don't want that. Another alternative is to use Android new paging library.

2) The library Glide has built in image caching that is able to handle large images and large number of images

3) Since we are using retrofit, you can chain call backs to do any other network functionality that may be needed in order. Each one can (for example) get images from different apis and eventually add them all to different or same list. Or what I had to do in the past, load a image that linked to a details page and needed to make a database call to check a bool flag, only after the page data got loaded by the rest call. 

4) It is also possible that since retrofit has the concept of *addConverterFactory* that it can handle rest calls of data of json, xml, and etc. As long as the models match the response. This gives the scalability to return other data formats.

## Trade offs

I don't like the static amount of data that is returned, if I had time I would implement paging.

I would also like to handle flickrs variations of image sizes. Not all images have a size, I would return multiple images sizes and find the largest working one to display on list.

I would also like to do more UI design with Google's Material Design. 

A requirement was to add the image size to thee entry, but the flickr api did not have this option. It had the option as a separate api call, which would be resourcefully wasted to do this for every image you load in the list. So I left it out.

One last trade off is the flickr library. I am not a fan of flickr. The api is well built with lot of options but the variety of images and quality are not as good. Also, the safe search does not always do it's job and some images I have seen in testing have been rated R.

