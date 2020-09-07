import React from "react";
import PropTypes from "prop-types";

const SelectInput = ({
  value,
  name,
  options,
  onChange,
  label,
  defaultOption,
}) => {
  return (
    <>
      <div className="form-group">
        <label htmlFor={name}>{label}</label>
        <select
          className="form-control"
          name={name}
          value={value}
          onChange={onChange}
        >
          <option>{defaultOption}</option>
          {options.map((option) => {
            return (
              <React.Fragment key={option.value}>
                <option value={option.value}>{option.text}</option>
              </React.Fragment>
            );
          })}
        </select>
      </div>
    </>
  );
};

SelectInput.propTypes = {
  name: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  options: PropTypes.array.isRequired,
  label: PropTypes.string.isRequired,
  defaultOption: PropTypes.string,
  onChange: PropTypes.func.isRequired,
};

export default SelectInput;
