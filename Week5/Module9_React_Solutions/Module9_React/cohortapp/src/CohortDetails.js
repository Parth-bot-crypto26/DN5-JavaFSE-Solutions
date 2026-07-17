import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const statusColor = cohort.status === 'ongoing' ? 'green' : 'blue';

  return (
    <div className={styles.box}>
      <h3 style={{ color: statusColor }}>{cohort.cohortCode}</h3>
      <dl>
        <dt>Name</dt>
        <dd>{cohort.cohortName}</dd>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>
        <dt>Trainees</dt>
        <dd>{cohort.trainees}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
