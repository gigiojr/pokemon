# Approach
1. I've used this project to teach about Android applications and learn about [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) and [Room](https://developer.android.com/training/data-storage/room) libraries (I had never used these libraries before).
2. The architecture was created using the recommendation by Android Jetpack ([complete documentation](https://developer.android.com/jetpack/guide)) with MVVM patttern.
3. The main application has the folders:
```javascript
/data
    /di: dependency injection
    /model
        /api: api responses
        /db: database entity
    /network: network functions and validadtions
    /repository: repositories to connect to web services and local storage
    /service
    /util: generic approaches
    /viewmodel
/ui
    /activity
    /fragment
```
4. I've created a module called `components` to encapsulate all components of the application. 
   * This approach could be used to encapsulate other parts of the project like data, domain, web service. The better choice depends on the size of the project and how many projects will be linked to it.
5. I've create only two tests inside `components` module in `androidTest` folder to test `FlexOptionComponent` and `SearchBarComponent`. I can create more tests later.
6. About webhook, I've never seen this tool before and I didn't have a lot of time to study and create a permanent link, but I included two calls to send when pokemon is a catch (on the first page) and when pokemon is marked as favorite (on the detail page). You can see all the requests [here](https://webhook.site/#!/cf6b43a8-9a5e-44a5-870a-349f87c61d91/22d98548-efa3-498c-846e-b5ee3be35a03/1).
    * Observation: I don't know how long this link will be, so if you have problems and need to create another link you need:
      * Edit URL response with:
        ```javascript
        Default status code = 200
        Content Type = application/json,
        Timeout before response = 0
        Response body = { "sv_status" = true }
        ```
      * Update `data/service/WebHookService.kt` with id get in webhook.site.
7. The pages was create with different layouts to portrait and landscape orientation.
8. The local database is used only to get a bit history.

