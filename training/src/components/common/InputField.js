import React from "react";
import PropTypes from "prop-types";

const InputField = ({ value, name, onChange, label }) => {
  return (
    <>
      <div className="form-group">
        <label htmlFor={name}>{label}</label>
        <input
          type="text"
          id={name}
          className="form-control"
          name={name}
          value={value}
          onChange={onChange}
        />
      </div>
    </>
  );
};

InputField.propTypes = {
  value: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  label: PropTypes.string.isRequired,
};

export default InputField;
