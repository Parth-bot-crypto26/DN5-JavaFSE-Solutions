import React from 'react';

const office = {
  name: 'Skyline Business Tower',
  rent: 55000,
  address: 'MG Road, Bengaluru'
};

const officeList = [
  { name: 'Skyline Business Tower', rent: 55000, address: 'MG Road, Bengaluru' },
  { name: 'Tech Park One', rent: 72000, address: 'Whitefield, Bengaluru' },
  { name: 'Corporate Hub', rent: 48000, address: 'Andheri, Mumbai' },
  { name: 'Metro Plaza', rent: 65000, address: 'Salt Lake, Kolkata' }
];

function App() {
  const heading = <h1>Office Space Rental Listings</h1>;
  const officeImage = (
    <img
      src="https://placehold.co/300x150?text=Office+Space"
      alt="Office Space"
      width="300"
      height="150"
    />
  );

  return (
    <div>
      {heading}
      {officeImage}
      <h2>Featured Office</h2>
      <p>Name: {office.name}</p>
      <p>Address: {office.address}</p>
      <p style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
        Rent: Rs. {office.rent}
      </p>

      <h2>All Available Offices</h2>
      <ul>
        {officeList.map((item, index) => (
          <li key={index}>
            {item.name} - {item.address} -{' '}
            <span style={{ color: item.rent < 60000 ? 'red' : 'green' }}>
              Rs. {item.rent}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
