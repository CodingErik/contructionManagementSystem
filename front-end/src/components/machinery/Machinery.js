import React, { useEffect, useState } from 'react';
import './Machinery.css';

export default function Materials(props) {
  const [machinery, setMachinery] = useState();

  useEffect(() => {
    setMachinery(props.machinery);
  }, [props]);

  return (
    <div>
      <h3 className='mb-3'>Machinery</h3>

      <div
        className='materialsWrapper row mt-3'
        style={{
          minHeight: '375px',
          minWidth: '275px',
        }}
      >
        {machinery &&
          Object.entries(machinery).map((key) => {
            return (
              <div className='col-6'>
                {key[0] !== 'id' && key[0] !== 'projectId' && (
                  <div className='materialOrMachineryIcon'>
                    <p className='textElementMaterials'>
                      {key[0].charAt(0).toUpperCase() +
                        key[0].substring(1, key[0].length)}
                    </p>
                    <p className='textElementMaterials'>{key[1]}/30</p>
                  </div>
                )}
              </div>
            );
          })}
      </div>
    </div>
  );
}
