import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div>
      <CalculateScore name="Ravi Kumar" school="Green Valley High School" total={420} goal={500} />
    </div>
  );
}

export default App;
