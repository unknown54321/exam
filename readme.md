<h1>DominiGeiger Application Exam<h1>


**<h3>Input variables</h3>**
- travis encrypt $(heroku auth:token) --add deploy.api_key
- heroku config:set LOGZ_URL=<insert url>
- heroku config:set LOGZ_TOKEN=<token>

Default prefix on controller
- /devices


Some missing requirements: 
- I was not able to add unittesting in time, since i prioritized adding the necessary features to
the application.

- The measurements did not go as planned, so there are only measurements values. However 
none are in practical used case sceneario. 

Infrastructor: 
- https://github.com/unknown54321/infra
