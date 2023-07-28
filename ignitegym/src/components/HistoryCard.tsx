import { HStack, Heading, Text, VStack } from "native-base";

export function HistoryCard() {
  return (
    <HStack
      w="full"
      bg="gray.500"
      py={4}
      px={5}
      mb={3}
      rounded="md"
      alignItems="center"
      justifyContent="space-between"
    >
      <VStack mr={5} flex={1}>
        <Heading
          color="white"
          fontSize="md"
          textTransform="capitalize"
          numberOfLines={1}
        >
          Costas
        </Heading>
        <Text color="gray.100" numberOfLines={1}>
          Puxada frontal
        </Text>
      </VStack>
      <Text color="gray.300" fontSize="md">
        17:46
      </Text>
    </HStack>
  );
}
