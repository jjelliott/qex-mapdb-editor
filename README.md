# qex-mapdb-editor
Compose Desktop application for editing the mapdb.json for the Quake EX port

## Running from source
To run from source, you must have Java 11 installed.

On Windows:
```
gradlew.bat run
```

On Unix:
```
./gradlew run
```

## Building
To build native packages you must have Java 15 or better.


On Windows:
```
gradlew.bat package
```

On Unix:
```
./gradlew package
```

This will create a binary for _your os_. I do not yet know how to package for another OS yet.
