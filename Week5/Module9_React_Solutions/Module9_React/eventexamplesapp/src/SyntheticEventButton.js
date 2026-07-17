import React from 'react';

function SyntheticEventButton() {
  const onPress = (event) => {
    alert('I was clicked');
  };

  return <button onClick={onPress}>OnPress</button>;
}

export default SyntheticEventButton;
