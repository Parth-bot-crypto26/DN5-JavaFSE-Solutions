import React from 'react';

class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }

  incrementCount = () => {
    this.setState((prevState) => ({ count: prevState.count + 1 }));
  };

  sayHello = () => {
    alert('Hello, thanks for incrementing the counter!');
  };

  handleIncrementClick = () => {
    this.incrementCount();
    this.sayHello();
  };

  handleDecrementClick = () => {
    this.setState((prevState) => ({ count: prevState.count - 1 }));
  };

  render() {
    return (
      <div>
        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.handleIncrementClick}>Increment</button>
        <button onClick={this.handleDecrementClick}>Decrement</button>
      </div>
    );
  }
}

export default Counter;
