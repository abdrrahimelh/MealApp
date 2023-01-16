**Introduction**
This project is an android application called MealApp.
It aims to provide the user with different recipes from different categories.

**Developers**
This project was done by :
- Abderrahim EL HIMIR
- Ghofrane HAMDOUNI

**Features developed**
In this application : 
- Images are clickable and they let you navigate to another page.
- Youtube URL's are hyperlinked and it navigates to the video on youtube.
- It is possible to choose favorite items using the star button.
- Favorite items (categories or recipes) are saved in your local storage. So even if you close your app you will still find them.

**Difficulties**
- One if the technical difficulties we faced in this app is the onClick on the Star Button. At the beginning  we put the onClick directly in the adapter which sounds okay but it doesn't work as expected when we click on a star for Item 1  Item 9 for example is added to favorites too . The solution was that we should implement an onclick interface that should be passed to the adapter from the MainActivity.
