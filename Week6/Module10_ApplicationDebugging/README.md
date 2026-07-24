# Module 10 — Application Debugging (Week 6)

This module doesn't have its own set of hands-on exercise documents in the course
material — it's grouped with Module 9 (React) as a combined 2-week block ("React and
application debugging"), and is about applying debugging skills to the apps built in
Module 9, not building new standalone apps.

## Contents

- **`.vscode/launch.json`** — reusable VS Code debug configuration for any Create-React-App
  project: one config to debug the running app in Chrome, one to debug Jest tests. Drop this
  `.vscode` folder into any Module 9 app to enable `F5` debugging.
- **`DEBUGGING_LOG.md`** — a worked example: a deliberately introduced bug in the `counterapp`
  from Module 9, debugged using both Chrome DevTools and the VS Code debugger, with the fix
  verified at the end.
- **`worked-example/before/CountPeople.js`** — the buggy version.
- **`worked-example/after/CountPeople.js`** — the fixed version (matches the correct version
  already in `Week5_6/Module9_React/counterapp/src/CountPeople.js`).

## How to reproduce this locally

```bash
# from Week5_6/Module9_React/counterapp
cp ../../../Module10_ApplicationDebugging/.vscode -r .
cp ../../../Module10_ApplicationDebugging/worked-example/before/CountPeople.js src/CountPeople.js
npm install
npm start
```

Then follow `DEBUGGING_LOG.md` step by step in Chrome DevTools and/or VS Code.
