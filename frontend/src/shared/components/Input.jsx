export const Input = (props) => {
  const { id, label, type, error, onChange, defaultValue } = props;

  return (
    <div className="form-outline mb-4">
      <label className="form-label" htmlFor={id}>
        {label}
      </label>
      <input
        type={type}
        id="username"
        className={
          error
            ? "form-control form-control-lg is-invalid"
            : "form-control form-control-lg"
        }
        onChange={onChange}
        defaultValue={defaultValue}
      />
      <div className="invalid-feedback">{error}</div>
    </div>
  );
};
