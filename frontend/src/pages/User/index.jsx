import { Component } from "react";
import { useParams } from "react-router-dom";
import { loadUser } from "./api";

import { Spinner } from "@/shared/components/Spinner";
import { Alert } from "@/shared/components/Alert";
import { ProfileCard } from "./components/ProfileCard";

export class UserClass extends Component {
  state = {
    user: null,
    apiProgress: false,
    error: null,
  };

  loadSelectedUser = async () => {
    this.setState({ apiProgress: true });
    try {
      const response = await loadUser(this.props.id);
      this.setState({
        user: response.data,
      });
    } catch (error) {
      this.setState({
        error: error.response.data.message,
      });
    } finally {
      this.setState({ apiProgress: false });
    }
  };

  async componentDidMount() {
    this.loadSelectedUser();
  }

  componentDidUpdate(previousProps, previousState) {
    if (this.props.id !== previousProps.id) {
      this.loadSelectedUser();
    }
  }

  componentWillUnmount() {
    console.log("Component will be unmounted");
  }

  render() {
    return (
      <>
        {this.state.user && <ProfileCard user={this.state.user} />}
        {this.state.apiProgress && (
          <Alert styleType="secondary" center>
            <Spinner />
          </Alert>
        )}
        {this.state.error && (
          <Alert styleType="danger">{this.state.error}</Alert>
        )}
      </>
    );
  }
}

export const User = () => {
  const { id } = useParams();
  return (
    <>
      <UserClass id={id} />
    </>
  );
};
