import { Text } from "native-base";

import { SignIn } from "./src/screens/SignIn";

import {
  Roboto_400Regular,
  Roboto_700Bold,
  useFonts,
} from "@expo-google-fonts/roboto";

import { NativeBaseProvider } from "native-base";
import { THEME } from "./src/theme";
import { StatusBar } from "react-native";

export default function App() {
  const [fontsLoaded] = useFonts({ Roboto_400Regular, Roboto_700Bold });
  return (
    <NativeBaseProvider theme={THEME}>
      <StatusBar
        barStyle="light-content"
        backgroundColor="transparent"
        translucent
      />
      {fontsLoaded ? <SignIn /> : <Text>upa</Text>}
    </NativeBaseProvider>
  );
}
