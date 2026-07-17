import React from 'react';

// Technique 3: logical && operator and element variables
function CourseDetails({ enrolled }) {
  let statusMessage;
  switch (enrolled) {
    case true:
      statusMessage = <span>You are enrolled in "React for Beginners"</span>;
      break;
    default:
      statusMessage = <span>Enroll now to access "React for Beginners"</span>;
  }

  return (
    <div>
      <p>Course Details: {statusMessage}</p>
      {enrolled && <p>Next live session: Monday, 10 AM</p>}
    </div>
  );
}

export default CourseDetails;
