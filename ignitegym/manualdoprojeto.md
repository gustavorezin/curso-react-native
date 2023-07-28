## INICIANDO O PROJETO

- npx expo init ignitegym;

## MAPEAMENTO DAS PASTAS COM BABEL

- npm i --save-dev babel-plugin-module-resolver;
- configurar babel.config.js (plugins);
- substituir tsconfig.json;

## FONTE DO PROJETO

- https://docs.expo.dev/develop/user-interface/fonts/#use-a-google-font;
- npx expo install expo-font @expo-google-fonts/roboto;
- importar no App.tsx;

## INSTALAR NATIVE BASE (bootstrap do react native)

- https://docs.nativebase.io/install-expo
- npm i native-base
- expo install react-native-svg@12.1.1
- expo install react-native-safe-area-context@3.3.2

## PARA PODER USAR SVG

- https://github.com/kristerkari/react-native-svg-transformer
- npm i react-native-svg-transformer --save-dev
- criar arquivo: metro.config.js

## ROTAS DE NAVEGAÇÃO

- npm install @react-navigation/native
- npx expo install react-native-screens react-native-safe-area-context
- npm install @react-navigation/native-stack

- npm i @react-navigation/bottom-tabs

## GALERIA DE IMAGENS

- npx expo install expo-image-picker
- adicionar plugin no app.json
