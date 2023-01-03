// npm i --save @react-navigation/bottom-tabs @react-navigation/native 

import React from 'react'
import { View, Text } from 'react-native'
import { NavigationContainer } from '@react-navigation/native'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import Icon from 'react-native-vector-icons/AntDesign'

import TodoListsScreen from '../Screen/TodoListsScreen'
import HomeScreen from '../Screen/HomeScreen'
import SignInScreen from '../Screen/SignInScreen'
import SignUpScreen from '../Screen/SignUpScreen'
import SignOutScreen from '../Screen/SignOutScreen'

import { TokenContext } from '../Context/Context'

const Tab = createBottomTabNavigator()

export default function Navigation () {
  return (
    <TokenContext.Consumer>
      {([token, setToken]) => (
        <NavigationContainer>
          {token == null ? (
            <Tab.Navigator>
              <Tab.Screen name='Connexion' component={SignInScreen}  options={{tabBarLabel: 'Connexion', tabBarIcon:({focused}) => (<Icon name = 'login' size = '25' color = 'steelblue'/>),}}/>
              <Tab.Screen name='Inscription' component={SignUpScreen} options={{tabBarLabel: 'Inscription', tabBarIcon:({focused}) => (<Icon name = 'addusergroup' size = '25' color = 'steelblue'/>),}}/>
            </Tab.Navigator>
          ) : (
            <Tab.Navigator>
              <Tab.Screen name='Accueil' component={HomeScreen} options={{tabBarLabel: 'Accueil', tabBarIcon:({focused}) => (<Icon name = 'home' size = '25' color = 'steelblue'/>),}}/>
              <Tab.Screen name='Liste des tâches' component={TodoListsScreen} options={{tabBarLabel: 'Liste des tâches', tabBarIcon:({focused}) => (<Icon name = 'book' size = '25' color = 'steelblue'/>),}}/>
              <Tab.Screen name='Deconnexion' component={SignOutScreen} options={{tabBarLabel: 'Deconnexion', tabBarIcon:({focused}) => (<Icon name = 'logout' size = '25' color = 'steelblue'/>),}}/>
            </Tab.Navigator>
          )}
        </NavigationContainer>
      )}
    </TokenContext.Consumer>
  )
}
