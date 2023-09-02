# GitHub User Explorer

## Features

- Firebase Authentication for user sign-in.
- Displays a list of GitHub users fetched from the GitHub API.
- Shows detailed information of a selected GitHub user.
- Implements caching via Room.
- Follows MVVM architecture pattern and uses Jetpack Compose for UI.

## Technologies Used

- Jetpack Compose
- MVVM with Repository Pattern
- Android Architecture Components
- Coroutines
- Retrofit + Moshi
- Hilt
- Room (for Data Caching)
- Compose Destinations (for Navigation)
- Unit Testing
- Paparazzi Testing
- Compose Testing

## Installation

Clone this repository and import it into Android Studio. There are no special steps required for installation. Note that the GitHub API has a rate limit of 60 requests per hour.

## Challenges and Learnings

- Encountered issues with integrating Paparazzi due to duplicate dependencies.
- Explored different methods for handling one-shot events. Open to further discussion for best practices.
- Observed minor issues with navigation transitions. Currently, the navigation isn't as smooth as it could be. Transitions between screens show some glitches

## Known Limitations and Future Work (TODOs)

- Implement product flavors for better mock-version support.
- Extend test coverage as only fundamental parts are currently tested.
- Add a CI pipeline for automated lint checking and testing.
- Move hardcoded strings to the resource folder.
- Separate out shared UI components, themes, and resources into a separate module.
- Dependency clean-up and reorganization, possibly by grouping them in `libs.version`.
