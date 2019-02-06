# MarshmallowBackEndTest

### How to run the app
 1. Clone this repository and import it into any spring enabled IDE
 2. Run a maven build on the project
 3. Run the project as a Spring Boot App
 4. Send the JSON payload to http://localhost:8080/calculate

### Sample input JSON:
```
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWW"
}
```

### Example output:
```
{
  "finalPosition" : [1, 3],
  "oilPatchesCleaned" : 1
}
```
