
# Project CoderHack

A RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data. This project is designed for a service for managing the leaderboard of a specific contest, it has only one contest with a single leaderboard.



## Project Features

- **Register Contestant** -- Create a new user with UserID and Name, UserID will be checked for Unique value
- **View Contestant** -- Get a list of registered contestants sorted by Scores, also can be searched with a specific UserID.
- **Update Score** -- Update the score of a specific contestant.
- **Deletion of registered contestants** -- Delete the contestants from the contest.
- **Assign Badges** -- Based on the score of the specific contestant badges will be assigned.
    - 1 <= Score < 30 -> Code Ninja
    - 30 <= Score < 60 -> Code Champ
    - 60 <= Score <= 100 -> Code Master


## API EndPoints

- **GET /users** - Retrieve a list of all registered contestants.
- **GET /users/{userId}** - Retrieve the details of a specific contestant.
- **POST /users** - Register a new contestant to the contest.
- **PUT /users/{userId}** - Update the score of a specific contestant.
- **DELETE /users/{userId}** - Deregister a specific contestant from the contest.

### Installation Prequisite

- Java 17 or higher
- MongoDB
- PostMan to test the APIs

### Getting Started

***Clone this project to your local system.***

```bash
  git clone https://github.com/sbarman7593/CoderHack.git
```
***Go to the path CoderHack.***

```bash
  cd CoderHack
```
***Build the project using below command, or run the project from CoderHackApplication main class.***

```bash
  ./gradlew bootrun
```
## API Testing

To test the API endpoints for this project please follow this [PostMan Collection](https://personal-2212.postman.co/workspace/Personal-Workspace~35a77d54-cb46-44d1-95e3-988ec58edf33/collection/27961992-3a744518-e2d1-4644-b5a5-4751723fa304?action=share&creator=27961992).
