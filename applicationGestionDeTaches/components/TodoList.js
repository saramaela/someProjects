import React, {useState, useEffect, useRef } from "react";
import { StyleSheet,ScrollView, View, TextInput, Button, Text, FlatList, Switch, Animated } from 'react-native';
import { createTask, taskList, deleteTask, checkAll, unCheckAll, doneTasks, undoneTasks, modifyTask} from "../API/todoAPI";
import TodoItem from './TodoItem';
import { TokenContext, UsernameContext } from "../Context/Context";



export default function TodoList(){
   
   /** initialiser todos afin de pouvoir l'utiliser avec les changements d'états*/
   const [todos, setTodos]= useState([])
   const [todosScreen, setTodosScreen] = useState([])
   const [count, setCount]= useState(todos.filter(item => item.done).length)
   const [newTodoText, setNewTodoText] = useState("")
   const [modifiedText, setModifyText] = useState("")
   const [tmpId, setTmpId] = useState("")


   const token = React.useContext(TokenContext)
   const username = React.useContext(UsernameContext)
   const counter = useRef(new Animated.Value(0)).current;
   const [enableModif, setEnableModif] = useState(false);

   const load = (value) => {
      Animated.timing(counter, {
        toValue: value,
        duration: 1000,
        useNativeDriver: true,
      }).start();
    };
   
   useEffect(() => {
      getTaskList()
   } ,[] 
   );

   useEffect(() => {
      setEnableModif(enableModif)
   } ,[enableModif] 
   );

   useEffect(() => {
      setModifyText(modifiedText)
   } ,[modifiedText] 
   );
   

   useEffect(() => {
      load(count)
      if (count >= 100) {
        setCount(100);
        clearInterval(countInterval);
      }
    }, [count]);
   

   const width = counter.interpolate({
      inputRange: [0, todosScreen.length],
      outputRange: ["0%", "100%"],
      extrapolate: "clamp"
    });



   function getTaskList() {
      
      taskList(username[0], token[0]).then(data => {
         if (JSON.stringify(todos) != JSON.stringify(data) ) {
            setTodos(data);
            setTodosScreen(data) ;
            setCount(data.filter(item => item.done).length)
         }

      })
   }
   

   const updateCountItemDone = (id) =>{
      /**compte les fonctions realisées et actualise au fur et à mesure */
      const newTodos = todos.map((todo) => {
      if (todo.id === id) {
         todo.done = !todo.done;
      } 
      return todo;
      });

      setCount(newTodos.filter(item => item.done).length);
   }


   const addNewTodo = () =>{
      /**ajoute un nouvel item à notre liste de taches */
      const newTask = createTask(newTodoText,username[0],token[0]).then(data => {getTaskList()})
      setNewTodoText("")
   }

   
   
   

   const progressTodo = () =>{
      /**affiche les todos pas encore réalisés cad todos non cochés*/
      undoneTasks(token[0], username[0]).
      then(data => {
         setTodos(data)
      })
   }


   const solvedTodo = () =>{
      /**affiche les todos dont l'exécution est terminée cad todos cochés*/
      doneTasks(token[0], username[0]).
      then(data => {
         setTodos(data)
      })  
   }


   const allTodos = () =>{
      /**affiche les todos qu'ils soient cochés ou non*/
      getTaskList()

   }


   const checkAllTodos = () =>{
      checkAll(token[0] ,username[0]).then(data => {getTaskList()})
   }
   

   const uncheckAllTodos = () =>{
      unCheckAll(token[0], username[0]).then(data => {getTaskList()})
   }


   const deleteTodo = (id) => {
      /**permet la suppression d'un item donné en paramètre */
      deleteTask(id,token[0]).then(getTaskList).then(getTaskList)
   }

   const modifyTodo = () =>{
      /**permet la modification d' un element */
      modifyTask(tmpId, token[0], modifiedText).then(getTaskList)
      console.log("ICI" + todos)
      setModifyText("")
      setEnableModif(false)
      setTmpId("")

   }

   const enableModifyTodo = () =>{
      setEnableModif(true)

   }

   

   return (
      
      <ScrollView contentContainerStyle={{flex: 1}} >
         
            <Text style={styles.textWelcome}>
               Bienvenue dans votre application de gestion de tâches !
               Il y a au total <span style ={{color:'#DC143C'}}>{todosScreen.length} tâche(s) </span> dont <span style ={{color:'#DC143C'}}>{count} realisée(s) </span>. Let's Go!
               &nbsp;
               &nbsp;
               &nbsp;
            </Text>

            <View style={styles.progressBar}>
               <Animated.View style={{ ...StyleSheet.absoluteFill, backgroundColor: '#DC143C', width: width }}/>
               <Text> {count}/{todosScreen.length}</Text>
            </View>
         
            
         <div>
            <FlatList
            numColumns={3}
               style={styles.listTask}
               data={todos}
               renderItem={({ item }) => <TodoItem item={item} getTaskList={getTaskList} deleteTodo={deleteTodo} enableModifyTodo={enableModifyTodo} setTmpId={setTmpId} updateCountItemDone={updateCountItemDone} modifyTodo={modifyTodo}
                  checkAllTodos={checkAllTodos} uncheckAllTodos={uncheckAllTodos} />} />
         </div>
         
         <div >
            <View style={styles.labelDiv}>
               <View>
               <TextInput style={styles.input}
               
               onChangeText={setModifyText}
               placeholder='Modifier ici votre item...'
               minLength={1}
               editable={enableModif}
               onSubmitEditing={modifyTodo}
               value={modifiedText} />
            </View>
            <View >
               <Button color='#2c3f5c' title="Enregistrer la modification" onPress={modifyTodo}>New</Button></View>
            </View>
            
         </div>
               
        <div>
         <View style={styles.labelDiv}>
            <View>
            <TextInput style={styles.input}
               
               onChangeText={setNewTodoText}
               placeholder='Saisir ici un nouvel item...'
               minLength={1}
               onSubmitEditing={addNewTodo}
               value={newTodoText} />
            </View>
            <View>
            <Button color='#2c3f5c' title="Créer nouvel item" onPress={addNewTodo}>New</Button>
            </View>
         </View>
         </div>

         <View style={styles.contentButton}>
               

               <Button color='#2c3f5c' title="Cocher tout" onPress={checkAllTodos}>New</Button>

               <Button color='#2c3f5c' title="Décocher tout" onPress={uncheckAllTodos}>New</Button>

               <Button color='#2c3f5c' title="Todos résolus" onPress={solvedTodo}>New</Button>

               <Button color='#2c3f5c' title="Todos en cours" onPress={progressTodo}>New</Button>

               <Button color='#2c3f5c' title="Tous les Todos (confondus)" onPress={allTodos}>New</Button>
         </View>
      </ScrollView>

   );
}



const styles = StyleSheet.create({

   contentButton: {
      contentContainerStyle : 'center',
      marginLeft : '35%',
      marginRight : '35%',
      justifyContent:'center',
      padding:5,
      
   },

   listTask: {
      marginLeft : '26%',
      paddingBottom : 20,
      contentContainerStyle : 'center',
      backgroundColor : 'e8ffff',
      fontSize: 12, 
   },

   input :{
      contentContainerStyle : 'center',
      borderColor :'black',
      padding :10,
      borderRadius : 5,
      borderWidth : 2,
      width : '50%',
      marginBottom: 5,
      marginLeft : '26%',
   },
   
   textWelcome:{
      fontSize: 22,
      fontFamily: 'roboto',
      padding : 35,
      justifyContent : 'center',

   },

   labelDiv:{
      marginLeft : '35%',
      marginRight : '35%',
      flexDirection: "row",
      flex: 2,
   },

   progressBar: {
      height: 40,
      display: 'flex',
      width: '30%',
      backgroundColor: 'white',
      borderColor: '#000',
      borderWidth: 2,
      borderRadius: 5,
      marginLeft : '35%',
      marginRight : '35%',
      marginBottom : 35,
      flexDirection: "row"
    }
})