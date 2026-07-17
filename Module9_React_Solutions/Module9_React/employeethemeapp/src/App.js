import React, { useState } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';
import employeesMock from './employeesMock';

function App() {
  const [theme, setTheme] = useState('light');

  return (
    <ThemeContext.Provider value={theme}>
      <div>
        <h1>Employee Management</h1>
        <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
          Toggle Theme (current: {theme})
        </button>
        <EmployeesList employees={employeesMock} />
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
