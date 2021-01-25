# Dealerware_GitHubAPI_Assessment
1. What concerns would you have from a testing perspective? 
- First of all I need to define why I need to test that functionality. I decided to test actions with GitHub repository because I find it very important. Almost every GitHub 
user creates own reposotiry to share the project. After that I have to define what kind of testing I would do. As static testing I'd walkthrough the documentation, analyze the requirements.
I do functional testing too. Initially I'd do manual testing first, I used Postman for that. According to GitHub API Documentation I provided all the steps and verified the responses.
Then I created my automation framework to do end-to-end testing.

2. How would you go about tackling the QA for this work?
My main support was GitHub documentation. Having and analyzing the API documentation I am able to provide and expect needed data. For example when I create new repository I know what 
should I pass into my request, what kind of method I'm sending, what response code I should expect and so on. In my company we use Swagger as documentation, it is very clear to get the idea 
about application API.

3. What sort of tests would be worth describing or worth automating?
In my case I have done end-to-end testing. I decided to automate that because those set of tests have to be executed over and over again. Definitely I would add them to smoke test.
Also by automationg them we save a lot of time. It would take much more time with manual testing.

4. What tools would use? 
I used Postman for manual testing, because first of all it is open source tool. Using Postman we can use environment, collection variables. Run set of tests as a collection. For automation,
I used RestAssured libraries. It supports given,when,then notations which make tests human readable. It easily integrates with other testing frameworks like Junit. I used Hamcrest
Matchers for assertion, also makes validation easy to read. For serialization I used Jackson databind which has different useful annotations. Lastly I used Lombok to automatically
generate getters, setters and constructors.
