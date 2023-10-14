import defaultProfileImage from "@/images/profile.png";

export const ProfileImage = ({ width }) => {
  return (
    <img
      src={defaultProfileImage}
      width={width}
      className="img-fluid rounded-circle shadow-sm"
    />
  );
};
