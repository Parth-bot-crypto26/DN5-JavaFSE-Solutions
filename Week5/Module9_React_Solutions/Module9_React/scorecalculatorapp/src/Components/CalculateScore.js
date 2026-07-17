import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = ((total / goal) * 100).toFixed(2);

  return (
    <div className="score-card">
      <h2>Score Card</h2>
      <p>Name: {name}</p>
      <p>School: {school}</p>
      <p>Total Marks: {total}</p>
      <p>Maximum Marks: {goal}</p>
      <p className="average">Average Score: {average}%</p>
    </div>
  );
}

export default CalculateScore;
