import React from 'react'
import { View, Text, Button } from 'react-native'

import { TokenContext } from '../Context/Context'
import imgOut from '../assets/bye.jpeg'

export default function SignOut ({ navigation }) {
  return (
    <TokenContext.Consumer>
      {([token, setToken]) => (
        <>
        <View
            style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}
          >

            <Text style={{fontSize:16}}>Êtes-vous sûr(e) de vouloir vous déconnecter ? </Text>
            <Button color='#b80f0a' title='Je me déconnecte' onPress={() => setToken(null)} />
            <p><img src ={imgOut}  alt = 'Image Aurevoir' width={400} height={350}/></p>
            
            
          </View>
          
        </>
      )}
    </TokenContext.Consumer>
  )
}
