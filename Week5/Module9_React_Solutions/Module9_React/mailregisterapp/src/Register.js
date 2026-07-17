import React, { useState } from 'react';

function Register() {
  const [formData, setFormData] = useState({ name: '', email: '', password: '' });
  const [errors, setErrors] = useState({});

  const validate = (data) => {
    const newErrors = {};

    if (!data.name || data.name.length < 5) {
      newErrors.name = 'Name should have at least 5 characters';
    }
    if (!data.email || !data.email.includes('@') || !data.email.includes('.')) {
      newErrors.email = 'Email should contain @ and .';
    }
    if (!data.password || data.password.length < 8) {
      newErrors.password = 'Password should have at least 8 characters';
    }
    return newErrors;
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    const updatedData = { ...formData, [name]: value };
    setFormData(updatedData);
    setErrors(validate(updatedData));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const validationErrors = validate(formData);
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length === 0) {
      alert('Registration successful!');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Mail Register</h2>
      <div>
        <label>Name: </label>
        <input type="text" name="name" value={formData.name} onChange={handleChange} />
        {errors.name && <p style={{ color: 'red' }}>{errors.name}</p>}
      </div>
      <div>
        <label>Email: </label>
        <input type="text" name="email" value={formData.email} onChange={handleChange} />
        {errors.email && <p style={{ color: 'red' }}>{errors.email}</p>}
      </div>
      <div>
        <label>Password: </label>
        <input type="password" name="password" value={formData.password} onChange={handleChange} />
        {errors.password && <p style={{ color: 'red' }}>{errors.password}</p>}
      </div>
      <button type="submit">Register</button>
    </form>
  );
}

export default Register;
