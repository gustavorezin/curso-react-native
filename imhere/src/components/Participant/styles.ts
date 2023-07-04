import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
	container: {
		width: '100%',
		flexDirection: 'row',
		alignItems: 'center',
		marginBottom: 10,
	},
	name: {
		backgroundColor: '#1F1E25', 
		color: '#FFF',
		fontSize: 16,
		flex: 1,
		padding: 16,
    marginRight: 8,
		borderRadius: 4,
	}, 
  button: {
    backgroundColor: '#E23C44',
    alignItems: 'center', 
    justifyContent: 'center',
    height: 56, 
    width: 56, 
    borderRadius: 4,
  }, 
  buttonText: {
    color: '#FFF',
    fontSize: 24,
  }
})