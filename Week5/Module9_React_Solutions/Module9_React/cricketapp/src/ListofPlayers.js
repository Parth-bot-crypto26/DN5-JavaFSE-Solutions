import React from 'react';

const players = [
  { name: 'Rohit', score: 85 },
  { name: 'Virat', score: 92 },
  { name: 'Shubman', score: 45 },
  { name: 'Shreyas', score: 60 },
  { name: 'KL Rahul', score: 55 },
  { name: 'Hardik', score: 30 },
  { name: 'Ravindra', score: 72 },
  { name: 'Axar', score: 20 },
  { name: 'Kuldeep', score: 15 },
  { name: 'Bumrah', score: 5 },
  { name: 'Siraj', score: 10 }
];

function ListofPlayers() {
  const playerNames = players.map((player) => player.name);
  const lowScorers = players.filter((player) => player.score < 70);

  return (
    <div>
      <h2>List of Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
      <h3>All Player Names</h3>
      <p>{playerNames.join(', ')}</p>
      <h3>Players with score below 70</h3>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
