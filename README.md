# EventTrackerProject

## Overview

This application keeps track of a user's tv watching sessions over time. It links each session to which platform the user is watching on. The goal for the first weekend of this extended project was just setting up the api endpoints. The future work for this project is to allow users to view the breakdown of their time spent on each platform to better inform their subscription choices.

---

## How to Run

Use the following base URL in combination with the Resource URI from the REST API section to access the project's current API endpoints. `http://3.13.173.184:8080/TVTrackerApp/`

---

## REST API

| HTTP Method | Resource URI | Request Body | Returns |
|-------------|--------------|--------------|---------|
| GET         | `api/tv_watching_sessions` |    | `List&lt;TvWatchingSession&gt;` |
| GET        | `api/tv_watching_sessions/{id}`|  | `TvWatchingSession`  |
| POST      | `api/tv_watching_sessions` | `TvWatchingSession` | `List&lt;TvWatchingSession&gt;` |
| PUT       | `api/tv_watching_sessions/{id}`|  `TvWatchingSession` | `TvWatchingSession` |
| DELETE    |  `api/tv_watching_sessions/{id}`|   | `void` |

---

## Lessons Learned
When your base information table has foreign keys to two other tables, it increases the initial api building complexity. What I had assumed would be a super straight forward process turned into having to build out several of the other entities and tests before I could set up the api endpoints. I'm still happy with how the base-level of the project turned out and I'm excited to add a front-end to it.
