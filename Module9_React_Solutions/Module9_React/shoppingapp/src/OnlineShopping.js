import React from 'react';
import Cart from './Cart';

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.cartItems = [
      new Cart('Wireless Mouse', 599),
      new Cart('Mechanical Keyboard', 2499),
      new Cart('USB-C Hub', 1299),
      new Cart('Laptop Stand', 899),
      new Cart('Webcam HD', 1799)
    ];
  }

  render() {
    return (
      <div>
        <h1>Shopping Cart</h1>
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Price (Rs.)</th>
            </tr>
          </thead>
          <tbody>
            {this.cartItems.map((item, index) => (
              <tr key={index}>
                <td>{item.itemName}</td>
                <td>{item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
