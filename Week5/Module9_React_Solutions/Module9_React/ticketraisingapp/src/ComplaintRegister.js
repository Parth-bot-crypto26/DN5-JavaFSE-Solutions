import React, { useState } from 'react';

function generateReferenceNumber() {
  return 'REF-' + Math.floor(100000 + Math.random() * 900000);
}

function ComplaintRegister() {
  const [employeeName, setEmployeeName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    const referenceNumber = generateReferenceNumber();
    alert(`Complaint registered successfully. Your reference number is ${referenceNumber}`);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Raise a Complaint</h2>
      <div>
        <label>Employee Name: </label>
        <input
          type="text"
          value={employeeName}
          onChange={(e) => setEmployeeName(e.target.value)}
        />
      </div>
      <div>
        <label>Complaint: </label>
        <textarea
          value={complaint}
          onChange={(e) => setComplaint(e.target.value)}
        />
      </div>
      <button type="submit">Submit Complaint</button>
    </form>
  );
}

export default ComplaintRegister;
