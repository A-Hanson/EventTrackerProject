# EventTrackerProject

## Overview

## How to Run

## REST API

| HTTP Method | Resource URI | Request Body | Returns |
|-------------|--------------|--------------|---------|
| GET         | `api/tv_watching_sessions` |    | `List&lt;TvWatchingSession&gt;` |
| GET        | `api/tv_watching_sessions/{id}`|  | `TvWatchingSession`  |
| POST      | `api/tv_watching_sessions` | `TvWatchingSession` | `List&lt;TvWatchingSession&gt;` |
| PUT       | `api/tv_watching_sessions/{id}`|  `TvWatchingSession` | `TvWatchingSession` |
| DELETE    |  `api/tv_watching_sessions/{id}`|   | `void` |
