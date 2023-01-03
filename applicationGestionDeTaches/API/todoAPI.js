const API_URL = 'http://127.0.0.1:4000'

const SIGN_IN =
  'mutation($username:String!, $password:String!){signIn(username:$username, password:$password)}'

const SIGN_UP =
  'mutation($username:String!, $password:String!){signUp(username:$username, password:$password)}'

const TASK_LIST = 
  'query taskLists($username: String!) {tasks(where: { owner: { username: $username } }) {id title done}}'

const CREATE_TASK = 
  'mutation newTask($title: String!, $username: String!) {createTasks(input: {title: $title done: false owner: { connect: { where: { username: $username } } }}) {tasks {title}}}'

const DELETE_TASK = 
  'mutation delete($id:ID){deleteTasks(where: { id: $id }){nodesDeleted}}'

const UPDATE_TASK = 
  'mutation update($id: ID, $done: Boolean) {updateTasks(where: { id: $id }update: { done: $done}) {tasks{id title done}}}'

const MODIFY_TASK = 
  'mutation update($id: ID, $title: String) {updateTasks(where: { id: $id }update: { title: $title}) {tasks{id title done}}}'

const CHECK_ALL = 
  'mutation($user:String!) {updateTasks(update: {done: true} where:{owner:{username:$user}}) {tasks{id title done}}}'

const UNCHECK_ALL = 
  'mutation($user:String!) {updateTasks(update: {done: false} where:{owner:{username:$user}}) {tasks{id title done}}}'

const DONE_TASKS =
  'query doneTasks($user:String!){tasks(where: { owner: { username:  $user} done : true }) {id title done}}'

const UNDONE_TASKS =
  'query doneTasks($user:String!){tasks(where: { owner: { username:  $user} done : false }) {id title done}}'


export function signIn (username, password) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      query: SIGN_IN,
      variables: {
        username: username,
        password: password
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.signIn
    })
    .catch(error => {
      throw error
    })
}


export function signUp (username, password) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      query: SIGN_UP,
      variables: {
        username: username,
        password: password
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.signUp
    })
    .catch(error => {
      throw error
    })
}


export function taskList (username,token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: TASK_LIST,
      variables: {
        username: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}


export function doneTasks (token ,username) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: DONE_TASKS,
      variables: {
        user: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}



export function undoneTasks (token ,username) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: UNDONE_TASKS,
      variables: {
        user: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}



export function createTask (title,username,token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: CREATE_TASK,
      variables: {
        username: username,
        title: title
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
    })
    .catch(error => {
      throw error
    })
}



export function deleteTask (id,token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: DELETE_TASK,
      variables: {
        id: id
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
    })
    .catch(error => {
      throw error
    })
}



export function updateTask (id,token,done) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: UPDATE_TASK,
      variables: {
        id: id,
        done: done
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}

export function modifyTask (id,token,title) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: MODIFY_TASK,
      variables: {
        id: id,
        title: title
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}

export function checkAll (token, username) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: CHECK_ALL,
      variables: {
        user: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}



export function unCheckAll (token,username) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "authorization" : "Bearer " + token
    },
    body: JSON.stringify({
      query: UNCHECK_ALL,
      variables: {
        user: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
}



