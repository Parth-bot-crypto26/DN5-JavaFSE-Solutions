# Module 9 — React
## DN 5.0 Deep Skilling - Java FSE

Each exercise below is its own Create-React-App project (own `package.json`,
`public/`, `src/`). To run any of them locally:

```bash
cd <app-folder>
npm install
npm start
```

For the two apps with unit tests (`cohortapp`, `gitclientapp`):

```bash
npm test
```

> Note: these projects were authored in an environment without internet
> access, so `node_modules` were not installed/verified here. Run `npm
> install` locally before `npm start` / `npm test`.

---

## ✅ Mandatory Exercises

| # | Topic | App Folder |
|---|-------|------------|
| 1 ⭐ | Setting up a React environment, create-react-app | `myfirstreact` |
| 2 ⭐ | Class components, multiple components | `StudentApp` |
| 3 ⭐ | Function components, applying styles | `scorecalculatorapp` |
| 4 ⭐ | Component lifecycle (componentDidMount, componentDidCatch) | `blogapp` |
| 5 ⭐ | Styling with CSS Modules | `cohortapp` |
| 9 ⭐ | ES6 features (map, filter, destructuring, spread/merge) | `cricketapp` |
| 10 ⭐ | JSX, inline CSS | `officespacerentalapp` |
| 11 ⭐ | Events, event handlers, synthetic events | `eventexamplesapp` |
| 12 ⭐ | Conditional rendering | `ticketbookingapp` |
| 13 ⭐ | Conditional rendering (lists, keys, multiple techniques) | `bloggerapp` |

## 📚 Additional Important Hands-on

| # | Topic | App Folder |
|---|-------|------------|
| 6 | React Router (BrowserRouter, Routes, Route, Link, useParams) | `TrainersApp` |
| 7 | Props | `shoppingapp` |
| 8 | State | `counterapp` |
| 14 | Context API (createContext, Provider, useContext) | `employeethemeapp` |
| 15 | Forms | `ticketraisingapp` |
| 16 | Form validation | `mailregisterapp` |
| 17 | Consuming REST APIs | `fetchuserapp` |

## 🎁 Bonus (not in the mandatory hands-on tracker, included for completeness)

| # | Topic | App Folder |
|---|-------|------------|
| 18 | Unit testing with Jest & Enzyme | `cohortapp` (`CohortDetails.test.js`) |
| 19 | Mocking dependencies with Jest | `gitclientapp` (`GitClient.test.js`) |

---

## App Summaries

- **myfirstreact** — minimal app printing a welcome heading.
- **StudentApp** — `Home`, `About`, `Contact` class components composed in `App`.
- **scorecalculatorapp** — `CalculateScore` function component computing an average score, styled via `mystyle.css`.
- **blogapp** — `Posts` class component fetching from JSONPlaceholder in `componentDidMount`, with `componentDidCatch` error handling.
- **cohortapp** — `CohortDetails` styled with a CSS Module (green/blue heading by status), plus Enzyme/Jest unit tests (`CohortDetails.test.js`).
- **TrainersApp** — trainer directory with `react-router-dom` routes for Home, Trainers List, and Trainer Details (`useParams`).
- **shoppingapp** — `Cart` model class and `OnlineShopping` class component rendering 5 cart items via props.
- **counterapp** — `CountPeople` class component tracking mall entry/exit counts in state.
- **cricketapp** — `ListofPlayers` (map/filter) and `IndianPlayers` (destructuring/spread merge), toggled via a `flag`.
- **officespacerentalapp** — JSX elements/attributes, an office object and list, conditional inline-CSS coloring by rent.
- **eventexamplesapp** — `Counter` (increment/decrement + chained handlers), `WelcomeButton` (argument passing), `SyntheticEventButton` (`onPress`), `CurrencyConvertor` (INR→EUR on submit).
- **ticketbookingapp** — Guest vs. logged-in User views toggled via conditional rendering.
- **bloggerapp** — `BookDetails` (if/else), `BlogDetails` (ternary), `CourseDetails` (switch + `&&`) — three different conditional-rendering techniques.
- **employeethemeapp** — `ThemeContext` shared from `App` down to `EmployeeCard` without prop drilling.
- **ticketraisingapp** — `ComplaintRegister` form generating a reference number on submit.
- **mailregisterapp** — `Register` form with live + submit-time validation (name ≥ 5 chars, email has `@`/`.`, password ≥ 8 chars).
- **fetchuserapp** — `Getuser` class component fetching a random user profile and rendering title/first name/photo.
- **gitclientapp** — `GitClient` (axios) fetching a GitHub user's repo names, with a Jest test that mocks axios.
