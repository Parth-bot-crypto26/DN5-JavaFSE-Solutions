import React from 'react';
import Counter from './Counter';
import WelcomeButton from './WelcomeButton';
import SyntheticEventButton from './SyntheticEventButton';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
  return (
    <div>
      <h1>Event Examples App</h1>
      <Counter />
      <WelcomeButton />
      <SyntheticEventButton />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
