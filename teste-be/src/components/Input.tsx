import { IInputProps, Input as InputNativeBase } from "native-base";

type Props = IInputProps & {};

export function Input({ ...rest }: Props) {
  return (
    <InputNativeBase
      bg="gray.700"
      h={14}
      px={4}
      borderWidth={0}
      fontSize="md"
      color="white"
      fontFamily="body"
      placeholderTextColor="gray.300"
      _focus={{
        bg: "gray.700",
        borderWidth: 1,
        borderColor: "green.500",
      }}
      {...rest}
    />
  );
}
