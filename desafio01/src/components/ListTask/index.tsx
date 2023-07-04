import { useState } from "react";
import { View, Text } from "react-native";
import { CheckBox } from "react-native-elements";
import { Task } from "../../types/Task";
import { styles } from "./styles";
import Icon from 'react-native-vector-icons/FontAwesome';

type Props = {
	task: Task
}

export function ListTask({ task }: Props) {
	const [isSelected, setSelection] = useState(false);

	return(
		<View style={styles.container}>
			<CheckBox
			style={styles.taskCheckbox}
				checked={isSelected}
				onPress={() => setSelection(!isSelected)} 
				uncheckedColor="#757575"
				checkedColor="#2196f3"
				checkedIcon={<Icon name="check" color="#2196f3" />}
			/> 
			<Text style={styles.taskText}>
				{task.text}
			</Text>
		</View>
	)
}