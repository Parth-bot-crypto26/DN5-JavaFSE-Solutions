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

  UpdateExit = () => {
    this.setState((prevState) => ({ exitCount: prevState.exitCount + 1 }));
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
