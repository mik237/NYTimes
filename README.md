# NY_Times

NYTimes Project code structure:

<b>How to run:</b>
Import the project in Android Studio and build.

<b>Packages</b>

  1. <b>adapters:</b>    Containing the PopularArticlesListAdapter class which is responsible for dispatching list of popular articles to recyclerview.
  2. <b>api:</b>         Containing the NetworkService interface & model classes to handle response from api.
  3. <b>di:</b>          Containing AppModule & NetworkModule providing dependencies
  4. <b>mappers:</b> 	 Containing mapper class responsible for mapping response model to view model with few fields.
  5. <b>ui:</b>		 Containing activities, fragments & viewmodels.
  6. <b>util:</b>     	 Containing utilities classes, extensions and constants.
  

Application Architecture using MVI architecture.

<b>Libraries Used</b>
  1. AndroidX
  2. Glide
  3. Retrofit
  4. Hilt for dependency injection.
  5. NavigationController
  6. MVI architecture
  7. NetworkBoundResource


<b>Flow chart</b> (https://drive.google.com/file/d/1UBNVYVNqWc13pKwtb2Q7GK8qrjUo2VV_/view?usp=sharing)


