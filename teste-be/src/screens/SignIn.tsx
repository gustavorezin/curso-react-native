import { VStack, Heading, Center } from "native-base";
import { Input } from "../components/Input";
import { Button } from "../components/Button";
import { api } from "../services/api";
import { useState } from "react";
import { ClienteDTO } from "../dtos/ClienteDTO";

export function SignIn() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const clientedto: ClienteDTO = {} as ClienteDTO;

  async function signIn() {
    try {
      clientedto.username = username;
      clientedto.password = password;
      console.log(
        `login: ${clientedto.username} // senha: ${clientedto.password}`
      );
      const { data } = await api.post("/auth/login", clientedto);
      console.log(data);
    } catch (error) {
      console.error("Error fetching data:", error);
      throw error;
    }
  }

  return (
    <VStack flex={1} px={10} bgColor="gray.100">
      <Center my={40}>
        <Heading bgColor="black" mb={12}>
          Login Spring Boot
        </Heading>
        <Input placeholder="Login" mb={2} onChangeText={setUsername} />
        <Input
          placeholder="Senha"
          mb={8}
          secureTextEntry
          onChangeText={setPassword}
        />
        <Button title="Acessar" onPress={signIn} />
      </Center>
    </VStack>
  );
}
