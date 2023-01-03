import React, { useState, useEffect} from "react";
import { Image, View, Text, StyleSheet, Switch, TouchableOpacity } from 'react-native';
import { modifyTask, updateTask } from "../API/todoAPI";
import { TokenContext } from "../Context/Context";
import { getTaskList } from "./TodoList";

export default function TodoItem(props) {
    const [done, setDone] = useState(props.item.done);
    const [titleItem, setTitleItem] = useState(props.item.title);
    const token = React.useContext(TokenContext)

    useEffect(() => {
        setDone(props.item.done)
    },
        [props.item.done]
    );
    
    useEffect(() => {
        setTitleItem(props.item.title)
    },
        [props.item.title]
    );

    function update(id,token,done) {
        updateTask(id,token[0],done).then(data => {})
    }
   



    return (
        <View style={styles.content}>

            <Switch value={done} onValueChange={() => {setDone(!done); props.updateCountItemDone(props.item.id); update(props.item.id,token,!done)}}/>

            <Text style={[styles.text_item, { textDecorationLine: done ? 'line-through' : 'none' }]}>{titleItem}</Text>
        
            <TouchableOpacity onPress={() => {props.enableModifyTodo(); props.setTmpId(props.item.id)}}>
                
                <Image source={require('../assets/modify.png')} style={{ height: 24, width: 24 }} />

            </TouchableOpacity>

            <TouchableOpacity onPress={() => (props.deleteTodo(props.item.id))}>
                
                <Image source={require('../assets/trash-can-outline.png')} style={{ height: 24, width: 24 }} />

            </TouchableOpacity>

            
        </View>
    )
}

const styles = StyleSheet.create({
    content: {
        flexDirection: 'row'
    },
    text_item: {
        marginLeft: 10,
        width: 150
    }
})