import React from 'react';

class Getuser extends React.Component {
  constructor(props) {
    super(props);
    this.state = { user: null, loading: true, error: null };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then((response) => response.json())
      .then((data) => {
        this.setState({ user: data.results[0], loading: false });
      })
      .catch((error) => {
        this.setState({ error: error.message, loading: false });
      });
  }

  render() {
    const { user, loading, error } = this.state;

    if (loading) return <p>Loading user...</p>;
    if (error) return <p>Error fetching user: {error}</p>;

    return (
      <div>
        <h2>{user.name.title} {user.name.first}</h2>
        <img src={user.picture.large} alt={user.name.first} />
      </div>
    );
  }
}

export default Getuser;
