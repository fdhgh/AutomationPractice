
# AutomationPractice Project

Automated Test Suite for automationpractice.com using Cucumber and Selenium WebDriver (Java).


## Getting Started

### Running the tests

WebDrivers are held in `src\test\resources\webdrivers`

To run all tests, run the project as a Maven test with the chosen browser as `-Dbrowser` argument:
```
mvn test -Dbrowser=chrome
```

Browsers currently accepted are `chrome` and `firefox`. Please note that there are currently issues when running in Firefox, so Chrome is recommended. The version of the driver must correspond to the version of the browser installed on your system, per the recommendations on the developer's website.

### Reporting

HTML report is produced at the end of each run and saved to `target\htmlreports`

Screen captures of test failures are saved under the scenario name with a timestamp in `target\screenshots`


## General Info

This project uses:
* Maven - Dependency management and build tool
* Selenium WebDriver - Open source test automation tool https://www.seleniumhq.org/
* ChromeDriver - WebDriver for Chrome http://chromedriver.chromium.org/
* GeckoDriver - WebDriver for Firefox https://github.com/mozilla/geckodriver/releases
* JUnit - Test framework https://junit.org/junit4/
* Cucumber - BDD framework https://cucumber.io/

Written in Java 11 in Eclipse IDE.

Feature test steps are defined in the feature files: `src\test\java\features`


## Future improvements

* Reduce use of XPath and CSS selectors. These have been used sparingly where another locator could not be found or for some other reason could not be used. In particular, these have been used when parsing tables on the Order History Page.
* Decouple item number as basket item index from item number as element identifier. Where “item 1” is referenced in the feature files, this is interpreted as both the first item in the user’s basket test object (TestItem), as well as the first product listed on the webpage. These could be decoupled by giving the TestItem object its own id and storing some identifier for the product as an object variable.
* Check for and remove any test dependencies e.g. where one test must follow another. Known test dependencies include:
  * ReviewAddMessage test requires items to have been purchased
  * All tests rely on the user logon existing in the system
* Extend browser support (Edge, IE, etc.). Firefox is supported but has shown up issues particularly where JavaScript components perform differently than in Chrome. These issues could be worked through with more time spent on the task, however this was not explicitly in the scope of the task.
* Improve handling of WebDriver object. The WebDriver object is currently passed around a lot, particularly to methods that require it to wait for an object to be visible or clickable. This can presumably be improved but I have not determined the best way to handle the driver object.
* WebDriver files as dependencies instead of included in the resources. Originally the WebDriver was accessed via an environment variable but the WebDrivers were bundled into the test resources for ease of use.
* General refactoring – there’s always room for improvement!

### Other Issues

* The performance of the AutomationPractice website and the reliance on JavaScript can result in long wait times for visibility of elements, in turn affecting the runtime of the test.
* There is a lack of consistency of provision of element IDs, so in some cases other selectors have been used where required.
* The tests use a TestUser object variable to determine whether the user is logged in. This is set when the driver performs the login or logout action. There is a risk here of the TestUser login variable being out-of-sync with the user’s actual state on the website, for example if the login or logout action is unsuccessful. This could be fixed by using the state of the webpage to determine whether the user is logged in.
* Screen capture on test failure is brittle and relies on the element causing the failure to be scrolled into view prior to the test failure. The element in question is scrolled into view in some methods but there should be a standardised approach to this so that it does not have to be implemented at the page object level.
* Identifying the relevant order history by date/time is made difficult by the fact that the website seems to use US Eastern time. The date of the order is checked against the current date but this could fail at certain times. Presently if it does fail, it reverts to the most recent order. A better method of identifying the relevant order – without having to handle timezones – would be to extract the order reference number at the checkout stage, storing it in the TestUser object then using it to find the correct order history link text (which contains this reference number).
