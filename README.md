# CleanArchitecture
This repo demonstrates Clean Architecture built on top of MVVM, Usecases . It uses Coroutines, MVVM, LiveData, Dagger 2.


This repo consumes the Github API to search for an user and list the repositories for that particular user .


App is divided into 2 features , feature_search -> User can search for an user, feature_repositories -> Can find all the repositories for that user .


feature_search -> Using caching with remote API calls. When searching for an user , it will first check whether that particular record is present on Room DB , if it is then it will return that value and then call the API to update the UI and updates the data on the Room DB. If data is not present on the room DB , calls remote API , saves the data in DB and shows it to UI.



feature_repository -> This is an example of usage of visitor pattern with recycler view . Fetching the repository data from the API , modifying the response in the Repository class and add a different data Type in the response . Then convert the data model to UIModel which is used on the TypeFactory to different which ViewHolder to inflate while rendering the list into the screen.




