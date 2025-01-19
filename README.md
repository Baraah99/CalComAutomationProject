# Cal.com Testing Repository - Automation Project

This repository contains automated tests for the Scheduling application running on `http://localhost:3000`.
These tests ensure the functionality of key features such as adding, editing, and deleting events.

## Testing Strategy and Objectives

In addition to validate the Login. The primary objective of these tests is to validate that the core features of the events scheduling platform function as expected.
We focus on verifying the following:

- **Add New Event**: Ensuring a new event can be created with the correct event name, description, duration time and saved successfully.
- **Edit Event**: Verifying that an existing schedule can be associated with certain date and time (e.g., changing days or time).
- **Delete Event**: Ensuring an event can be deleted correctly.
- **Delete Booking**: Ensuring a Booking can be deleted correctly.

The tests are designed to cover all common user actions, providing assurance that the application is stable and functional.

## Setup and Requirements

To run these tests, ensure the following prerequisites are met:

1. **Node.js** (version 16 or above) is installed.
2. **npm** (Node Package Manager) is available.
3. **docker** (version 19 or above ) is installed

### Steps to Set Up the Testing Environment

1. Clone this repository:
   ```bash
   git clone https://github.com/baraah99/CalComProject.git
   
2. Set the Database of cal.com using docker:
    ```bash
    git clone https://github.com/calcom/docker


