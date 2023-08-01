import { StatusBar } from "react-native";
import { NativeBaseProvider } from "native-base";
import {
  Roboto_400Regular,
  Roboto_700Bold,
  useFonts,
} from "@expo-google-fonts/roboto";

import { Routes } from "@routes/index";

import { Loading } from "@components/Loading";
import { THEME } from "./src/theme";

import { AuthContextProvider } from "@contexts/AuthContext";

export default function App() {
  const [fonstLoaded] = useFonts({ Roboto_400Regular, Roboto_700Bold });

  return (
    <NativeBaseProvider theme={THEME}>
      <StatusBar
        barStyle="light-content"
        backgroundColor="transparent"
        translucent
      />
      <AuthContextProvider>
        {fonstLoaded ? <Routes /> : <Loading />}
      </AuthContextProvider>
    </NativeBaseProvider>
  );
}
