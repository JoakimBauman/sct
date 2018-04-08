I rewrote the app using a simple MVP pattern. The view layer is passive and can only display data provided by the presenters. The view layer contains everything related to Android and the presenters doesn't depend on Android at all. I used data bindings to bind the provided data directly to the views. I know not everyone likes db but I find it very useful in cases like this, displaying simple data in a list or details view. 

# AndroidJobCandidate
Task description:

Fix all of the TODOs and rewrite the project so it reflects your coding style and preferred way of displaying a list of items and a details page. When clicking one of the items in the list, the details of that item should be shown. When loading data from the Api, there should be a ProgressBar visible. In the case of a connection timeout, there should be a fullscreen error message with a retry button. Clicking the retry button should make a new request to the api. At the interview we expect you to walk us through the code and explain what you have done. 

Your solution should be something you would put into production. This means that we expect that the app is stable and performs well in all possible use cases
