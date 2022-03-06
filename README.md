# MovieDataBase
Sample project using MVVM and Live data to load movies from The Movie Database

<p align="center">
  <img src="https://github.com/bardurt/MovieDataBase/blob/master/screenshots/Screenshot_20220306_100822.png" width="180" alt="accessibility text">
  <img src="https://github.com/bardurt/MovieDataBase/blob/master/screenshots/Screenshot_20220306_100856.png" width="180" alt="accessibility text">
  <img src="https://github.com/bardurt/MovieDataBase/blob/master/screenshots/Screenshot_20220306_100921.png" width="180" alt="accessibility text">
  <img src="https://github.com/bardurt/MovieDataBase/blob/master/screenshots/Screenshot_20220306_100950.png" width="180" alt="accessibility text">
</p>


## Before usage

### Step 1
Go to get https://www.themoviedb.org/ and get a free API KEY

### Step 2
got to the file ```com.bardur.moviedb.api.MovieDatabaseApiService.kt``` and find the variable 
```
private const val API_KEY = ""
``` 
and replace the empty String with the api key from <b>Step 1</b> 
``` 
private const val API_KEY = "<API_KEY_FROM_STEP_1>" 
```


### Step 3
Run the project and play around to see a list of popular movies!

### Step 4
Add your own features and improvements!

#### Things which can be improved
1 : Some layouts are very similar, can this be improved?

2 : Accessing the repo is not optimal, we get it from Activity by casting, can this be optimzed?

3 : Add Room to support persitent storage.
