import React, { useState } from "react";
import { FlatList } from "react-native";
import { View, Text, Image, TextInput, TouchableOpacity } from "react-native";
import { ListTask } from "../../components/ListTask";
import { Task } from "../../types/Task";
import { styles } from "./styles";

export function Home() {

	const [tasks, setTasks] = useState<Task[]>([
		{id: 1, text: 'Comprar pão', done: false}
	])
	const [lastId, setLastId] = useState(0)

	const handleAddTask = (text: string, done = false) => {
		const newTask = {id: lastId + 1, text, done}
		setTasks([...tasks, newTask])
		setLastId(lastId + 1)
	}

	return(
		<View style={styles.container}>
			<View style={styles.topsection}>
				<Image 
					style={styles.topsectionLogo}
					source={require('../../../assets/logo.png')}
				/>
			</View>
			<View style={styles.form}>
				<TextInput 
					style={styles.formInput}
					placeholder={'Adicione uma nova tarefa'}
					placeholderTextColor={'#808080'}
					onSubmitEditing={(event) => handleAddTask(event.nativeEvent.text)}
				/>
				<TouchableOpacity style={styles.formButton}>
					<Text style={styles.formButtonText}>
						+
					</Text>
				</TouchableOpacity>
			</View>
			<View style={styles.statusContent}>
				<View style={styles.leftComponent}>
					<Text style={styles.textLeft}>Criadas</Text>
					<TextInput 
						style={styles.inputComponent}
						editable={false}
						value='0'
					/>
				</View>
				<View style={styles.rightComponent}>
					<Text style={styles.textRight}>Concluídas</Text>
					<TextInput 
						style={styles.inputComponent}
						editable={false}
						value='0'
					/>
				</View>
			</View>
			<FlatList 
				data={tasks}
				keyExtractor={(item) => item.id.toString()}
				renderItem={({ item }) => 
					<ListTask task={item}/>
				}
				showsVerticalScrollIndicator={false}
        ListEmptyComponent={() => (
          <View style={styles.divider} />
        )}
			/>
		</View>
	)
}