import React from 'react';

function GuestPage({ onLogin }) {
  return (
    <div>
      <h2>Available Flights</h2>
      <ul>
        <li>Flight AI-101 : Delhi to Mumbai : Rs. 4500</li>
        <li>Flight AI-202 : Bengaluru to Chennai : Rs. 2800</li>
        <li>Flight AI-303 : Kolkata to Hyderabad : Rs. 3600</li>
      </ul>
      <p>Login to book a ticket.</p>
      <button onClick={onLogin}>Login</button>
    </div>
  );
}

export default GuestPage;
