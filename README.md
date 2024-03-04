
# Full stack example

Intentionally meant to be fullstack but it is only backend part of the project. The BE downloads exchange rate data and saves them into MongoDB database. User can decide whether retrieva data from the database or from the original source.


## Run Locally

Clone the project

```bash
git clone 
```

Go to the project directory

```bash
cd fullstackexample
```

Run docker with MongoDB instance

```bash
docker-compose -f ./compose-dev.yml up
```

Run the BE application

```bash
cd SpringBEExample/
./gradlew run
```

Alternative: Run the application from Intellij IDEA

```bash
If you open this project in Intellij IDEA you can run it directly by 
using top menu 'Run > Run or pressing Shift + F10.
```




