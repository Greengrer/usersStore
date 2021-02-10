//Just an algorithms.
//So, what's better: add a same piece of code into every class, tests and Starter.class alike or to get it out in a separate class(I don't know which package, though, but it's for creating a datasource.)
// Obvious decision is putting it into separate class.

//So, 1. put creating datasource and filling serviceImplementation in separate function and class.
//Apparently - package is lab1.
//Name - let's call DBDataSource.
//2. Mock get requests. Oof. Okay. Way to create the stub:
//HtmlRequest mockedRequest = mock(HtmlRequest.class);
//
//3. Test if response has right headers and body. How precisely?  I can get header of a response. Also, Window.
//How to make window.

//aaaaaaaaaaaa. So I wanna mock service layer just so I don't dare touch anything besides web layer during testing.

//1. Mock ServiceImplementation.class.   Let's try it in GetUsersListServletTest.class... Do I make a separate class for that? Do I not? To do or not to do.
////10. Change all da tests to go with that mock.
////11. Get all da tests.

//2. I am not sure how, but test PageGenerator.GetPage - 'tis also weblayer.

//3. Wow, I've done this thing?

// Check with mocks if functions are called on response.

//4. Test when we should get exceptions.
//5. Search.
// How to make page show only what was searched for - not showing entirety of it in redirect?

//1. add a getPage(Map<String, Object>).
// Make Filter's doPost call this getPage.
// Change the template to incorporate message.


//2. find the way to map "else" on servlet.


// Find a way to write InputStream into response.


// Figure difference between classpath and "...src/main/...".
// Rearrange resources.

//3. Figure the way to filter by substring in SQL.

//4. Fix my Runtime Exceptions messages.

//5. Clean up the code.
