import React from 'react';

const RUPEE_TO_EURO_RATE = 0.011;

class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = { rupees: '', euros: null };
    this.rupeesInput = React.createRef();
  }

  handleSubmit = (event) => {
    event.preventDefault();
    const rupees = parseFloat(this.rupeesInput.current.value) || 0;
    const euros = (rupees * RUPEE_TO_EURO_RATE).toFixed(2);
    this.setState({ rupees, euros });
  };

  render() {
    return (
      <div>
        <h2>Currency Convertor (INR to EUR)</h2>
        <form onSubmit={this.handleSubmit}>
          <input type="number" ref={this.rupeesInput} placeholder="Enter amount in INR" />
          <button type="submit">Convert</button>
        </form>
        {this.state.euros !== null && (
          <p>{this.state.rupees} INR = {this.state.euros} EUR</p>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
