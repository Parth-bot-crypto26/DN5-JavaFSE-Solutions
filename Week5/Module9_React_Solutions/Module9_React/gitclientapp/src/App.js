import React, { useEffect, useState } from 'react';
import GitClient from './GitClient';

const gitClient = new GitClient();

function App() {
  const [repos, setRepos] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    gitClient
      .getRepositories('techiesyed')
      .then(setRepos)
      .catch((err) => setError(err.message));
  }, []);

  return (
    <div>
      <h1>Git Client App</h1>
      {error && <p>Error: {error}</p>}
      <ul>
        {repos.map((name, index) => (
          <li key={index}>{name}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
