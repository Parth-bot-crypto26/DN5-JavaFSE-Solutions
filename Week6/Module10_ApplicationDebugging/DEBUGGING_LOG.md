# Module 10 — Application Debugging: Worked Example

**App under test:** `counterapp` (from Module 9 / Week 5-6)
**Component:** `CountPeople.js`
**Bug:** Clicking the **Exit** button incorrectly increases the "People entered" count instead of "People exited".

This walkthrough demonstrates the debugging techniques from the module — Chrome DevTools
(breakpoints, DOM inspection, Sources panel) and the VS Code debugger (breakpoints, watches,
step into/over/out) — applied to a real bug.

---

## 1. Reproduce the symptom

1. `npm start` the app from `worked-example/before/` (swap it into `counterapp/src/` to run it).
2. Click **Login** a few times — "People entered" increases correctly.
3. Click **Exit** — "People entered" increases again instead of "People exited". Bug confirmed.

## 2. Debugging in Chrome DevTools

1. Open the app in Chrome, press `F12` (or right-click → Inspect) to open DevTools.
2. Go to the **Elements** tab and inspect the DOM — confirms both `<p>` counters and the
   `<button>Exit</button>` element exist as expected, so the bug isn't in the markup.
3. Switch to the **Sources** tab, locate `CountPeople.js` under the webpack source tree.
4. Click the line number for the line inside `UpdateExit` that calls `this.setState(...)` to set
   a breakpoint.
5. Click the **Exit** button in the running app — execution pauses at the breakpoint.
6. In the right-hand **Scope** panel, expand `prevState` — it shows `entryCount` and `exitCount`.
   Stepping over the `setState` call (F10) and then checking the **React DevTools** "Components"
   tab shows `entryCount` changed, `exitCount` did not — confirming the wrong state key is being
   updated inside `UpdateExit`.

## 3. Debugging in VS Code

1. Open the `counterapp` folder in VS Code.
2. Use the `.vscode/launch.json` provided in this folder — configuration **"Debug React app in
   Chrome"** (requires the `npm start` dev server already running on port 3000 and the
   **Debugger for Chrome** / built-in JS debug extension).
3. Set a breakpoint directly in the editor on the `setState` line inside `UpdateExit`.
4. Press `F5` to launch the debug session, click **Exit** in the browser.
5. VS Code pauses at the breakpoint. Add a **Watch**: `prevState.exitCount` — it's `0` and never
   changes, which is the smoking gun. Reading the line confirms `entryCount` was updated instead
   of `exitCount` — a copy-paste bug from `UpdateEntry`.

## 4. Fix

Changed the state key inside `UpdateExit` from `entryCount` to `exitCount`
(see `worked-example/after/CountPeople.js`).

## 5. Verify

Re-ran the app with the fix, repeated steps in section 1 — Exit now correctly increments
"People exited" only, Login only increments "People entered". Confirmed via both the on-screen
counters and the React DevTools Components panel showing the correct state shape after each click.

---

## Key debugging techniques demonstrated

- **DOM inspection** (Elements tab) to rule out markup/rendering issues.
- **Breakpoints** in both Chrome DevTools Sources panel and VS Code editor.
- **Stepping** through code execution (step over) to watch state updates happen live.
- **Scope/Watch inspection** to compare expected vs. actual variable values at the point of failure.
- **React DevTools** Components panel to confirm component state before/after a state update.
