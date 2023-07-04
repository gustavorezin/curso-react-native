import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#191919',
	}, 
	topsection: {
		height: 174,
		backgroundColor: '#0D0D0D',
		alignItems:'center',
		justifyContent: 'center',
	},
	topsectionLogo: {
		width: 110,
	}, 
	form: {
		paddingHorizontal: 24,
		flexDirection: 'row',
		position: 'absolute',
		marginTop: 150,
	},
	formInput: {
		flex: 1,
		backgroundColor: '#262626',
		height: 54,
		padding: 16,
		borderRadius: 6,
		fontSize: 16,
		color: '#F2F2F2',
		marginRight: 4,
		borderWidth: 1,
		borderColor: '#0D0D0D'
	}, 
	formButton: {
		backgroundColor: '#1E6F9F',
		width: 56,
		borderRadius: 6,
		alignItems:'center',
		justifyContent: 'center',
	}, 
	formButtonText: {
		color: '#F2F2F2',
		fontSize: 20,
	}, 
	statusContent: {
		marginTop: 54,
		flexDirection: 'row',
		paddingHorizontal: 24,
		height: 20,
		marginBottom: 24,
	}, 
	leftComponent: {
		flex: 1,
		flexDirection: 'row',
	},
	textLeft: {
		fontSize: 14,
		fontWeight: 'bold',
		color: '#4EA8DE',
		marginRight: 8,
	},
	rightComponent: {
		flexDirection: 'row',
	},
	textRight: {
		fontSize: 14,
		fontWeight: 'bold',
		color: '#8284FA',
		marginRight: 8,
	},
	inputComponent: {
		backgroundColor: '#333',
		borderRadius: 100,
		paddingHorizontal: 2,
		textAlign: 'center',
		fontWeight: 'bold',
		color: '#9A9A9A',
	},
	divider: {
		borderBottomWidth: 1,
		borderBottomColor: '#808080',
		marginVertical: 14,
		marginHorizontal: 24,
	},
})