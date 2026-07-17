import React from 'react';

const allPlayers = [
  'Rohit', 'Virat', 'Shubman', 'Shreyas', 'KL Rahul',
  'Hardik', 'Ravindra', 'Axar', 'Kuldeep', 'Bumrah', 'Siraj'
];

const [oddTeamPlayer, evenTeamPlayer, ...restPlayers] = allPlayers;

const T20players = ['Rohit', 'Virat', 'Suryakumar'];
const RanjiTrophyPlayers = ['Sarfaraz', 'Yashasvi', 'Prithvi'];
const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

function IndianPlayers() {
  return (
    <div>
      <h2>Indian Players</h2>
      <p>Odd Team Player: {oddTeamPlayer}</p>
      <p>Even Team Player: {evenTeamPlayer}</p>
      <p>Remaining Players: {restPlayers.join(', ')}</p>
      <h3>Merged Team (T20 + Ranji Trophy)</h3>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;
