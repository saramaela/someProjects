import React from 'react'
import { ImageBackground,View, Text, Button } from 'react-native'
import { Link } from '@react-navigation/native'

import SignIn from '../components/SignIn'

export default function SignInScreen () {
  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        borderWidth: 18,

      borderColor: "#585858",
      borderRadius: 450,
      }}
    >
      

      <Text style={{fontSize: 26,
      padding : 35,
      justifyContent : 'center',
      color: '#001833',
      fontWeight: 'bold',
}}>
        Application de gestion de tâches
      </Text>
      <SignIn/>
      <Text style={{padding: 12,}}>
        Pas de compte ? Inscrivez-vous:{' '}
        <Link
          style={{ textDecorationLine: 'underline' }}
          to={{ screen: 'SignUp' }}
        >
          Inscription
        </Link>
      </Text>
    </View>
  )
}
