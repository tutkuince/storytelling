import defaultProfileImage from "@/images/profile.png";

export const UserListItem = ({ user }) => {
  return (
    <li className="list-group-item list-group-item-action">
      <img
        src={defaultProfileImage}
        width="30"
        className="img-fluid rounded-circle shadow-sm"
      />
      <span className="ms-2">{user.username}</span>
    </li>
  );
};
