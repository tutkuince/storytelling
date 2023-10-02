import { useParams } from "react-router-dom";

export const Activation = () => {
  const { token } = useParams();
  return (
    <div>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptate porro
      iste perspiciatis natus dolor iusto facere, velit maxime quis atque quod
      pariatur incidunt totam fuga, reprehenderit quos sint quam beatae.
    </div>
  );
};
