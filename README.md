I rewrote the app using a simple MVP pattern. The view layer is passive and can only display data provided by the presenters. The view layer contains everything related to Android and the presenters doesn't depend on Android at all. I used data bindings to bind the provided data directly to the views. I know not everyone likes db but I find it very useful in cases like this, displaying simple data in a list or details view. 
