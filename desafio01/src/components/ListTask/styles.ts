import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
	container: {
		backgroundColor: '#262626',
		marginHorizontal: 24,
		flexDirection: 'row',
		marginBottom: 10,
		height: 64,
		alignItems: 'center',
		borderRadius: 8,
		borderWidth: 1,
		borderColor: '#333'
	}, 
	taskCheckbox: {
		marginRight: 2,
		backgroundColor: '#FFF'
	}, 
	taskText: {
		color: '#F2F2F2'
	}
})