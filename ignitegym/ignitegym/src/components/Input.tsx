import {
  Input as InputNativeBase,
  IInputProps,
  FormControl,
} from "native-base";

type Props = IInputProps & {
  errorMessage?: string | null;
};

export function Input({ errorMessage = null, ...rest }: Props) {
  const invalid = !!errorMessage; // "!!" transforma em booleano
  return (
    <FormControl isInvalid={invalid} mb={4}>
      <InputNativeBase
        bg="gray.700"
        h={14}
        px={4}
        borderWidth={0}
        fontSize="md"
        color="white"
        fontFamily="body"
        placeholderTextColor="gray.300"
        isInvalid={invalid}
        _invalid={{
          borderWidth: 1,
          borderColor: "red.700",
        }}
        _focus={{
          bg: "gray.700",
          borderWidth: 1,
          borderColor: "green.500",
        }}
        {...rest}
      />
      {/* <FormControl.ErrorMessage>{errorMessage}</FormControl.ErrorMessage> */}
    </FormControl>
  );
}
