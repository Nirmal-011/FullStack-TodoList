import { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import { completeTodo, deleteTodo, incompleteTodo, listAllTodos } from "./Service/TodoService";
import { useHistory } from "react-router-dom";

type todo={
    id:number,
    title:string,
    description:string,
    taskCompleted:boolean
}

const ListOfTodo=()=>{
    let[todos,setTodos]=useState<todo[]>([])
    let navigator=useHistory();
   
    function getTodos(){
        listAllTodos()
        .then((response)=>{
          console.log( response.data);
            setTodos(response.data)
        }).catch((err)=>console.log(err))
    }

    function addTodo(){
        navigator.push("/add-todo")
    }

    function editTodo(id:number){
      navigator.push(`/update/${id}`)
    }

    function removeTodo(id:number){
      deleteTodo(id)
      .then((response)=>{
        console.log(response.data);
        getTodos();
      }).catch((error)=>console.log(error))
    }
   
    function complete(id:number){
      completeTodo(Number(id)).then((response)=>{
        console.log("Complete Todo",response.data);
        getTodos()
      }).catch((error)=>console.log(error))
    }

    function incomplete(id:number){
      incompleteTodo(Number(id))
      .then((response)=>{
        console.log("InComplete Todo",response.data)
        getTodos();
      }).catch((err)=>console.log(err))
    }

    useEffect(()=>getTodos(),[])
    return(<>
    <div className="container">
   
    <h1 style={{textAlign:"center", textDecoration:"underline", fontFamily:"initial"}}>List of Todos</h1>
    <button className="btn btn-primary" onClick={addTodo}>Add Todo</button>
    <br />
    <br />  
    <Table striped bordered hover>
        <thead>
          <tr >
            <th>Id</th>
            <th>Todo Title</th>
            <th>Todo Description</th>
            <th>TaskCompleted</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          { todos.map((todo)=>
          
            <tr key={todo.id}>
                <td>{todo.id}</td>
                <td>{todo.title}</td>
                <td>{todo.description}</td>
                <td>{todo.taskCompleted ? "Yes" : "No"}</td>
                <td>
               
                  <button className="btn btn-primary" onClick={()=>editTodo(todo.id)}>Update</button>&nbsp;&nbsp;
                  <button className="btn btn-danger" onClick={()=>removeTodo(todo.id)}>Delete</button>&nbsp;&nbsp;
                  <button className="btn btn-success" onClick={()=>complete(todo.id)}>Complete</button>&nbsp;&nbsp;
                  <button className="btn btn-info" onClick={()=>incomplete(todo.id)}>inComplete</button>
                </td>
            </tr>
            
            )

          }
         
        </tbody>
      </Table>
      </div>
      </>)
}
export default ListOfTodo;