import React from 'react';

function sayWelcome(message) {
  alert(`Say ${message}`);
}

function WelcomeButton() {
  return <button onClick={() => sayWelcome('welcome')}>Say Welcome</button>;
}

export default WelcomeButton;
