import React from 'react';

class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entryCount: 0,
      exitCount: 0
    };
  }

  UpdateEntry = () => {
    this.setState((prevState) => ({ entryCount: prevState.entryCount + 1 }));
  };

  // BUG: copy-pasted from UpdateEntry and the state key was never changed,
  // so clicking "Exit" increments entryCount instead of exitCount.
  UpdateExit = () => {
    this.setState((prevState) => ({ entryCount: prevState.entryCount + 1 }));
  };

  render() {
    return (
      <div>
        <h1>Mall Visitor Counter</h1>
        <p>People entered: {this.state.entryCount}</p>
        <p>People exited: {this.state.exitCount}</p>
        <button onClick={this.UpdateEntry}>Login</button>
        <button onClick={this.UpdateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
