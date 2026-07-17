import React from 'react';

function UserPage({ onLogout }) {
  return (
    <div>
      <h2>Book Your Flight</h2>
      <ul>
        <li>Flight AI-101 : Delhi to Mumbai : Rs. 4500 <button>Book</button></li>
        <li>Flight AI-202 : Bengaluru to Chennai : Rs. 2800 <button>Book</button></li>
        <li>Flight AI-303 : Kolkata to Hyderabad : Rs. 3600 <button>Book</button></li>
      </ul>
      <button onClick={onLogout}>Logout</button>
    </div>
  );
}

export default UserPage;
