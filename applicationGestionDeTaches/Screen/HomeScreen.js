import React from 'react'
import { View, Text, Button } from 'react-native'
import {Document, Page, StyleSheet, PDFViewer, ReactPDF, PDFDownloadLink} from '@react-pdf/renderer'
import TodoList from '../components/TodoList'

import { UsernameContext } from '../Context/Context'
import imgTodo from '../assets/todoImage.png'




const MyDoc = ()=>(
  <Document>
    <Page size= 'A4'>
      <View>
      <Text> Bonjour</Text>
      </View>
    </Page>
  </Document>
);





export default function HomeScreen () {
  return (
    <UsernameContext.Consumer>
      {([username, setUsername]) => {
        return (
          <View
            style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>

            <p><img src ={imgTodo}  alt = 'Image Todo' width={500} height={325}/></p>
            <Text style={{fontSize:22}}>Bienvenue !</Text>
            <Text style={{fontSize:22, marginBottom : 7}}>Vous êtes connecté(e) en tant que <span style ={{color:'#48494b',
            fontWeight:'bold', textDecorationLine:'underline'}}>{username} </span> </Text>

          </View>
        )
      }}
    </UsernameContext.Consumer>
  )
}
