import { TouchableOpacityProps } from "react-native";
import { Container, Icon, Title } from "./styles";

// junta as propriedades do TouchableOpacity + adicionais.
type Props = TouchableOpacityProps & {
  title: string;
};

// ...rest seria o resto das propriedades, por exemplo as do TouchableOpacity
export function GroupCard({ title, ...rest }: Props) {
  return (
    <Container {...rest}>
      <Icon />
      <Title>{title}</Title>
    </Container>
  );
}
