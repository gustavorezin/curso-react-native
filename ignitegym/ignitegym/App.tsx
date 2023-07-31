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

export default function App() {
  const [fonstLoaded] = useFonts({ Roboto_400Regular, Roboto_700Bold });

  return (
    <NativeBaseProvider theme={THEME}>
      <StatusBar
        barStyle="light-content"
        backgroundColor="transparent"
        translucent
      />
      {fonstLoaded ? <Routes /> : <Loading />}
    </NativeBaseProvider>
  );
}
