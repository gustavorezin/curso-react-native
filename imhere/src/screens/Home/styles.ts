import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    backgroundColor: '#131016',
  }, 
  eventName: {
    color: '#FFF',
    fontSize: 24,
    marginTop: 24,
    fontWeight: 'bold',
  }, 
  eventDate: {
    color: '#6B6B6B',
  }, 
  input: {
    flex: 1,
    backgroundColor: '#1F1E25',
    color: '#FFF',
    height: 56, 
    padding: 16,
    fontSize: 16,
    borderRadius: 4,
    marginRight: 8,
  }, 
  button: {
    backgroundColor: '#31CF67',
    alignItems: 'center', 
    justifyContent: 'center',
    height: 56, 
    width: 56, 
    borderRadius: 4,
  }, 
  buttonText: {
    color: '#FFF',
    fontSize: 24,
  }, 
  form: {
    flexDirection: 'row',
    width: '100%',
    marginTop: 32,
    marginBottom: 48,
  }, 
  listEmptyText: {
    color: '#FFF',
    textAlign:'center',
  }
})